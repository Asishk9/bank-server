package genpact.bank.loan.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.entity.Loan;
import static genpact.bank.loan.utils.LoanUtils.*;

@Repository
public class LoanDaoImpl implements LoanDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Optional<Loan> getSingleLoan(int loanId) {

	    try {
	        Loan loan = jdbcTemplate.queryForObject(GET_LOAN, new BeanPropertyRowMapper<>(Loan.class), loanId);
	        System.out.println("Loan details : "+loan);
	        return Optional.ofNullable(loan);
	    } catch (EmptyResultDataAccessException e) {
	        return Optional.empty();
	    }

	}

	@Override
	public int insertLoan(Loan loan) {



		return jdbcTemplate.update(INSERT_LOAN, loan.getLoanAmount(), loan.getStatus(),
				LocalDateTime.now(), loan.getApprovedAt(), loan.getLoanAccountId(), loan.getLoanTenure(),
				loan.getInstallmentPerMonth(), loan.getTotalInstallments(), loan.getInterestRate(),loan.getCustomerId());

	}

	@Override
	public List<Map<String, Object>> getAllLoans() {
		return jdbcTemplate.queryForList(GET_LOANS);
	}

	@Override
	public int updateLoan(int loanId, Loan loan) {

		return jdbcTemplate.update(UPDATE_LOAN, loan.getLoanAmount(), loan.getStatus(), loan.getLoanTenure(),
				loan.getInstallmentPerMonth(), loan.getTotalInstallments(), loan.getInterestRate(),loan.getApprovedAt(),
				loanId);
	}

	@Override
	public List<Map<String, Object>> getBranchLoans(int branchId) {
		return jdbcTemplate.queryForList(GET_BRANCH_LOANS, branchId);
	}

	@Override
	public List<Map<String, Object>> getCustomerLoans(int customerId) {

		return jdbcTemplate.queryForList(GET_CUSTOMER_LOANS, customerId);
	}

	@Override
	public int manageStatus(int loanId, String status) {
		return jdbcTemplate.update(MANAGE_LOAN_STATUS,status,loanId);
	}

	@Override
	public   Map<String,Object> getCustomerDetails(int customerId) {
		
		return  jdbcTemplate.queryForMap(GET_CUSTOMER_DETAILS, customerId);
	}

	@Override
	public boolean checkAccountExists(String accountNumber) {
		
		
		int count = jdbcTemplate.queryForObject(CHECK_ACCOUNT_EXISTS, new Object[]{accountNumber}, Integer.class);
        
		return count> 0;
	}

	@Override
	public int insertAccount(Account account) {
		
		return jdbcTemplate.update(INSERT_ACCOUNT,account.getAccountNo(),account.getCustomerId(),account.getAccountType(),account.getInterestRate(),account.getBranchId());

	}

	@Override
	public int getAccountId(String accountNo) {
		
		
		        Account account = jdbcTemplate.queryForObject(GET_ACCOUNT_ID, new BeanPropertyRowMapper<>(Account.class), accountNo);
		        return account.getAccountId();
	}

	@Override
	public Optional<Account> getAccountDetails(int loanAccountId) {
		
		  try {
		        Account account = jdbcTemplate.queryForObject(GET_ACCOUNT_DETAILS, new BeanPropertyRowMapper<>(Account.class), loanAccountId);
		        System.out.println("Account details : "+account);
		        return Optional.ofNullable(account);
		    } catch (EmptyResultDataAccessException e) {
		        return Optional.empty();
		    }
		
	}

	@Override
	 public Map<String, byte[]> getUserImages(int userId) {
	        String sql = "SELECT profile_image, id_proof, address_proof FROM users WHERE user_id = ?";
	        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
	            Map<String, byte[]> images = new HashMap<>();
	            images.put("profile_image", rs.getBytes("profile_image"));
	            images.put("id_proof", rs.getBytes("id_proof"));
	            images.put("address_proof", rs.getBytes("address_proof"));
	            return images;
	        });
	    }
	 
	 @Override
	 public int[] getBranchManagerAndAccountIds(int  customerId) {
		 String sql = "SELECT b.bm_id, a.account_id " +
	             "FROM users u " +
	             "INNER JOIN branches b ON u.branch_id = b.branch_id " +
	             "INNER JOIN accounts a ON u.user_id = a.customer_id " +
	             "WHERE u.user_id = ? AND a.account_type = 'savings'";

	                     
	        return jdbcTemplate.queryForObject(
	            sql,
	            new Object[]{ customerId },
	            (rs, rowNum) -> {
	                int bmId = rs.getInt("bm_id");
	                int accountId = rs.getInt("account_id");
	                return new int[]{ bmId, accountId };
	            }
	        );
	    }
	}
	


