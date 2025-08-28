package genpact.bank.user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import genpact.bank.user.entity.User;
import genpact.bank.user.repository.UserDao;
import genpact.bank.user.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public int registerUser(User user) {
		String passwordFromUi = user.getPassword();
		String passSalt = Utils.generateSalt();
		String tempPassword = passSalt + passwordFromUi;
		String passHash = Utils.generateHash(tempPassword);
		user.setPassSalt(passSalt);
		user.setPassHash(passHash);

		return userDao.insertUser(user);
	}

	private String generateRandomUsername() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder username = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 10; i++) { // Generate 10 characters
			username.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		return username.toString();
	}

	private String generateTemporaryPassword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
		StringBuilder password = new StringBuilder();
		Random random = new Random();

		// Generate a password of length 10
		for (int i = 0; i < 10; i++) {
			password.append(characters.charAt(random.nextInt(characters.length())));
		}

		return password.toString();
	}

	@Override
	public int registerCustomer(User user) {

		String username = generateRandomUsername();
//	        System.out.println("in here"+username);
		// Generate a unique password
		String password = generateTemporaryPassword();

		// Set generated username and password
		user.setUsername(username);
		user.setPassword(password);

		String passwordFromUi = user.getPassword();
		String passSalt = Utils.generateSalt();
		String tempPassword = passSalt + passwordFromUi;
		String passHash = Utils.generateHash(tempPassword);
		user.setPassSalt(passSalt);
		user.setPassHash(passHash);

		return userDao.insertUser(user);
	}

	@Override
	public List<User> fetchAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Optional<User> fetchUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public User fetchUserByUserId(int userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public int modifyUser(User user, int userId) {
		return userDao.updateUser(user, userId);
	}
	
	@Override
	public int updateProfileImage(int userId, MultipartFile profileImage) {
		return userDao.updateImage(userId, profileImage);
	}

	@Override
	public int removeUser(int userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public List<User> fetchBankManagers() {
		return userDao.getBankManagers();
	}

	@Override
	public boolean matchPasswords(String passwordFromUI, String pwdSaltFromDb, String pwdHashFromDb) {

		String tempPassword = pwdSaltFromDb + passwordFromUI;
		String newHash = Utils.generateHash(tempPassword);

		return newHash.equals(pwdHashFromDb);

	}

	@Override
	public int updatePassword(String username, String oldPassword, String newPassword) {
		Optional<User> optionalUser = fetchUserByUsername(username);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			boolean isMatch = matchPasswords(oldPassword, user.getPassSalt(), user.getPassHash());

			if (isMatch) {
				String newPassSalt = Utils.generateSalt();
				String tempNewPassword = newPassSalt + newPassword;
				String newPassHash = Utils.generateHash(tempNewPassword);

				user.setPassSalt(newPassSalt);
				user.setPassHash(newPassHash);

				return userDao.changePassword(user, username);
			}
		}
		return 0;
	}

	@Override
	public int forgetPassword(String username, String newPassword) {

		Optional<User> optionalUser = fetchUserByUsername(username);
		User user = optionalUser.get();
		String newPassSalt = Utils.generateSalt();
		String tempNewPassword = newPassSalt + newPassword;
		String newPassHash = Utils.generateHash(tempNewPassword);

		user.setPassSalt(newPassSalt);
		user.setPassHash(newPassHash);
		return userDao.changePassword(user, username);
	}

	public boolean changeApprovalStatus(int userId, int approverId) {
		return userDao.updateApprovalStatus(userId, approverId);
	}

	@Override
	public Map<String, byte[]> getUserImages(int userId) {
		return userDao.getUserImages(userId);
	}

	@Override
	public List<User> fetchBankEmployees(String branchId) {
		return userDao.getBankEmployees(branchId);
	}

	@Override
	public List<User> fetchCustomers(String branchId) {
		return userDao.getCustomers(branchId);
	}

	

}
