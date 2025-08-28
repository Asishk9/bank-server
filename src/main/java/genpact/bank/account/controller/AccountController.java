package genpact.bank.account.controller;

import genpact.bank.account.entity.Account;
import genpact.bank.account.service.AccountService;
import genpact.bank.user.entity.User;
import genpact.bank.user.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	UserService userService;

	// Get all accounts
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accountList = accountService.getAllAccounts();
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}

	// Get all accounts in a specific branch
	@GetMapping("/branch/{branchId}")
	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable int branchId) {
		List<Account> accountList = accountService.getAllAccountsInBranch(branchId);
		return new ResponseEntity<>(accountList, HttpStatus.OK);
	}

	// View account by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> viewAccount(@PathVariable int id) {
		Account account = accountService.getAccountById(id);
		if (account == null) {
			return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	// View accounts by customer ID
	@GetMapping("/customer/{id}")
	public ResponseEntity<List<Account>> viewAccountByCustomerId(@PathVariable int id) {
		List<Account> accounts = accountService.getAccountByCustomerId(id);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("/customerDetails/{customerId}")
	public ResponseEntity<Object> viewCustomerDetails(@PathVariable int customerId) {
		User user = userService.fetchUserByUserId(customerId);
		System.out.println(user);
		if (user != null) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}

	// Create a new account
	@PostMapping("/CreateAccount")
	public ResponseEntity<String> createAccount(@RequestBody Account account) {
		try {
			int result = accountService.createAccount(account);
			String accountNo = account.getAccountNo();

			// Check for account number length
			if (accountNo != null && accountNo.length() > 20) {
				return new ResponseEntity<>("Account number exceeds the allowed length!", HttpStatus.BAD_REQUEST);
			}

			String responseMessage = "Customer account number is: " + accountNo;
			if (result > 0) {
				return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Error Creating Account", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Account creation failed due to an error: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/CreateOtherAccount")
	public ResponseEntity<String> createOtherAccount(@RequestBody Account account) {
		try {
			int result = accountService.createOtherAccount(account);
			String accountNo = account.getAccountNo();

			// Check for account number length
			if (accountNo != null && accountNo.length() > 20) {
				return new ResponseEntity<>("Account number exceeds the allowed length!", HttpStatus.BAD_REQUEST);
			}

			String responseMessage = "Your account number is: " + accountNo;
			if (result > 0) {
				return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Error Creating Account", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>("Account creation failed due to an error: " + e.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	// Update an existing account
	@PutMapping("/update/{accountId}")
	public ResponseEntity<String> updateAccount(@PathVariable int accountId, @RequestBody Account account) {
		account.setAccountId(accountId);
		int result = accountService.updateAccount(account, accountId);
		if (result > 0) {
			return new ResponseEntity<>("Account updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Account update failed. Please try again.", HttpStatus.BAD_REQUEST);
		}
	}

	// Close an account
	@PutMapping("/close/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable int id) {
		int result = accountService.deleteAccount(id);

		// Return the result
		if (result > 0) {
			return new ResponseEntity<>("Account Deleted Successfully!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Account Not Found or Deletion Failed!", HttpStatus.NOT_FOUND);
		}
	}

}
