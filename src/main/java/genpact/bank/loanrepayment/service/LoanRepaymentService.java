package genpact.bank.loanrepayment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import genpact.bank.loanrepayment.entity.LoanRepayment;

public interface LoanRepaymentService {

	int addLoanRepayment(LoanRepayment loanRepayment);

	Optional<LoanRepayment> getSingleLoanRepaymentDetails(int repaymentId);

	List<Map<String, Object>> fetchAllRepaymentsOfLoan(int loanId);

	Optional<LoanRepayment> fetchNextDueDetails(int loanId);
	
	

}
