package genpact.bank.loanrepayment.repository;

import static genpact.bank.loanrepayment.utils.LoanRepaymentUtils.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import genpact.bank.loanrepayment.entity.LoanRepayment;

@Repository
public class LoanRepaymentDaoImpl implements LoanRepaymentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// Insert loan repayment
	@Override
	public int insertLoanRepayment(LoanRepayment loanRepayment) {

		return jdbcTemplate.update(INSERT_LOAN_REPAYMENT, loanRepayment.getLoanId(),
				loanRepayment.getInstallmentNumber(), (loanRepayment.getDueDate()), loanRepayment.getExpectedAmount(),
				loanRepayment.getAmountPaid(), (loanRepayment.getPaymentDate()), loanRepayment.getStatus(),
				loanRepayment.getPenalty(), loanRepayment.getOutstandingBalance(), LocalDate.now(),
				(loanRepayment.getUpdatedAt()));
	}

	// Get single loan repayment details with repayment id
	@Override
	public Optional<LoanRepayment> getSingleLoanRepayment(int repaymentId) {

		try {
			LoanRepayment loanRepaymentDetails = jdbcTemplate.queryForObject(GET_SINGLE_LOAN_REPAYMENT,
					new BeanPropertyRowMapper<>(LoanRepayment.class), repaymentId);
			return Optional.ofNullable(loanRepaymentDetails);
		} catch (DataAccessException e) {

			return Optional.empty();

		}
	}

	// Get all repayments of a loan with loan id
	@Override
	public List<Map<String, Object>> getAllRepaymentsOfLoan(int loanId) {
		return jdbcTemplate.queryForList(GET_ALL_REPAYMENTS_OF_LOAN, loanId);

	}

	@Override
	public Optional<LoanRepayment> getRecentRepaymentDetails(int loanId) {

		try {

			LoanRepayment loanRepaymentDetails = jdbcTemplate.queryForObject(GET_RECENT_REPAYMENT_DETAILS,
					new BeanPropertyRowMapper<>(LoanRepayment.class), loanId);
			return Optional.ofNullable(loanRepaymentDetails);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return Optional.empty();

		}
	}

	@Override
	public int getTotalRepaymentsCountOfLoan(int loanId) {
		
		return jdbcTemplate.queryForObject(GET_REPAYMENTS_COUNT_OF_SINGLE_LOAN, new Object[]{loanId}, Integer.class);
	}

}
