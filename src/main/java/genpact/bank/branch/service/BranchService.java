package genpact.bank.branch.service;

import java.util.List;
import java.util.Optional;

import genpact.bank.branch.entity.Branch;

public interface BranchService {

	int addBranch(Branch branch);

	Optional<Branch> fetchBranchById(int branchId);

	List<Branch> fetchAllBranches();

	int modifyBranch(Branch branch, int branchId);

	int removeBranch(int branchId);

	List<Branch> fetchBranchesByRm(int rmId);

	int updateBmId(int branchId, int bmId);

}
