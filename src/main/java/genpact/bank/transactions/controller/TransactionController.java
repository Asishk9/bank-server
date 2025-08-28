package genpact.bank.transactions.controller;

import genpact.bank.account.entity.Account;
import genpact.bank.account.service.AccountService;
import genpact.bank.transactions.entities.Transaction;
import genpact.bank.transactions.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;

	// Get all transactions
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		if (transactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	// Get transaction details by ID
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Integer transactionId) {
		Transaction transaction = transactionService.getTransactionById(transactionId);
		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(transaction, HttpStatus.OK);
	}

	// Add a new transaction
	@PostMapping("/add")
	public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
		transactionService.addTransaction(transaction);
		return new ResponseEntity<>("Transaction added successfully!", HttpStatus.CREATED);
	}

	@GetMapping("/branch/{branchId}")
	public ResponseEntity<List<Transaction>> getBranchTransactions(@PathVariable int branchId) {
		List<Transaction> transactions = transactionService.getBranchTransactions(branchId);
		if (transactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}

	// Withdraw amount from an account
	@PutMapping("/withdraw/{accountId}")
	public ResponseEntity<?> withdrawAmount(@PathVariable int accountId, @RequestBody Map<String, Integer> payload) {
		try {
			// System.out.println(payload);
			if (payload == null || !payload.containsKey("amount")) {
				return ResponseEntity.badRequest().body("Amount is required");
			}

			int amount = payload.get("amount");
			if (amount <= 0) {
				return ResponseEntity.badRequest().body("Invalid Amount");
			}

			Account account = accountService.getAccountById(accountId);

			if (account.getBalance() < amount) {
				return ResponseEntity.badRequest().body("Insufficient balance");
			}

			accountService.withdraw(accountId, amount);

			// Create and save transaction
			Transaction transaction = new Transaction();
			transaction.setAccountId(accountId);
			transaction.setTransactionType("Debit");
			transaction.setAmount(amount);
			transaction.setStatus("Completed");
			transaction.setFinalBalance(account.getBalance() - amount);
			transaction.setTransactionMode("Cash");
			transaction.setDescription("Withdrawal through Cash");
			transactionService.addTransaction(transaction);

			// System.out.println(transaction.getReferenceNumber());

			Transaction transactionDetails = transactionService.getTransactionByRef(transaction.getReferenceNumber());

			// Return transaction details
			return ResponseEntity.ok().body(transactionDetails);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing withdrawal ");
		}
	}

	// Deposit money into an account
	@PutMapping("/deposit/{accountId}")
	public ResponseEntity<?> depositAmount(@PathVariable int accountId, @RequestBody Map<String, Integer> payload) {
		try {
			// System.out.println(payload);
			if (payload == null || !payload.containsKey("amount")) {
				return ResponseEntity.badRequest().body("Amount is required");
			}

			int amount = payload.get("amount");
			if (amount <= 0) {
				return ResponseEntity.badRequest().body("Invalid Amount");
			}

			Account account = accountService.getAccountById(accountId);

			accountService.deposit(accountId, amount);

			// Create and save transaction
			Transaction transaction = new Transaction();
			transaction.setAccountId(accountId);
			transaction.setTransactionType("Credit");
			transaction.setAmount(amount);
			transaction.setStatus("Completed");
			transaction.setFinalBalance(account.getBalance() + amount);
			transaction.setTransactionMode("Cash");
			transaction.setDescription("Deposit through Cash");
			transactionService.addTransaction(transaction);

			// System.out.println(transaction.getReferenceNumber());

			Transaction transactionDetails = transactionService.getTransactionByRef(transaction.getReferenceNumber());

			// Return transaction details
			return ResponseEntity.ok().body(transactionDetails);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing deposit: ");
		}
	}

	// Send money to another account
	@PutMapping("/transfer/{accountId}")
	public ResponseEntity<?> transferMoney(@PathVariable int accountId, @RequestBody Map<String, String> request) {
		try {
			System.out.println(request);
			if (request == null || !request.containsKey("amount") || !request.containsKey("accountNo")) {
				return ResponseEntity.badRequest().body("Amount and target account number are required");
			}

			int amount = Integer.parseInt(request.get("amount"));
			if (amount <= 0) {
				return ResponseEntity.badRequest().body("Invalid Amount");
			}

			String targetAccountNo = request.get("accountNo");
			Account sourceAccount = accountService.getAccountById(accountId);
			Account targetAccount = accountService.getAccountByAccountNo(targetAccountNo);

			System.out.println(targetAccount);

			if (sourceAccount.getAccountNo().equals(targetAccountNo)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Please enter reciver Account number");
			}

			if (sourceAccount.getBalance() < amount) {
				return ResponseEntity.badRequest().body("Insufficient balance");
			}

			// Perform the transfer
			accountService.send(accountId, amount, targetAccountNo);

			// Create and save transaction for source account
			Transaction debitTransaction = new Transaction();
			debitTransaction.setAccountId(accountId);
			debitTransaction.setTransactionType("Debit");
			debitTransaction.setAmount(amount);
			debitTransaction.setStatus("Completed");
			debitTransaction.setFinalBalance(sourceAccount.getBalance() - amount);
			debitTransaction.setTransactionMode("Online");
			debitTransaction.setDescription("Transfer to account " + targetAccountNo);
			transactionService.addTransaction(debitTransaction);

			// Create and save transaction for target account
			Transaction creditTransaction = new Transaction();
			creditTransaction.setAccountId(targetAccount.getAccountId());
			creditTransaction.setTransactionType("Credit");
			creditTransaction.setAmount(amount);
			creditTransaction.setStatus("Completed");
			creditTransaction.setFinalBalance(targetAccount.getBalance() + amount);
			creditTransaction.setTransactionMode("Online");
			creditTransaction.setDescription("Transfer from account " + sourceAccount.getAccountNo());
			transactionService.addTransaction(creditTransaction);

			Transaction transactionDetails = transactionService
					.getTransactionByRef(debitTransaction.getReferenceNumber());

			// Return transaction details
			return ResponseEntity.ok().body(transactionDetails);

		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing transfer: ");
		}
	}

	// Get transactions by account ID
	@GetMapping("/statement/{accountId}")
	public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable int accountId) {
		try {
			List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);

			// Check if transactions exist
			if (transactions.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a transaction
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateTransaction(@PathVariable("id") Integer transactionId,
			@RequestBody Transaction updatedTransaction) {
		Transaction existingTransaction = transactionService.getTransactionById(transactionId);
		if (existingTransaction == null) {
			return new ResponseEntity<>("Transaction not found.", HttpStatus.NOT_FOUND);
		}
		updatedTransaction.setTransactionId(transactionId);
		transactionService.updateTransaction(updatedTransaction);
		return new ResponseEntity<>("Transaction updated successfully!", HttpStatus.OK);
	}

	// Delete a transaction by ID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTransaction(@PathVariable("id") Integer transactionId) {
		Transaction transaction = transactionService.getTransactionById(transactionId);
		if (transaction == null) {
			return new ResponseEntity<>("Transaction not found.", HttpStatus.NOT_FOUND);
		}
		transactionService.deleteTransaction(transactionId);
		return new ResponseEntity<>("Transaction deleted successfully!", HttpStatus.OK);
	}
}
