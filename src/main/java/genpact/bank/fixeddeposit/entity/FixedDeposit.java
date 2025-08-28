package genpact.bank.fixeddeposit.entity;

import java.time.LocalDate;

public class FixedDeposit {
    private int fdId;
    private int accountId;
    private double depositAmount;
    private double interestRate;
    private int tenureMonths;
    private double maturityAmount;
    private String status;
  //  private int customerId;
    private LocalDate maturityDate;
    private String accountNo;
    
    public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public LocalDate getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(LocalDate maturityDate) {
		this.maturityDate = maturityDate;
	}
	// Getters and Setters
    public int getFdId() {
        return fdId;
    }
    public void setFdId(int fdId) {
        this.fdId = fdId;
    }
//    public int getCustomerId() {
//    	return customerId;
//    }
//    public void setCustomerId(int customerId) {
//    	this.customerId=customerId;
//    }
    public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public double getDepositAmount() {
        return depositAmount;
    }
    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
        calculateMaturityAmount();
    }
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        calculateMaturityAmount();
    }
    public int getTenureMonths() {
        return tenureMonths;
    }
    public void setTenureMonths(int tenureMonths) {
        this.tenureMonths = tenureMonths;
        calculateMaturityAmount();
    }
    
    public void calculateMaturityAmount() {
        // Formula: Maturity Amount = Deposit Amount * (1 + Interest Rate * Tenure in Years)
        double tenureInYears = tenureMonths / 12.0;
        this.maturityAmount = depositAmount * (1 + (interestRate / 100) * tenureInYears);
        
    }

    public double getMaturityAmount() {
    	
        return maturityAmount;
        
    }
   
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FixedDeposit{" +
                "fdId=" + fdId +
                ", accountId=" + accountId +
                ", depositAmount=" + depositAmount +
                ", interestRate=" + interestRate +
                ", tenureMonths=" + tenureMonths +
                ", maturityAmount=" + maturityAmount +
                ", status='" + status + '\'' +
                '}';
    }
    public void setMaturityAmount(double maturityAmount) {
        this.maturityAmount = maturityAmount; 
    }
}