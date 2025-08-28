package genpact.bank.transactions.repositories;

import java.util.List;

import genpact.bank.transactions.entities.Transaction;

public interface TransactionDao {
	List<Transaction> getAllTransactions();

	Transaction getTransactionById(Integer transactionId);

	void addTransaction(Transaction transaction);

	void updateTransaction(Transaction transaction);

	void deleteTransaction(Integer transactionId);

	Transaction getTransactionByRefId(String referenceNumber);

	List<Transaction> getTransactionByAccountId(int accountId);

	List<Transaction> getBranchTransaction(int branchId);

}
