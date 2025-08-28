package genpact.bank.user.repository;

import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import genpact.bank.user.entity.User;
import genpact.bank.user.utils.Utils;

@Repository
public class UserDaoImpl implements UserDao{

	    @Autowired
	    private JdbcTemplate jdbcTemplate;

		@Override
		public int insertUser(User user) {
			String INSERT_USER = "INSERT INTO users "
					+ "(full_name, gender, date_of_birth, email, "
					+ "phone, address, city_id, pincode, role, branch_id, "
					+ "profile_image, id_proof, address_proof, "
					+ "username, pass_salt, pass_hash, approved, approver_id) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			Blob blobProfileImage = Utils.convertToBlob(user.getProfileImage());
			Blob blobIdProof = Utils.convertToBlob(user.getIdProof());
			Blob blobAddressProof = Utils.convertToBlob(user.getAddressProof());
			
			 return jdbcTemplate.update(INSERT_USER, 
					 user.getFullName(), 
					 String.valueOf(user.getGender()),
		             user.getDateOfBirth(), 
		             user.getEmail(), 
		             user.getPhone(), 
		             user.getAddress(), 
		             user.getCityId(),
		             user.getPincode(), 
		             user.getRole(), 
		             user.getBranchId(), 
		             blobProfileImage,
		             blobIdProof,
		             blobAddressProof,
		             user.getUsername(), 
		             user.getPassSalt(),
		             user.getPassHash(), 
		             user.isApproved(), 
		             user.getApproverId());

		}

		@Override
		public List<User> getAllUsers() {
		    String SELECT_ALL_USERS = "SELECT * FROM users";
		    return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
		}

		@Override
		public Optional<User> getUserByUsername(String username) {
		    String SELECT_USER_BY_USERNAME = "SELECT \r\n"
		    		+ "    u.*,\r\n"
		    		+ "    c.city_name,\r\n"
		    		+ "    s.state_name,\r\n"
		    		+ "    b.*\r\n"
		    		+ "FROM \r\n"
		    		+ "    users u\r\n"
		    		+ "JOIN \r\n"
		    		+ "    city c ON u.city_id = c.city_id\r\n"
		    		+ "JOIN \r\n"
		    		+ "    state s ON c.state_id = s.state_id\r\n"
		    		+ "JOIN \r\n"
		    		+ "    branches b ON u.branch_id = b.branch_id\r\n"
		    		+ " WHERE username = ?";
		    User user = jdbcTemplate.queryForObject(SELECT_USER_BY_USERNAME, new UserJoinRowMapper(), username);
		    return Optional.ofNullable(user);
		}

		@Override
		public User getUserByUserId(int userId) {
			String SELECT_USER_BY_ID = "SELECT \r\n"
					+ "    u.*,\r\n"
					+ "    c.city_name,\r\n"
					+ "    s.state_name,\r\n"
					+ "    b.branch_name\r\n"
					+ "FROM \r\n"
					+ "    users u\r\n"
					+ "JOIN \r\n"
					+ "    city c ON u.city_id = c.city_id\r\n"
					+ "JOIN \r\n"
					+ "    state s ON c.state_id = s.state_id\r\n"
					+ "JOIN \r\n"
					+ "    branches b ON u.branch_id = b.branch_id\r\n"
					+ "WHERE \r\n"
					+ "    u.user_id = ?;\r\n"
					+ "    ";
		    User user = jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserJoinRowMapper(), userId);
		    return user;
		}
		
		@Override
		public int updateUser(User user, int userId) {
		    String UPDATE_USER = "UPDATE users SET username = ?, full_name = ?, gender = ?, date_of_birth = ?, email = ?, phone = ?, address = ? WHERE user_id = ?";

		    return jdbcTemplate.update(UPDATE_USER,
		    	user.getUsername(),
		        user.getFullName(),
		        String.valueOf(user.getGender()),
		        user.getDateOfBirth(),
		        user.getEmail(),
		        user.getPhone(),
		        user.getAddress(),
		        userId
		    );
		}
		
		@Override
		public int updateImage(int userId, MultipartFile profileImage) {
			String UPDATE_IMAGE = "UPDATE users SET profile_image = ? WHERE user_id = ?";
			Blob blobProfileImage = Utils.convertToBlob(profileImage);
			return jdbcTemplate.update(UPDATE_IMAGE, blobProfileImage, userId);
		}

		@Override
		public int deleteUser(int userId) {
		    String DELETE_USER = "DELETE FROM users WHERE user_id = ?";
		    return jdbcTemplate.update(DELETE_USER, userId);
		}

		@Override
		public List<User> getBankManagers() {
		    String SELECT_USERS_BY_ROLE = "SELECT * FROM users WHERE role = 'Bank Manager'";
		    return jdbcTemplate.query(SELECT_USERS_BY_ROLE, new UserRowMapper());
		}	
		

		@Override
		public List<User> getBankEmployees(String branchId) {
			String GET_BE = "SELECT * FROM USERS where role = 'Bank Employee' and branch_id = ?";
			return jdbcTemplate.query(GET_BE, new UserRowMapper(), branchId);
		}
		

		@Override
		public List<User> getCustomers(String branchId) {
			String GET_CUSTOMER = "SELECT * FROM USERS where role = 'Customer' and branch_id = ?";
			return jdbcTemplate.query(GET_CUSTOMER, new UserRowMapper(), branchId);
		}
		
		
		@Override
		public boolean updateApprovalStatus(int userId, int approverId) {
	        String CHANGE_APPROVAL = "UPDATE users SET approved = !approved, approver_id = ? WHERE user_id = ?";
	        int rowsAffected = jdbcTemplate.update(CHANGE_APPROVAL, approverId, userId);
	        return rowsAffected > 0;
	    }

		@Override
		public int changePassword(User user, String username) {
		    String UPDATE_PASSWORD = "UPDATE users SET pass_salt = ?, pass_hash = ? WHERE username = ?";
		    return jdbcTemplate.update(UPDATE_PASSWORD, user.getPassSalt(), user.getPassHash(), username);
		}

		@SuppressWarnings("deprecation")
		@Override
		public Map<String, byte[]> getUserImages(int userId) {
			  String sql = "SELECT profile_image, id_proof, address_proof FROM users WHERE user_id = ?";
		        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> {
		            Map<String, byte[]> images = new HashMap<>();
		            images.put("profile_image", rs.getBytes("profile_image"));
		            images.put("id_proof", rs.getBytes("id_proof"));
		            images.put("address_proof", rs.getBytes("address_proof"));
		            return images;
		        });
		}

		


}
