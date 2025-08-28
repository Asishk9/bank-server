package genpact.bank.fixeddeposit.controller;

import genpact.bank.fixeddeposit.entity.FixedDeposit;
import genpact.bank.fixeddeposit.service.FixedDepositService;
import genpact.bank.user.entity.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fixedDeposits")
public class FixedDepositController {

    @Autowired
    private FixedDepositService fixedDepositService;

    @GetMapping
    public ResponseEntity<?> getAllFixedDeposits() {
        List<FixedDeposit> fixedDepositList = fixedDepositService.getAllFixedDeposits();
        return new ResponseEntity<>(fixedDepositList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewFixedDeposit(@PathVariable int id) {
        FixedDeposit fixedDeposit = fixedDepositService.getFixedDepositById(id);
        if (fixedDeposit == null) {
            return new ResponseEntity<>("Fixed Deposit not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fixedDeposit, HttpStatus.OK);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<?> viewFixedDepositCustomer(@PathVariable int id) {
    	List<FixedDeposit> fixedDepositList = fixedDepositService.getFixedDepositsByCustomer(id);
    	 return new ResponseEntity<>(fixedDepositList, HttpStatus.OK);
    }
    
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<?> viewFixedDepositBranch(@PathVariable int branchId) {
    	List<FixedDeposit> fixedDepositList = fixedDepositService.getFdByBranchId(branchId);
    	 return new ResponseEntity<>(fixedDepositList, HttpStatus.OK);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<String> addFixedDeposit(@RequestBody FixedDeposit fixedDeposit, @PathVariable int userId) {
        int result = fixedDepositService.createFixedDeposit(fixedDeposit, userId);
        if (result > 0) {
            return new ResponseEntity<>("Fixed Deposit Created Successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Fixed Deposit Creation Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{fdId}")
    public ResponseEntity<String> updateFixedDeposit(@PathVariable int fdId, @RequestBody FixedDeposit fixedDeposit) {
        fixedDeposit.setFdId(fdId);
        int result = fixedDepositService.updateFixedDeposit(fixedDeposit);
        if (result > 0) {
            return new ResponseEntity<>("Fixed Deposit updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fixed Deposit update failed. Please try again.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFixedDeposit(@PathVariable int id) {
        int result = fixedDepositService.deleteFixedDeposit(id);
        if (result > 0) {
            return new ResponseEntity<>("Fixed Deposit Deleted Successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fixed Deposit Not Found or Deletion Failed!", HttpStatus.NOT_FOUND);
        }
    }
}