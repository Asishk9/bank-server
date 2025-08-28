package genpact.bank.loanrepayment.exceptions;

public class LoanRepaymentNotFoundException  extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public LoanRepaymentNotFoundException(String message) {
		super(message);
	}

}
