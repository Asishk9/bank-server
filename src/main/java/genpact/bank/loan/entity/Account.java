package genpact.bank.loan.entity;

public class Account {
		private int accountId;
		private String accountNo;
		private int customerId;
		private String accountType;
		private double balance;
		private double interestRate;
		private boolean active;
		private int branchId;
	 
		public Account() {
			super();
	 
		}
	 
		public Account(int accountId, String accountNo, int customerId, String accountType,
				double balance, double interestRate, boolean active,int branchId) {
			super();
			this.accountId = accountId;
			this.accountNo = accountNo;
			this.customerId = customerId;
			this.accountType = accountType;
			this.balance = balance;
			this.interestRate = interestRate;
			this.active = active;
			this.branchId=branchId;
		}
	 
		public Account(String accountNo, int customerId, String accountType, double balance,
				double interestRate, boolean active,int branchId) {
			super();
			this.accountNo = accountNo;
			this.customerId = customerId;
			this.accountType = accountType;
			this.balance = balance;
			this.interestRate = interestRate;
			this.active = active;
			this.branchId=branchId;
		}
	 
		// Getters and setters for all fields
		public int getAccountId() {
			return accountId;
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
		
		
		public int getBranchId() {
			return branchId;
		}

		public void setBranchId(int branchId) {
			this.branchId = branchId;
		}

		@Override
		public String toString() {
			return "\n Account [accountId=" + accountId + ", accountNo=" + accountNo + ", customerId=" + customerId
					+ ", customerName=" + ", accountType=" + accountType + ", balance="
					+ balance + ", interestRate=" + interestRate + ", active=" + active + "]";
		}

	 

}
