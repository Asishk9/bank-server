package genpact.bank.user.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import genpact.bank.user.entity.User;

public class UserJoinRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();

		user.setUserId(rs.getInt("user_id"));
		user.setFullName(rs.getString("full_name"));
		user.setGender(rs.getString("gender").charAt(0));
		user.setDateOfBirth(rs.getDate("date_of_birth"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setAddress(rs.getString("address"));
		user.setCityId(rs.getInt("city_id"));
		user.setPincode(rs.getString("pincode"));
		user.setRole(rs.getString("role"));
		user.setBranchId(rs.getInt("branch_id"));
		user.setUsername(rs.getString("username"));
		user.setPassSalt(rs.getString("pass_salt"));
		user.setPassHash(rs.getString("pass_hash"));
		user.setApproved(rs.getBoolean("approved"));
		user.setApproverId(rs.getInt("approver_id"));
		user.setCityName(rs.getString("city_name"));
        user.setStateName(rs.getString("state_name"));
        user.setBranchName(rs.getString("branch_name"));


//		MultipartFile profileImage = Utils.converToMultipart(rs.getBlob("profile_image"));
//		MultipartFile idProof = Utils.converToMultipart(rs.getBlob("id_proof"));
//		MultipartFile addressProof = Utils.converToMultipart(rs.getBlob("address_proof"));
//
//		user.setProfileImage(profileImage);
//		user.setIdProof(idProof);
//		user.setAddressProof(addressProof);

		return user;
	}


}
