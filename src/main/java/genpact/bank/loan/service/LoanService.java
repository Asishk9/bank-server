package genpact.bank.loan.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.entity.Loan;

public interface LoanService {
	public Optional<Loan> fetchSingleLoan(int loanId);

	public int createLoan(Loan loan, int customerId);

	public List<Map<String, Object>> fetchAllLoans();

	int updateLoan(int loanId, Loan loan);

	public List<Map<String, Object>> fetchBranchLoans(int branchId);

	public List<Map<String, Object>> fetchCustomerLoans(int deductionAccountId);

	public int manageStatus(int loanId,String status);

	public Map<String, Object> fetchCustomerDetails(int savingsAccntId);

	public Optional<Account> getAccountDetails(int loanAccountId);

	public Map<String, byte[]> getUserImages(int userId); 

}
