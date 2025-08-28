package genpact.bank.branch.exception;

public class InvalidBranchDataException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidBranchDataException(String message) {
        super(message);
    }
}