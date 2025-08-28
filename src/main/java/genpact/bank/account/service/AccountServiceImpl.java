package genpact.bank.account.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genpact.bank.account.repository.AccountRepository;
import genpact.bank.account.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    // Constructor-based injection
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    // Generate a unique account number
    public String generateUniqueAccountNumber() {
        Random random = new Random();
        String accountNumber;
        boolean checkExists;

        do {
            accountNumber = String.format("%010d", random.nextInt(1_000_000_000));
            checkExists = repository.checkAccountExists(accountNumber); 
        } while (checkExists);

        return accountNumber;
    }

    // Create a new account
    @Override
    public int createAccount(Account account) {
        String accountNumber = generateUniqueAccountNumber();
        account.setAccountNo(accountNumber);
        int customerId = getMaxCustomerId();
        account.setCustomerId(customerId);
        return repository.save(account);
    }
    
    @Override
	public int createOtherAccount(Account account) {
    	  String accountNumber = generateUniqueAccountNumber();
          account.setAccountNo(accountNumber);
          return repository.save(account);
	}

    // Fetch maximum customer ID
    public int getMaxCustomerId() {
        return repository.getMaxCustomerId();
    }

    // Fetch account by ID
    @Override
    public Account getAccountById(int accountId) {
        return repository.findById(accountId);
    }

	@Override
	public Account getAccountByAccountNo(String accountNo) {
		return repository.findByAccountNo(accountNo);
	}

    // Fetch accounts by customer ID
    @Override
    public List<Account> getAccountByCustomerId(int id) {
        return repository.findByCustomerId(id);
    }

    // Fetch all accounts
    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    // Update an account
    @Override
    public int updateAccount(Account account, int accountId) {
        return repository.updateAccount(account, accountId);
    }

    // Withdraw amount from an account
    @Override
    public int withdraw(int accountId, int amount) {
        return repository.withdraw(accountId, amount);
    }

    // Deposit amount into an account
    @Override
    public int deposit(int accountId, int amount) {
        return repository.deposit(accountId, amount);
    }

    // Delete an account
    @Override
    public int deleteAccount(int accountId) {
        return repository.delete(accountId);
    }

	@Override
	public int send(int accountId, int amount, String accountIdToSend) {
		return repository.send(accountId, amount, accountIdToSend);
	}

	@Override
	public List<Account> findAccountsByType(String accountType) {
		return repository.getAccountByType(accountType);
	}

	@Override
	public List<Account> getAllAccountsInBranch(int branchId) {
		return repository.findAllAccountsInBranch(branchId);
	}

	

}
