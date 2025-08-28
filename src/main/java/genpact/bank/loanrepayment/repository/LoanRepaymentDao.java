package genpact.bank.loanrepayment.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import genpact.bank.loanrepayment.entity.LoanRepayment;

public interface LoanRepaymentDao {

	int insertLoanRepayment(LoanRepayment loanRepayment);

	Optional<LoanRepayment> getSingleLoanRepayment(int repaymentId);

	List<Map<String, Object>> getAllRepaymentsOfLoan(int loanId);

	Optional<LoanRepayment> getRecentRepaymentDetails(int loanId);

	int getTotalRepaymentsCountOfLoan(int loanId);

}
