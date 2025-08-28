package genpact.bank.account.repository;

import java.util.List;

import genpact.bank.account.entity.Account;

public interface AccountRepository {
    int save(Account account); 
    Account findById(int accountId); 
	 Account findByAccountNo(String accountNo);
    List<Account> findAll(); 
    int updateAccount(Account account, int accountId);
    int withdraw(int accountId, int amount);
    int deposit(int accountId, int amount);
    int send(int accountId,int amount,String accountIdToSend);
    int delete(int accountId); 
    List<Account> getAccountByType(String accountType);
    boolean checkAccountExists(String accountNumber);
    String findAccountNumberById(int accountId);
    List<Account> findByCustomerId(int customerId,String account_type);
	int getMaxCustomerId();
	List<Account> findAllAccountsInBranch(int branchId);
	List<Account> findByCustomerId(int customerId);
}
