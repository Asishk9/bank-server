package genpact.bank.branch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import genpact.bank.branch.entity.Branch;
import genpact.bank.branch.repository.BranchDao;

@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	BranchDao branchDao;
	
	@Override
	public int addBranch(Branch branch) {
		return branchDao.insertBranch(branch);
	}

	@Override
	public Optional<Branch> fetchBranchById(int branchId) {
		return branchDao.getBranchById(branchId);
	}

	@Override
	public List<Branch> fetchAllBranches() {
		
		return branchDao.getAllBranches();
	}

	@Override
	public int modifyBranch(Branch branch, int branchId) {
		return branchDao.updateBranch(branch, branchId);
	}

	@Override
	public int removeBranch(int branchId) {
		return branchDao.deleteBranch(branchId);
	}

	@Override
	public List<Branch> fetchBranchesByRm(int rmId) {
		return branchDao.getBranchByRmId(rmId);
	}

	@Override
	public int updateBmId(int branchId, int bmId) {
	    return branchDao.modifyBmId(branchId, bmId);
	}
}
