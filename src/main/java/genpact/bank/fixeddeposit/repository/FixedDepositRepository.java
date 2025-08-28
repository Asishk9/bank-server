package genpact.bank.fixeddeposit.repository;

import java.util.List;
import java.util.Map;

import genpact.bank.fixeddeposit.entity.FixedDeposit;

public interface FixedDepositRepository {
    int save(FixedDeposit fixedDeposit);  
    FixedDeposit findById(int fdId);      
    List<FixedDeposit> findAll();
    List<FixedDeposit> findByCustomerId(int customerId);
    int updateFixedDeposit(FixedDeposit fixedDeposit); 
    int delete(int fdId);
	List<FixedDeposit> findByBranchId(int branchId);                
}