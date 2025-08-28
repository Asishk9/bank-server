package genpact.bank.loanrepayment.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import genpact.bank.loanrepayment.entity.LoanRepayment;
import genpact.bank.loanrepayment.exceptions.LoanRepaymentCreationException;
import genpact.bank.loanrepayment.exceptions.LoanRepaymentNotFoundException;
import genpact.bank.loanrepayment.service.LoanRepaymentService;

@RestController
@RequestMapping("/api/loanRepayment")
public class LoanRepaymentController {
	@Autowired
	LoanRepaymentService loanRepaymentService;

	// Create Loan repayment
	@PostMapping("/create")
	public ResponseEntity<String> createLoanRepayment(@RequestBody LoanRepayment loanRepayment) {


		int result = loanRepaymentService.addLoanRepayment(loanRepayment);

		if (result <= 0) {

			throw new LoanRepaymentCreationException("Loan creation failed");
		}

		return ResponseEntity.ok("Loan repayment added successfully");

	}

	// Get single loan repayment details
	@GetMapping("/{repaymentId}")
	public ResponseEntity<LoanRepayment> getLoanRepayment(@PathVariable int repaymentId) {
		
		Optional<LoanRepayment> loanRepayment = loanRepaymentService.getSingleLoanRepaymentDetails(repaymentId);
		
		if (loanRepayment.isEmpty()) {
			throw new LoanRepaymentNotFoundException("Loan repayment with id " + repaymentId + " not found");
		}
		
		return ResponseEntity.ok(loanRepayment.get());

	}
	
	// Get all repayments of loan
	@GetMapping("/allLoanRepayments/{loanId}")
	public ResponseEntity<List<Map<String,Object>>> getAllRepaymentsOfLoan(@PathVariable int loanId){
		
		List<Map<String, Object>> loanList=loanRepaymentService.fetchAllRepaymentsOfLoan(loanId);
		
		
		
		return ResponseEntity.ok(loanList);
		
		
	}
	
	// Get recent repayment details
	
	@GetMapping("/recentRepaymentDetails/{loanId}")
	public ResponseEntity<LoanRepayment> getNextDueDetails(@PathVariable int loanId){
		Optional<LoanRepayment> optionalRecentRepayment=loanRepaymentService.fetchNextDueDetails(loanId);
		if(optionalRecentRepayment.isEmpty()) {
			throw new LoanRepaymentNotFoundException("No loan repayment found for the loan with the id "+loanId);
		}
		return ResponseEntity.ok(optionalRecentRepayment.get());
	}

}
