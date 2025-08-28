package genpact.bank.user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import genpact.bank.user.entity.User;

public interface UserService {
	
	int registerUser(User user);
    List<User> fetchAllUsers();
    Optional<User> fetchUserByUsername(String username);
    User fetchUserByUserId(int userId);
    int modifyUser(User user, int userId);
    int removeUser(int userId);
    List<User> fetchBankManagers();
	boolean matchPasswords(String passwordFromUI, String pwdSaltFromDb, String pwdHashFromDb);
	int updatePassword(String username, String oldPassword, String newPassword);
	boolean changeApprovalStatus(int userId, int approverId);
	public Map<String, byte[]> getUserImages(int userId);
	List<User> fetchBankEmployees(String branchId);
	List<User> fetchCustomers(String branchId);
	int registerCustomer(User user);
	int forgetPassword(String username, String newPassword);
	int updateProfileImage(int userId, MultipartFile profileImage);

}
