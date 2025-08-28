package genpact.bank.loanrepayment.utils;

public class LoanRepaymentUtils {
	
	// Get single repayment details
	public static final String GET_SINGLE_LOAN_REPAYMENT="SELECT * FROM loan_repayments WHERE repayment_id = ?";
	
	// Insert a loan repayment
	public static final String INSERT_LOAN_REPAYMENT = "INSERT INTO loan_repayments (loan_id, installment_number, due_date, expected_amount, amount_paid, payment_date, status, penalty, outstanding_balance, created_at, updated_at) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
	// Get all repayments of a loan
	public static final String GET_ALL_REPAYMENTS_OF_LOAN="SELECT * FROM loan_repayments WHERE loan_id=? ORDER BY repayment_id DESC";
	
	// Get recent repayment details
	public static final String GET_RECENT_REPAYMENT_DETAILS="SELECT * FROM loan_repayments WHERE loan_id=? ORDER BY payment_date DESC LIMIT 1";
	
	// Get total count of repayments of a loan
	public static final String GET_REPAYMENTS_COUNT_OF_SINGLE_LOAN="SELECT COUNT(*) FROM loan_repayments WHERE loan_id=?";
			

	
}
