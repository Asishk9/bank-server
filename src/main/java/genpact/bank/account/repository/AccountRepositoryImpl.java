package genpact.bank.account.repository;

import genpact.bank.account.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Account account) {
        String sql = "INSERT INTO accounts (account_no, customer_id, account_type, branch_id, balance, interest_rate, active) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, account.getAccountNo(), account.getCustomerId(),
                account.getAccountType(), account.getBranchId(),  account.getBalance(),
                account.getInterestRate(), account.isActive());
    }

    @Override
    public Account findById(int accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToAccount, accountId);
    }
	 @Override
	public Account findByAccountNo(String accountNo) {
    	  String sql = "SELECT * FROM accounts WHERE account_no = ?";
    	  return jdbcTemplate.queryForObject(sql, this::mapRowToAccount, accountNo);
	}
    
    @Override
    public List<Account> findByCustomerId(int customerId,String account_type) {
        String sql = "SELECT * FROM accounts WHERE customer_id = ? AND account_type = ?";
        return  jdbcTemplate.query(sql, this::mapRowToAccount, customerId,account_type);
    }

    @Override
	public List<Account> findByCustomerId(int customerId) {
		String sql = "SELECT * FROM accounts WHERE customer_id = ? AND account_type IN ('savings', 'current')";
		return jdbcTemplate.query(sql, this::mapRowToAccount, customerId);
	}
    
    @Override
    public List<Account> findAll() {
        String sql = "SELECT * FROM accounts";
        return jdbcTemplate.query(sql, this::mapRowToAccount);
    }


	@Override
	public List<Account> findAllAccountsInBranch(int branchId) {
		String sql = "SELECT * FROM accounts WHERE branch_id = ?";
		 return jdbcTemplate.query(sql, this::mapRowToAccount, branchId);
	}
	
	
	
    @Override
    public List<Account> getAccountByType(String accountType) {
        String sql = "SELECT * FROM accounts WHERE account_type = ?";
        return jdbcTemplate.query(sql, this::mapRowToAccount, accountType);
    }

    @Override
    public int updateAccount(Account account, int accountId) {
        String sql = "UPDATE accounts SET account_no = ?, customer_id = ?, account_type = ?, balance = ?, interest_rate = ?, active = ? WHERE account_id = ?";
        return jdbcTemplate.update(sql, account.getAccountNo(), account.getCustomerId(),
                account.getAccountType(), 
                account.getBalance(), account.getInterestRate(),
                account.isActive(), accountId);
    }
    @Override
    public int withdraw(int accountId, int amount) {
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        return jdbcTemplate.update(sql, amount, accountId);
    }
    @Override
    public int deposit(int accountId, int amount) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        return jdbcTemplate.update(sql, amount, accountId);
    }
    @Override
    public int send(int accountId, int amount, String accountIdToSend) {
    	 // Check sender's balance
        String checkBalanceSql = "SELECT balance FROM accounts WHERE account_id = ?";
        Integer senderBalance = jdbcTemplate.queryForObject(checkBalanceSql, Integer.class, accountId);
        if (senderBalance == null || senderBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance for account ID: " + accountId);
        }

        // Deduct amount from sender account
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        int rowsAffected1 = jdbcTemplate.update(sql, amount, accountId);

        // Add amount to receiver account
        String sqlSend = "UPDATE accounts SET balance = balance + ? WHERE account_no = ?";
        int rowsAffected2 = jdbcTemplate.update(sqlSend, amount, accountIdToSend);

        return rowsAffected1 + rowsAffected2; // Return total rows updated
    }

    @Override
    public int delete(int accountId) {
        String sql = "UPDATE accounts SET active = 0 WHERE account_id = ?";
        return jdbcTemplate.update(sql, accountId);
    }

    @Override
    public boolean checkAccountExists(String accountNumber) {
        String sql = "SELECT COUNT(*) FROM accounts WHERE account_no = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{accountNumber}, Integer.class);
        return count > 0;
    }

    @Override
    public String findAccountNumberById(int accountId) {
        String sql = "SELECT account_no FROM accounts WHERE account_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{accountId}, String.class);
    }

   
	@Override
	public int getMaxCustomerId() {
		String SELECT_MAX_CUSTOMER_ID = "SELECT MAX(user_id) AS max_id FROM users";
		return jdbcTemplate.queryForObject(SELECT_MAX_CUSTOMER_ID,Integer.class);
	}
    /**
     * Helper method to map a row from the ResultSet to an Account object.
     */
    private Account mapRowToAccount(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setAccountId(rs.getInt("account_id"));
        account.setAccountNo(rs.getString("account_no")); 
        account.setCustomerId(rs.getInt("customer_id"));
        account.setAccountType(rs.getString("account_type"));
        account.setBranchId(rs.getInt("branch_id"));
        account.setBalance(rs.getDouble("balance"));
        account.setInterestRate(rs.getDouble("interest_rate"));
        account.setActive(rs.getBoolean("active"));
        account.setOpenedDate(rs.getDate("created_at"));
        return account;
    }

	

	

}
