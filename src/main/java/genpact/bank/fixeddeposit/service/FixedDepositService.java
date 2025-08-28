package genpact.bank.fixeddeposit.service;

import java.util.List;
import java.util.Map;

import genpact.bank.fixeddeposit.entity.FixedDeposit;
import genpact.bank.user.entity.User;

public interface FixedDepositService {
   // int createFixedDeposit(FixedDeposit fixedDeposit);
    FixedDeposit getFixedDepositById(int fdId);
    List<FixedDeposit> getAllFixedDeposits();
    int updateFixedDeposit(FixedDeposit fixedDeposit);
    List<FixedDeposit> getFixedDepositsByCustomer(int customerId);
    int deleteFixedDeposit(int fdId);
	int createFixedDeposit(FixedDeposit fixedDeposit, int userId);
	List<FixedDeposit> getFdByBranchId(int branchId);
}