package genpact.bank.fixeddeposit.repository;

import genpact.bank.fixeddeposit.entity.FixedDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FixedDepositRepositoryImpl implements FixedDepositRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(FixedDeposit fixedDeposit) {
        String sql = "INSERT INTO fixed_deposits (account_id, deposit_amount, interest_rate, tenure_months, " +
                     "maturity_amount, status, maturity_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, fixedDeposit.getAccountId(), fixedDeposit.getDepositAmount(),
                fixedDeposit.getInterestRate(), fixedDeposit.getTenureMonths(),
                fixedDeposit.getMaturityAmount(), fixedDeposit.getStatus(), fixedDeposit.getMaturityDate());
    }

    @Override
    public FixedDeposit findById(int fdId) {
        String sql = "SELECT * FROM fixed_deposits WHERE fd_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToFixedDeposit, fdId);
    }

    @Override
    public List<FixedDeposit> findByCustomerId(int customerId) {
        String sql = "SELECT fd.*, a.account_no FROM fixed_deposits fd " +
                     "JOIN accounts a ON fd.account_id = a.account_id " +
                     "WHERE a.account_type='fixed_deposit' AND a.customer_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToFixedDeposits, customerId);
    }
    
    @Override
    public List<FixedDeposit> findByBranchId(int branchId) {
        String sql = "SELECT fd.*, a.account_no FROM fixed_deposits fd \r\n"
        		+ "JOIN accounts a ON fd.account_id = a.account_id \r\n"
        		+ "WHERE a.account_type = 'fixed_deposit' AND a.branch_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToFixedDeposits, branchId);
    }

    @Override
    public List<FixedDeposit> findAll() {
        String sql = "SELECT * FROM fixed_deposits";
        return jdbcTemplate.query(sql, this::mapRowToFixedDeposit);
    }

    @Override
    public int updateFixedDeposit(FixedDeposit fixedDeposit) {
        String sql = "UPDATE fixed_deposits SET account_id = ?, deposit_amount = ?, interest_rate = ?, " +
                     "tenure_months = ?, maturity_amount = ?, status = ?, maturity_date = ? WHERE fd_id = ?";
        return jdbcTemplate.update(sql, fixedDeposit.getAccountId(), fixedDeposit.getDepositAmount(),
                fixedDeposit.getInterestRate(), fixedDeposit.getTenureMonths(),
                fixedDeposit.getMaturityAmount(), fixedDeposit.getStatus(), 
                fixedDeposit.getMaturityDate(), fixedDeposit.getFdId());
    }

    @Override
    public int delete(int fdId) {
        String sql = "DELETE FROM fixed_deposits WHERE fd_id = ?";
        return jdbcTemplate.update(sql, fdId);
    }

    private FixedDeposit mapRowToFixedDeposit(ResultSet rs, int rowNum) throws SQLException {
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setFdId(rs.getInt("fd_id"));
        fixedDeposit.setAccountId(rs.getInt("account_id"));
        fixedDeposit.setDepositAmount(rs.getDouble("deposit_amount"));
        fixedDeposit.setInterestRate(rs.getDouble("interest_rate"));
        fixedDeposit.setTenureMonths(rs.getInt("tenure_months"));
        fixedDeposit.setMaturityAmount(rs.getDouble("maturity_amount"));
        fixedDeposit.setMaturityDate(rs.getDate("maturity_date") != null ? 
            rs.getDate("maturity_date").toLocalDate() : null);
        fixedDeposit.setStatus(rs.getString("status"));
        return fixedDeposit;
    }
    
    private FixedDeposit mapRowToFixedDeposits(ResultSet rs, int rowNum) throws SQLException {
        FixedDeposit fixedDeposit = new FixedDeposit();
        fixedDeposit.setFdId(rs.getInt("fd_id"));
        fixedDeposit.setAccountId(rs.getInt("account_id"));
        fixedDeposit.setDepositAmount(rs.getDouble("deposit_amount"));
        fixedDeposit.setInterestRate(rs.getDouble("interest_rate"));
        fixedDeposit.setTenureMonths(rs.getInt("tenure_months"));
        fixedDeposit.setMaturityAmount(rs.getDouble("maturity_amount"));
        fixedDeposit.setMaturityDate(rs.getDate("maturity_date") != null ? 
            rs.getDate("maturity_date").toLocalDate() : null);
        fixedDeposit.setStatus(rs.getString("status"));
        fixedDeposit.setAccountNo(rs.getString("account_no"));
        return fixedDeposit;
    }
}