package genpact.bank.loan.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.entity.Loan;
import genpact.bank.loan.exceptions.LoanCreationException;
import genpact.bank.loan.exceptions.LoanNotFoundException;
import genpact.bank.loan.exceptions.LoanUpdateException;
import genpact.bank.loan.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
	
	@Autowired
	LoanService loanService;
	
	 @CrossOrigin(origins = "http://localhost:3000")
	 
	// Get single loan details with loan id
	@GetMapping("/{loanId}")
	public ResponseEntity<Object> getLoan(@PathVariable int loanId) {
		
		Optional<Loan> optionalLoan = loanService.fetchSingleLoan(loanId);

		if (optionalLoan.isEmpty()) {
			throw new LoanNotFoundException(" Loan with id " + loanId + " not found");
		}

		return ResponseEntity.ok(optionalLoan.get());
	}

	// Create a loan
	@PostMapping("/create/{branchId}")
	public ResponseEntity<String> createLoan(@PathVariable int branchId,@RequestBody Loan loan) {

		System.out.println("Loan object from UI : " + loan);

		int result = loanService.createLoan(loan,branchId);

		if (result <= 0) {
			throw new LoanCreationException("Loan creation failed");

		}
		return ResponseEntity.ok("Loan created succesfully");

	}

	// Update the loan with loan id
	@PutMapping("/updateLoan/{loanId}")
	public ResponseEntity<String> updateLoan(@RequestBody Loan loan, @PathVariable int loanId)

	{

		Optional<Loan> optionalLoan = loanService.fetchSingleLoan(loanId);
		System.out.println(optionalLoan.get());

		if (optionalLoan.isEmpty()) {
			throw new LoanNotFoundException("\n Loan with id " + loanId + " not found");
		}

		int result = loanService.updateLoan(loanId, loan);

		if (result <= 0) {
			throw new LoanUpdateException("Loan update failed");

		}
		return ResponseEntity.ok("Loan updated successfully");

	}

	// Get all loans in a bank
	@GetMapping("/getAllLoans")
	public ResponseEntity<List<Map<String, Object>>> fetchAllLoans() {

		List<Map<String, Object>> loanList = loanService.fetchAllLoans();

		if (loanList.size() == 0) {
			throw new LoanNotFoundException("No loans found ");
		}
		return ResponseEntity.ok(loanList);
	}

	// Get all loans in a branch
	@GetMapping("/getBranchLoans/{branchId}")
	public ResponseEntity<List<Map<String, Object>>> getBranchLoans(@PathVariable int branchId) {

		List<Map<String, Object>> loanList = loanService.fetchBranchLoans(branchId);

		if (loanList.size() == 0) {
			throw new LoanNotFoundException("\n No loans found in the branch");
		}

		return ResponseEntity.ok(loanList);
	}

	// get all loans of a customer
	@GetMapping("/getCustomerLoans/{customerId}")
	public ResponseEntity<List<Map<String, Object>>> getCustomerLoans(@PathVariable int customerId) {

		List<Map<String, Object>> loanList = loanService.fetchCustomerLoans(customerId);

		if (loanList.size() == 0) {

			throw new LoanNotFoundException("Customer laons not found");
		}
		return ResponseEntity.ok(loanList);
	}

	// Manage Loan Status
	@PutMapping("/manageStatus/{loanId}")
	public ResponseEntity<String> manageStatus(@PathVariable int loanId, @RequestParam String status) {

		System.out.println("Status from UI : " + status);
		Optional<Loan> optionalLoan = loanService.fetchSingleLoan(loanId);

		if (optionalLoan.isEmpty()) {
			throw new LoanNotFoundException("Loan with id " + loanId + " not found");

		}

		int result = loanService.manageStatus(optionalLoan.get().getLoanId(), status);
		if (result <= 0) {
			throw new LoanUpdateException("\n Manage loan status failed");

		}

		return ResponseEntity.ok("Loan status updated");

	}
	
	// Get customer details using savings account id
	@GetMapping("/getCustomerDetails/{customerId}")
	public ResponseEntity<Map<String, Object>> getCustomerDetails(@PathVariable int customerId) {
	    Map<String, Object> customerDetails = loanService.fetchCustomerDetails(customerId);

	    if (customerDetails != null && !customerDetails.isEmpty()) {
	        System.out.println("Customer details: " + customerDetails);
	        return ResponseEntity.ok(customerDetails);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	}
	
	
	// Get Account details
	@GetMapping("/getAccountDetails/{loanAccountId}")
	public ResponseEntity<Account> getAccountDetails(@PathVariable int loanAccountId){
		Optional<Account> optionalAccount=loanService.getAccountDetails( loanAccountId);
		if(!optionalAccount.isEmpty()) {
			return ResponseEntity.ok(optionalAccount.get());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@GetMapping("getUserImages/{userId}")
    public ResponseEntity<Map<String, byte[]>> getImage(@PathVariable int userId) {
        Map<String,byte[]> images = loanService.getUserImages(userId);
        return ResponseEntity.ok(images);
    }

}
