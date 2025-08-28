package genpact.bank.branch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import genpact.bank.branch.entity.Branch;

@Repository
public class BranchDaoImpl implements BranchDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insertBranch(Branch branch) {

		String INSERT_BRANCH = "INSERT INTO branches "
				+ "(branch_name, branch_code, address, city_id, pincode, phone, bm_id, rm_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		return jdbcTemplate.update(INSERT_BRANCH, 
				branch.getBranchName(), 
				branch.getBranchCode(), 
				branch.getAddress(),
				branch.getCityId(), 
				branch.getPincode(), 
				branch.getPhone(), 
				branch.getBmId(), 
				branch.getRmId());
	}
	
	@Override
	public List<Branch> getAllBranches() {
		String SELECT_ALL_BRANCHES = "SELECT * FROM branches";

		return jdbcTemplate.query(SELECT_ALL_BRANCHES, new BranchRowMapper());
	}

	@Override
	public Optional<Branch> getBranchById(int branchId) {
		String SELECT_BRANCH_BY_ID = "SELECT \r\n"
				+ "    b.*,\r\n"
				+ "    c.city_name,\r\n"
				+ "    rm.full_name AS rm_name,\r\n"
				+ "    bm.full_name AS bm_name\r\n"
				+ "FROM \r\n"
				+ "    branches b\r\n"
				+ "JOIN \r\n"
				+ "    city c ON b.city_id = c.city_id\r\n"
	    		+ "JOIN \r\n"
				+ "    users rm ON b.rm_id = rm.user_id\r\n"
				+ "JOIN \r\n"
				+ "    users bm ON b.bm_id = bm.user_id\r\n"
				+ "WHERE \r\n"
				+ "    b.branch_id = ?;";
	
		Branch branch = jdbcTemplate.queryForObject(SELECT_BRANCH_BY_ID, new BranchJoinRowMapper(), branchId);
		return Optional.ofNullable(branch);
	}

	
	@Override
	public int updateBranch(Branch branch, int branchId) {
		String UPDATE_BRANCH = "UPDATE branches SET branch_name = ?, address = ?, phone = ?, bm_id = ? WHERE branch_id = ?";
		return jdbcTemplate.update(UPDATE_BRANCH, 
				branch.getBranchName(), 
				branch.getAddress(), 
				branch.getPhone(), 
				branch.getBmId(), 
				branchId
			);
	}
	

	@Override
	public List<Branch> getBranchByRmId(int rmId) {
		String SELECT_BRANCH_BY_RMID = "SELECT \r\n"
				+ "    b.*,\r\n"
				+ "    c.city_name,\r\n"
				+ "    rm.full_name AS rm_name,\r\n"
				+ "    bm.full_name AS bm_name\r\n"
				+ "FROM \r\n"
				+ "    branches b\r\n"
				+ "JOIN \r\n"
				+ "    city c ON b.city_id = c.city_id\r\n"
				+ "JOIN \r\n"
				+ "    users rm ON b.rm_id = rm.user_id\r\n"
				+ "JOIN \r\n"
				+ "    users bm ON b.bm_id = bm.user_id\r\n"
				+ "WHERE \r\n"
				+ "    b.rm_id = ?;";;
		return jdbcTemplate.query(SELECT_BRANCH_BY_RMID, new BranchJoinRowMapper(), rmId);
	}
	
	public int modifyBmId(int branchId, int bmId) {
	    String UPDATE_BM = "UPDATE branches SET bm_id = ? WHERE branch_id = ?";
	    return jdbcTemplate.update(UPDATE_BM, bmId, branchId); 
	}

	@Override
	public int deleteBranch(int branchId) {
		String DELETE_BRANCH = "DELETE FROM branches WHERE branch_id = ?";
		return jdbcTemplate.update(DELETE_BRANCH, branchId);
	}

}
