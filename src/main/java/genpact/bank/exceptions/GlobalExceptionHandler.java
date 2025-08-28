package genpact.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import genpact.bank.loan.exceptions.LoanCreationException;
import genpact.bank.loan.exceptions.LoanNotFoundException;
import genpact.bank.loan.exceptions.LoanUpdateException;
import genpact.bank.loanrepayment.exceptions.LoanRepaymentCreationException;
import genpact.bank.loanrepayment.exceptions.LoanRepaymentNotFoundException;
import genpact.bank.user.exception.ApprovalPendingException;
import genpact.bank.user.exception.DataValidationException;
import genpact.bank.user.exception.DuplicateEntryException;
import genpact.bank.user.exception.FileProcessingException;
import genpact.bank.user.exception.InvalidCredentialsException;
import genpact.bank.user.exception.UnauthorizedAccessException;
import genpact.bank.user.exception.UserNotFoundException;
import genpact.bank.user.exception.UserRegistrationException;
import genpact.bank.branch.exception.BranchNotFoundException;
import genpact.bank.branch.exception.InvalidBranchDataException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// Branch exceptions
	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<String> handleBranchNotFoundException(BranchNotFoundException ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidBranchDataException.class)
	public ResponseEntity<String> handleInvalidBranchDataException(InvalidBranchDataException ex) {
	    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	// User exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(DuplicateEntryException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<String> handleDataValidationException(DataValidationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<String> handleFileProcessingException(FileProcessingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<String> handleUserRegistrationException(UserRegistrationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApprovalPendingException.class)
    public ResponseEntity<String> handleApprovalPendingException(ApprovalPendingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<String> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
	
	// Loan not found exception
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(LoanNotFoundException ex){
		return new ResponseEntity<>("\n Error : "+ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	// Loan Creation exception
	@ExceptionHandler(LoanCreationException.class)
	public ResponseEntity<String> handleLoanNotFoundException(LoanCreationException ex){
		return new ResponseEntity<>("\n Error :  "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	// Loan Update exception
	@ExceptionHandler(LoanUpdateException.class)
	public ResponseEntity<String> handleLoanUpdateException(LoanUpdateException ex){
		return new ResponseEntity<>("\n Error :  "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	// Loan Repayment Not found exception
	@ExceptionHandler(LoanRepaymentNotFoundException.class)
	public ResponseEntity<String> handleLoanRepaymentNotFoundException(LoanRepaymentNotFoundException ex){
		return new ResponseEntity<>("\n Error : "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	// Loan Repayment Creation exception
	@ExceptionHandler(LoanRepaymentCreationException.class)
	public ResponseEntity<String> handleLoanRepaymentCreationException(LoanRepaymentCreationException ex){
		return new ResponseEntity<>("\n Error : "+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	


}
