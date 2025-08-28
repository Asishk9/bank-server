package genpact.bank.user.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import genpact.bank.user.entity.User;

public interface UserDao {

	int insertUser(User user);

	List<User> getAllUsers();

	Optional<User> getUserByUsername(String username);
	
	User getUserByUserId(int userId);

	int updateUser(User user, int userId);

	int deleteUser(int userId);

	List<User> getBankManagers();

	boolean updateApprovalStatus(int userId, int approverId);

	int changePassword(User user, String username);

	Map<String, byte[]> getUserImages(int userId);

	List<User> getBankEmployees(String branchId);

	List<User> getCustomers(String branchId);

	int updateImage(int userId, MultipartFile profileImage);


}
