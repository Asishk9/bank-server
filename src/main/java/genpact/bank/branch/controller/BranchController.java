package genpact.bank.branch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import genpact.bank.branch.entity.Branch;
import genpact.bank.branch.service.BranchService;

@RestController
@RequestMapping("api/branch")
public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping("/addBranch")
    public ResponseEntity<String> addBranch(@ModelAttribute Branch branch) {
        try {
            int result = branchService.addBranch(branch);
            if (result > 0) {
                return ResponseEntity.ok("Branch added successfully.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add branch.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured Please try again");
        }
    }

    @GetMapping("/details/{branchId}")
    public ResponseEntity<Object> getBranchById(@PathVariable int branchId) {
    	try {
        Optional<Branch> branch = branchService.fetchBranchById(branchId);
        if (branch.isPresent()) {
            return ResponseEntity.ok(branch.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found.");
    	} catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured Please try again");
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllBranches() {
    	 try {
        List<Branch> branches = branchService.fetchAllBranches();
        if(!branches.isEmpty()) {
        	  return ResponseEntity.ok(branches);
        } else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Branches Found");
        }
      
    	 } catch (Exception e) {
    	      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured Please try again");
         }
    }


    @PutMapping("/update/{branchId}")
    public ResponseEntity<String> updateBranch(@RequestBody Branch branch, @PathVariable int branchId) {
        try {
            int result = branchService.modifyBranch(branch, branchId);
            if (result > 0) {
                return ResponseEntity.ok("Branch updated successfully.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update branch or branch not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured Please try again");
        }
    }
    
    @GetMapping("/byRmId/{rmId}")
    public ResponseEntity<Object> getBranchesForRm(@PathVariable int rmId) {
    	  try {
        List<Branch> branches = branchService.fetchBranchesByRm(rmId);
        if (!branches.isEmpty()) {
            return ResponseEntity.ok(branches);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regional Manager with id : " + rmId + " not found");
    	  } catch (Exception e) {
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured Please try again");
          }
    }
    
    @PutMapping("/updateBmId/{branchId}")
    public ResponseEntity<String> updateBmId(
            @PathVariable int branchId,
            @ModelAttribute("bmId") int bmId) {
        try {
            // Call service to update the bmId
            int result = branchService.updateBmId(branchId, bmId);
            if (result > 0) {
                return ResponseEntity.ok("Branch Manager ID updated successfully.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occured Please try again");
        }
    }
    
    @DeleteMapping("/delete/{branchId}")
    public ResponseEntity<String> deleteBranch(@PathVariable int branchId) {
        try {
            int result = branchService.removeBranch(branchId);
            if (result > 0) {
                return ResponseEntity.ok("Branch deleted successfully.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branch not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

