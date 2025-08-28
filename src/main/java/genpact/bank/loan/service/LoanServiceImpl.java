package genpact.bank.loan.service;


import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.entity.Loan;
import genpact.bank.loan.repository.LoanDao;
import genpact.bank.loan.utils.CreateAccountUtil;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanDao loanDao;
	
	@Autowired
	CreateAccountUtil createAccountUtil;
	
	// Get single loan details
	@Override
	public Optional<Loan> fetchSingleLoan(int loanId) {

		return loanDao.getSingleLoan(loanId);
	}
	
	

	
	// Create a loan
	@Override
	public int createLoan(Loan loan,int branchId) {
		loan.setLoanAccountId(createAccountUtil.createAccount(loan.getCustomerId(),branchId));
		double installmentPerMonth = loan.calculateInstallmentPerMonth();
		loan.setInstallmentPerMonth(installmentPerMonth);
		
			System.out.println("Loan Object  from create service :"+loan);
		return loanDao.insertLoan(loan);

	}

	// Get all loans in a bank
	@Override
	public List<Map<String, Object>> fetchAllLoans() {
		
		return loanDao.getAllLoans();
	}
	
	// Update loan
	@Override
	public int updateLoan(int loanId,Loan loan) {
		
		double installmentPerMonth = loan.calculateInstallmentPerMonth();
		loan.setInstallmentPerMonth(installmentPerMonth);
		return loanDao.updateLoan(loanId,loan);
	}
	
	
	// Get loans in branch
	@Override
	public List<Map<String, Object>> fetchBranchLoans(int branchId) {
		
		return loanDao.getBranchLoans(branchId);
	}
	
	// Fetches all loans of a customer
	@Override
	public List<Map<String, Object>> fetchCustomerLoans(int customerId) {
		return loanDao.getCustomerLoans(customerId);
	}


	// Manage status
	@Override
	public int manageStatus(int loanId,String status) {
		Optional<Loan> optionalLoan=loanDao.getSingleLoan(loanId);
		optionalLoan.get().setStatus(status);
		if(status.equals("Approved")) {
			optionalLoan.get().setApprovedAt(new Date(System.currentTimeMillis()));
			System.out.println("Date from manageStatus : "+new Date(System.currentTimeMillis()));
		}
		
		System.out.println("Loan from manage status : "+optionalLoan.get());
		
		return updateLoan(loanId, optionalLoan.get());
		
	}

	
	@Override
	public Map<String, Object> fetchCustomerDetails(int customerId) {
	    // Use the DAO to get the customer details
	    return loanDao.getCustomerDetails(customerId);

	    
	}




	@Override
	public Optional<Account> getAccountDetails(int loanAccountId) {
		return loanDao.getAccountDetails(loanAccountId);
	}




	@Override
	public Map<String, byte[]> getUserImages(int userId) {
		return loanDao.getUserImages(userId);
	}
	
	

}
