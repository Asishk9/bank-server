package genpact.bank.transactions.repositories;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import genpact.bank.transactions.entities.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	private final JdbcTemplate jdbcTemplate;

	public TransactionDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		String sql = "SELECT * FROM transactions";
		return jdbcTemplate.query(sql, new TransactionRowMapper());
	}

	@Override
	public Transaction getTransactionById(Integer transactionId) {
		String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
		return jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), transactionId);
	}

	@Override
	public void addTransaction(Transaction transaction) {
		String sql = "INSERT INTO transactions (account_id, transaction_type, amount, final_balance, status, reference_number, transaction_mode, description) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, transaction.getAccountId(), transaction.getTransactionType(), transaction.getAmount(),
				transaction.getFinalBalance(), transaction.getStatus(), transaction.getReferenceNumber(),
				transaction.getTransactionMode(), transaction.getDescription());
	}

	@Override
	public void updateTransaction(Transaction transaction) {
		String sql = "UPDATE transactions SET account_id = ?, transaction_type = ?, amount = ?, final_balance = ?, status = ?, reference_number = ?, transaction_mode = ?, transaction_time = ?, description = ? "
				+ "WHERE transaction_id = ?";
		jdbcTemplate.update(sql, transaction.getAccountId(), transaction.getTransactionType(), transaction.getAmount(),
				transaction.getFinalBalance(), transaction.getStatus(), transaction.getReferenceNumber(),
				transaction.getTransactionMode(), transaction.getTransactionTime(), transaction.getDescription(),
				transaction.getTransactionId());
	}

	@Override
	public void deleteTransaction(Integer transactionId) {
		String sql = "DELETE FROM transactions WHERE transaction_id = ?";
		jdbcTemplate.update(sql, transactionId);
	}

	@Override
	public Transaction getTransactionByRefId(String referenceNumber) {
		String sql = "SELECT * FROM transactions WHERE reference_number = ?";
		return jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), referenceNumber);
	}

	@Override
	public List<Transaction> getTransactionByAccountId(int accountId) {
		String sql = "SELECT * FROM transactions where account_id = ?";
		return jdbcTemplate.query(sql, new TransactionRowMapper(), accountId);
	}

	@Override
	public List<Transaction> getBranchTransaction(int branchId) {
		String sql = "SELECT t.* from transactions t \r\n"
				+ "join accounts a on a.account_id = t.account_id\r\n"
				+ "where a.branch_id = ?";
		return jdbcTemplate.query(sql, new TransactionRowMapper(), branchId);
	}


}
