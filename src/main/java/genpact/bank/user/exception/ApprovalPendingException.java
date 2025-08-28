package genpact.bank.user.exception;

public class ApprovalPendingException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApprovalPendingException(String message) {
        super(message);
    }
}