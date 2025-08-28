package genpact.bank.transactions.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import genpact.bank.transactions.entities.Transaction;

public class TransactionRowMapper implements RowMapper<Transaction> {

	@Override
	public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(rs.getInt("transaction_id"));
		transaction.setAccountId(rs.getInt("account_id"));
		transaction.setTransactionType(rs.getString("transaction_type"));
		transaction.setAmount(rs.getDouble("amount"));
		transaction.setFinalBalance(rs.getDouble("final_balance"));
		transaction.setStatus(rs.getString("status"));
		transaction.setReferenceNumber(rs.getString("reference_number"));
		transaction.setTransactionMode(rs.getString("transaction_mode"));
		transaction.setTransactionTime(rs.getObject("transaction_time", LocalDateTime.class));
		transaction.setDescription(rs.getString("description"));
		return transaction;
	}

}
