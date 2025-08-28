package genpact.bank.loan.utils;

public class LoanUtils {

	// CRUD OPERATION QUERIES

	// Get loan details with the loan id
	public final static String GET_LOAN = "SELECT l.*,a.account_no FROM loans l JOIN accounts a ON l.loan_account_id=a.account_id WHERE loan_id= ?";

	// Insert a loan
	public final static String INSERT_LOAN = "INSERT INTO loans (  loan_amount, status, applied_at, approved_at,loan_account_id, loan_tenure, "
			+ "installment_per_month, total_installments, interest_rate,customer_id) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	// Update a loan
	public final static String UPDATE_LOAN = "UPDATE loans SET loan_amount = ?, status = ?, loan_tenure = ?, installment_per_month = ?, total_installments = ?, interest_rate = ?, approved_at=? WHERE loan_id = ?";

	// Get all loans
	public static final String GET_LOANS = "SELECT * FROM loans";

	// Get loans of a branch
	public static final String GET_BRANCH_LOANS = "SELECT l.* FROM loans l\r\n"
			+ "JOIN accounts a\r\n"
			+ "ON a.account_id=l.loan_account_id\r\n"
			+ "WHERE a.account_type='loan' AND a.branch_id=? ORDER BY l.applied_at DESC";

	// Get all loans of a customer
	public static final String GET_CUSTOMER_LOANS = "SELECT * FROM loans where customer_id = ? ORDER BY loan_id DESC";
	
	// Manage loan status
	public static final String MANAGE_LOAN_STATUS="UPDATE loans SET status = ? WHERE loan_id= ? ";
	
	// Get customer details
	public static final String GET_CUSTOMER_DETAILS="SELECT * FROM users WHERE user_id=?";
   
	// Insert account
	public static final String INSERT_ACCOUNT="INSERT INTO accounts(account_no,customer_id,account_type,interest_rate,branch_id) VALUES(?,?,?,?,?)";
	// Check account exists
	public static final String CHECK_ACCOUNT_EXISTS="SELECT COUNT(*) FROM accounts WHERE account_no = ?";
	
	// Get account id
	public static final String GET_ACCOUNT_ID="SELECT account_id FROM accounts WHERE account_no=? ";
	
	// Get account details
	public static final String GET_ACCOUNT_DETAILS="SELECT * FROM accounts WHERE account_id = ?";
}
