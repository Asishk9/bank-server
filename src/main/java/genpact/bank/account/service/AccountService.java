package genpact.bank.account.service;

import java.util.List;
import genpact.bank.account.entity.Account;

public interface AccountService {
    int createAccount(Account account); 
    Account getAccountById(int accountId);
Account getAccountByAccountNo(String accountNo);
    List<Account> getAllAccounts();
    int updateAccount(Account account, int accountId);
    int withdraw(int accountId, int amount);
    int deposit(int accountId, int amount);
    int send(int accountId, int amount, String accountIdToSend);
    int deleteAccount(int accountId);
    List<Account> findAccountsByType(String accountType);
	List<Account> getAllAccountsInBranch(int branchId);
	List<Account> getAccountByCustomerId(int id);
	int createOtherAccount(Account account);
}
