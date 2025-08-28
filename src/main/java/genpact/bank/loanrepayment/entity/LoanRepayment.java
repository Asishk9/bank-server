package genpact.bank.loanrepayment.entity;

import java.sql.Date;


public class LoanRepayment {
    private int repaymentId;
    private int loanId;
    private int installmentNumber;
    private Date dueDate;
    private double expectedAmount;
    private double amountPaid;
    private Date paymentDate;
    private String status;
    private double penalty;
    private double outstandingBalance;
    private Date createdAt;
    private Date updatedAt;

    // Default Constructor
    public LoanRepayment() {
    }

    // Parameterized Constructor
    public LoanRepayment(int repaymentId, int loanId, int installmentNumber, Date dueDate, double expectedAmount, double amountPaid, Date paymentDate, String status, double penalty, double outstandingBalance, Date createdAt, Date updatedAt) {
        this.repaymentId = repaymentId;
        this.loanId = loanId;
        this.installmentNumber = installmentNumber;
        this.dueDate = dueDate;
        this.expectedAmount = expectedAmount;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.status = status;
        this.penalty = penalty;
        this.outstandingBalance = outstandingBalance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    // Parameterized Constructor
    public LoanRepayment( int loanId, int installmentNumber, Date dueDate, double expectedAmount, double amountPaid, Date paymentDate, String status, double penalty, double outstandingBalance, Date createdAt, Date updatedAt) {
    	
    	this.loanId = loanId;
    	this.installmentNumber = installmentNumber;
    	this.dueDate = dueDate;
    	this.expectedAmount = expectedAmount;
    	this.amountPaid = amountPaid;
    	this.paymentDate = paymentDate;
    	this.status = status;
    	this.penalty = penalty;
    	this.outstandingBalance = outstandingBalance;
    	this.createdAt = createdAt;
    	this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public int getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(int repaymentId) {
        this.repaymentId = repaymentId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(int installmentNumber) {
        this.installmentNumber = installmentNumber;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getExpectedAmount() {
        return expectedAmount;
    }

    public void setExpectedAmount(double expectedAmount) {
        this.expectedAmount = expectedAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    

	@Override
	public String toString() {
		return "LoanRepayment [repaymentId=" + repaymentId + ", loanId=" + loanId + ", installmentNumber="
				+ installmentNumber + ", dueDate=" + dueDate + ", expectedAmount=" + expectedAmount + ", amountPaid="
				+ amountPaid + ", paymentDate=" + paymentDate + ", status=" + status + ", penalty=" + penalty
				+ ", outstandingBalance=" + outstandingBalance + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
    
}

