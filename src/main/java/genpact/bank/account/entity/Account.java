package genpact.bank.account.entity;

import java.sql.Date;

public class Account {
	private int accountId;
	private String accountNo;
	private int customerId;
	private String accountType;
	private int branchId;
	private double balance;
	private double interestRate;
	private boolean active;
	private Date openedDate;

	public Account() {
		super();

	}

	public Account(int accountId, String accountNo, int customerId, String accountType, int branchId,
			double balance, double interestRate, boolean active) {
		super();
		this.accountId = accountId;
		this.accountNo = accountNo;
		this.customerId = customerId;

		this.accountType = accountType;
		this.branchId = branchId;
		this.balance = balance;
		this.interestRate = interestRate;
		this.active = active;
	}

	public Account(String accountNo, int customerId, String accountType, int branchId, double balance,
			double interestRate, boolean active) {
		super();
		this.accountNo = accountNo;
		this.customerId = customerId;

		this.accountType = accountType;
		this.branchId = branchId;
		this.balance = balance;
		this.interestRate = interestRate;
		this.active = active;
	}

	// Getters and setters for all fields
	public int getAccountId() {
		return accountId;
	}


	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "\n Account [accountId=" + accountId + ", accountNo=" + accountNo + ", customerId=" + customerId
				+ ", customerName=" + ", accountType=" + accountType + ", branchId=" + branchId + ", balance="
				+ balance + ", interestRate=" + interestRate + ", active=" + active + "]";
	}

}
