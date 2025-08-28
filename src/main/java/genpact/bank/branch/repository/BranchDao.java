package genpact.bank.branch.repository;

import java.util.List;
import java.util.Optional;

import genpact.bank.branch.entity.Branch;

public interface BranchDao {

	int insertBranch(Branch branch);

	List<Branch> getAllBranches();

	Optional<Branch> getBranchById(int branchId);

	int updateBranch(Branch branch, int branchId);

	int deleteBranch(int BranchId);

	List<Branch> getBranchByRmId(int rmId);

	int modifyBmId(int branchId, int bmId);

}
