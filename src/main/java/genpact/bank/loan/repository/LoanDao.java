package genpact.bank.loan.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.entity.Loan;

public interface LoanDao {

	public Optional<Loan> getSingleLoan(int loanId);

	public int insertLoan(Loan loan);

	public List<Map<String, Object>> getAllLoans();

	int updateLoan(int loanId, Loan loan);

	public List<Map<String, Object>> getBranchLoans(int branchId);

	public List<Map<String, Object>> getCustomerLoans(int deductionAccountId);

	public int manageStatus(int loanId, String status);

	 public Map<String, Object> getCustomerDetails(int customerId);

	public boolean checkAccountExists(String accountNumber);

	public int insertAccount(Account account);

	public int getAccountId(String accountNo);

	public Optional<Account> getAccountDetails(int loanAccountId);

	Map<String, byte[]> getUserImages(int userId);

	int[] getBranchManagerAndAccountIds(int customerId);

}
