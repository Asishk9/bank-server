package genpact.bank.transactions.entities;

import java.time.LocalDateTime;

public class Transaction {

	private Integer transactionId;
	private Integer accountId;
	private String transactionType;
	private double amount;
	private double finalBalance;
	private String status;
	private String referenceNumber;
	private String transactionMode;
	private LocalDateTime transactionTime;
	private String description;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(Integer accountId, String transactionType, double amount, double finalBalance, String status,
			String referenceNumber, String transactionMode, LocalDateTime transactionTime, String description) {
		super();
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.finalBalance = finalBalance;
		this.status = status;
		this.referenceNumber = referenceNumber;
		this.transactionMode = transactionMode;
		this.transactionTime = transactionTime;
		this.description = description;
	}

	public Transaction(Integer transactionId, Integer accountId, String transactionType, double amount,
			double finalBalance, String status, String referenceNumber, String transactionMode,
			LocalDateTime transactionTime, String description) {
		super();
		this.transactionId = transactionId;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.amount = amount;
		this.finalBalance = finalBalance;
		this.status = status;
		this.referenceNumber = referenceNumber;
		this.transactionMode = transactionMode;
		this.transactionTime = transactionTime;
		this.description = description;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(double finalBalance) {
		this.finalBalance = finalBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", accountId=" + accountId + ", transactionType="
				+ transactionType + ", amount=" + amount + ", finalBalance=" + finalBalance + ", status=" + status
				+ ", referenceNumber=" + referenceNumber + ", transactionMode=" + transactionMode + ", transactionTime="
				+ transactionTime + ", description=" + description + "]";
	}

}
