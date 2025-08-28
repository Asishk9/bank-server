package genpact.bank.transactions.utils;

import java.time.LocalDateTime;

public class TransactionUtils {

	public static String generateReferenceNumber() {
		return "TXN-" + System.currentTimeMillis();
	}

	public static boolean isValidAmount(double amount) {
		return amount > 0; // Example condition: amount must be greater than zero.
	}

	public static LocalDateTime getCurrentTransactionTime() {
		return LocalDateTime.now();
	}

	public static String formatDescription(String description) {
		if (description == null || description.length() <= 100) {
			return description;
		}
		return description.substring(0, 100) + "...";
	}
}