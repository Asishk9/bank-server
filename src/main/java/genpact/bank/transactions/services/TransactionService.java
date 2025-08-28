package genpact.bank.transactions.services;

import java.util.List;

import genpact.bank.transactions.entities.Transaction;

public interface TransactionService {
	List<Transaction> getAllTransactions();

	Transaction getTransactionById(Integer transactionId);

	void addTransaction(Transaction transaction);

	void updateTransaction(Transaction transaction);

	void deleteTransaction(Integer transactionId);

	Transaction getTransactionByRef(String referenceNumber);

	List<Transaction> getTransactionsByAccountId(int accountId);

	List<Transaction> getBranchTransactions(int branchId);
}
