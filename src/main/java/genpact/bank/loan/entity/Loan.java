package genpact.bank.loan.entity;


import java.sql.Date;



public class Loan {
	private int loanId;
	private double loanAmount;
	private String status;
	private Date appliedAt;
	private Date approvedAt;
	private int loanAccountId;
	private int loanTenure;
	private double installmentPerMonth;
	private int totalInstallments;
	private double interestRate;
	private int customerId;
	

	// Constructors
	public Loan() {
		super();
	}
	
	public Loan(int loanId, double loanAmount, String status, Date appliedAt,
			Date approvedAt, int loanAccountId, int loanTenure, double installmentPerMonth, int totalInstallments,
			double interestRate,int customerId) {
		this.loanId = loanId;
		this.loanAmount = loanAmount;
		this.status = status;
		this.appliedAt = appliedAt;
		this.approvedAt = approvedAt;
		this.loanAccountId = loanAccountId;
		this.loanTenure = loanTenure;
		this.installmentPerMonth = installmentPerMonth;
		this.totalInstallments = totalInstallments;
		this.interestRate = interestRate;
		this.customerId=customerId;
	}
	public Loan(  double loanAmount, String status, Date appliedAt,
			Date approvedAt, int loanAccountId, int loanTenure, double installmentPerMonth, int totalInstallments,
			double interestRate,int customerId) {

	
		this.loanAmount = loanAmount;
		this.status = status;
		this.appliedAt = appliedAt;
		this.approvedAt = approvedAt;
		this.loanAccountId = loanAccountId;
		this.loanTenure = loanTenure;
		this.installmentPerMonth = installmentPerMonth;
		this.totalInstallments = totalInstallments;
		this.interestRate = interestRate;
		this.customerId=customerId;
		

	}

	// Getters and Setters
	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}


	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAppliedAt() {
		return appliedAt;
	}

	public void setAppliedAt(Date appliedAt) {
		this.appliedAt = appliedAt;
	}

	public Date getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(Date approvedAt) {
		this.approvedAt = approvedAt;
	}

	public int getLoanAcctId() {
		return loanAccountId;
	}

	public void setLaonAccId(int accountId) {
		this.loanAccountId = accountId;
	}

	public int getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}

	public double getInstallmentPerMonth() {
		return installmentPerMonth;
	}

	public void setInstallmentPerMonth(double installmentPerMonth) {
		this.installmentPerMonth = installmentPerMonth;
	}

	public int getTotalInstallments() {
		return totalInstallments;
	}

	public void setTotalInstallments(int totalInstallments) {
		this.totalInstallments = totalInstallments;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double calculateInstallmentPerMonth() {
		return (this.loanAmount/this.loanTenure+this.loanAmount * this.interestRate/(100*12));
	}
	

	public int getLoanAccountId() {
		return loanAccountId;
	}

	public void setLoanAccountId(int loanAccId) {
		this.loanAccountId = loanAccId;
	}
	
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanAmount=" + loanAmount + ", status=" + status
				+ ", appliedAt=" + appliedAt + ", approvedAt=" + approvedAt + ", loanAccountId=" + loanAccountId
				+ ", loanTenure=" + loanTenure + ", installmentPerMonth=" + installmentPerMonth + ", totalInstallments="
				+ totalInstallments + ", interestRate=" + interestRate +"]";
	}

	




}
