package genpact.bank.loan.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.repository.LoanDao;

@Component
public class CreateAccountUtil {
		
	@Autowired
	LoanDao loanDao;
	
	  public String generateUniqueAccountNumber() {
	        Random random = new Random();
	        String accountNumber;
	        boolean checkExists;

	        do {
	            accountNumber = String.format("%010d", random.nextInt(1_000_000_000));
	
	             checkExists = loanDao.checkAccountExists(accountNumber);
	            
	        } while (checkExists);

	        return accountNumber;
	    }

	
	
	public int createAccount(int customerId,int branchId) {
		
		Account account=new Account();
		
		account.setAccountType("loan");
		account.setCustomerId(customerId);
		account.setBranchId(branchId);
		account.setInterestRate(0);
		String accountNo=generateUniqueAccountNumber();
		account.setAccountNo(accountNo);
		 loanDao.insertAccount(account);
		 
		 return loanDao.getAccountId(accountNo);
		
		
		
	}

}
