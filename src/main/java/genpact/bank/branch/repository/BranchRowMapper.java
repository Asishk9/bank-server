package genpact.bank.branch.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import genpact.bank.branch.entity.Branch;

public class BranchRowMapper implements RowMapper<Branch> {

	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(rs.getInt("branch_id"));
		branch.setBranchName(rs.getString("branch_name"));
		branch.setBranchCode(rs.getString("branch_code"));
		branch.setAddress(rs.getString("address"));
		branch.setCityId(rs.getInt("city_id"));
		branch.setPincode(rs.getString("pincode"));
		branch.setPhone(rs.getString("phone"));
		branch.setBmId(rs.getInt("bm_id"));
		branch.setRmId(rs.getInt("rm_id"));
		return branch;

	}

}
