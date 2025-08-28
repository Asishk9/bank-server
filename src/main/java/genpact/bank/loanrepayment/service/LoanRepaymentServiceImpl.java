package genpact.bank.loanrepayment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import genpact.bank.loan.service.LoanService;
import genpact.bank.loanrepayment.entity.LoanRepayment;
import genpact.bank.loanrepayment.repository.LoanRepaymentDao;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService {


	@Autowired
	LoanRepaymentDao loanRepaymentDao;

	@Autowired
	LoanService loanService;

   

	// Add loan repayment
	@Override
	public int addLoanRepayment(LoanRepayment loanRepayment) {
		
		int totalRepayments=loanRepaymentDao.getTotalRepaymentsCountOfLoan(loanRepayment.getLoanId());
		
		System.out.println("Repayments count : "+totalRepayments);
		
		loanRepayment.setInstallmentNumber(totalRepayments + 1);

		if (loanRepayment.getAmountPaid() >= loanRepayment.getOutstandingBalance()) {
			loanRepayment.setOutstandingBalance(0);
			loanService.manageStatus(loanRepayment.getLoanId(), "Closed");

		} 
		System.out.println("Loan repayment object in loan repayment service : " + loanRepayment);
		return loanRepaymentDao.insertLoanRepayment(loanRepayment);
	}

	// Get single loan repayment details
	@Override
	public Optional<LoanRepayment> getSingleLoanRepaymentDetails(int repaymentId) {

		return loanRepaymentDao.getSingleLoanRepayment(repaymentId);
	}

	@Override
	public List<Map<String, Object>> fetchAllRepaymentsOfLoan(int loanId) {
		return loanRepaymentDao.getAllRepaymentsOfLoan(loanId);
	}

	@Override
	public Optional<LoanRepayment> fetchNextDueDetails(int loanId) {
		
		return loanRepaymentDao.getRecentRepaymentDetails(loanId);
		
		

	}

	

}
