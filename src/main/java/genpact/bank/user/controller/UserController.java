package genpact.bank.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import genpact.bank.branch.exception.InvalidBranchDataException;
import genpact.bank.user.entity.User;
import genpact.bank.user.exception.*;
import genpact.bank.user.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@ModelAttribute User user) {
		try {
		//	System.out.println("\n" + user + "\n");
			if (!user.getFullName().matches("^[a-zA-Z\\s]+$")) {
				throw new DataValidationException("Full name should contain only alphabets");
			}

			if (user.getPhone() == null || !user.getPhone().matches("^[0-9]{10}$")) {
				throw new InvalidBranchDataException("Please enter 10 digits phone number");
			}

			if (user.getPincode() == null || !user.getPincode().matches("^[0-9]{6}$")) {
				throw new InvalidBranchDataException("Pincode must contain exactly 6 digits");
			}

			int result = userService.registerUser(user);
			System.out.println("User from UI : " + user);
			if (result > 0) {
				return ResponseEntity.ok("User registered successfully.");
			}
			throw new UserRegistrationException("Failed to register user.");
		} catch (DataIntegrityViolationException ex) {
			String errorMessage = ex.getMostSpecificCause().getMessage();
			if (errorMessage.contains("username_UNIQUE")) {
				throw new DuplicateEntryException("Username already exists");
			} else if (errorMessage.contains("email_UNIQUE")) {
				throw new DuplicateEntryException("Email already exists");
			} else if (errorMessage.contains("phone_UNIQUE")) {
				throw new DuplicateEntryException("Phone number already exists");
			}
			throw new DataValidationException("Invalid user data: " + errorMessage);
		}
	}

	@PostMapping("/registerCustomer")
	public ResponseEntity<String> registerCustomer(@ModelAttribute User user) {
		try {
			if (!user.getFullName().matches("^[a-zA-Z\\s]+$")) {
				throw new DataValidationException("Full name should contain only alphabets");
			}

			if (user.getPhone() == null || !user.getPhone().matches("^[0-9]{10}$")) {
				throw new InvalidBranchDataException("Please enter 10 digits phone number");
			}

			if (user.getPincode() == null || !user.getPincode().matches("^[0-9]{6}$")) {
				throw new InvalidBranchDataException("Pincode must contain exactly 6 digits");
			}

			int result = userService.registerCustomer(user);
			System.out.println("Customer from UI : " + user);
			if (result > 0) {
				String username = user.getUsername();
				String password = user.getPassword();
				return ResponseEntity.ok("Username : " + username + ", Password : " + password);
			}
			throw new UserRegistrationException("Failed to register customer.");
		} catch (DataIntegrityViolationException ex) {
			String errorMessage = ex.getMostSpecificCause().getMessage();
			if (errorMessage.contains("email_UNIQUE")) {
				throw new DuplicateEntryException("Email already exists");
			} else if (errorMessage.contains("phone_UNIQUE")) {
				throw new DuplicateEntryException("Phone number already exists");
			}
			throw new DataValidationException("Invalid customer data: " + errorMessage);
		}
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<String> modifyUser(@RequestBody User user, @PathVariable int userId) {
		try {
			if (!user.getFullName().matches("^[a-zA-Z\\s]+$")) {
				throw new DataValidationException("Full name should contain only alphabets");
			}
			if (user.getPhone() == null || !user.getPhone().matches("^[0-9]{10}$")) {
				throw new InvalidBranchDataException("Please enter 10 digits phone number");
			}

			int result = userService.modifyUser(user, userId);
			if (result <= 0) {
				throw new UserNotFoundException("Failed to update user or user not found with ID: " + userId);
			}
			return ResponseEntity.ok("User updated successfully");
		} catch (DataIntegrityViolationException ex) {
			String errorMessage = ex.getMostSpecificCause().getMessage();
			if (errorMessage.contains("username_UNIQUE")) {
				throw new DuplicateEntryException("Username already exists");
			} else if (errorMessage.contains("email_UNIQUE")) {
				throw new DuplicateEntryException("Email already exists");
			} else if (errorMessage.contains("phone_UNIQUE")) {
				throw new DuplicateEntryException("Phone number already exists");
			}
			throw new DataValidationException("Invalid User data: " + errorMessage);
		}
	}

	@GetMapping
	public ResponseEntity<List<User>> fetchAllUsers() {
		List<User> users = userService.fetchAllUsers();
		if (users.isEmpty()) {
			throw new UserNotFoundException("No users found");
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/userDetails/{username}")
	public ResponseEntity<?> fetchUserByUsername(@PathVariable String username) {
		Optional<User> user = userService.fetchUserByUsername(username);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found with username: " + username);
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping("/details/{userId}")
	public ResponseEntity<User> fetchUserByUserId(@PathVariable int userId) {
		User user = Optional.ofNullable(userService.fetchUserByUserId(userId))
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
		return ResponseEntity.ok(user);
	}

	@GetMapping("/getUserImages/{userId}")
	public ResponseEntity<Map<String, byte[]>> getImage(@PathVariable int userId) {
		Map<String, byte[]> images = userService.getUserImages(userId);
		if (images == null || images.isEmpty()) {
			throw new FileProcessingException("No images found for user ID: " + userId);
		}
		return ResponseEntity.ok(images);
	}

	@PutMapping("/updateImage/{userId}")
	public ResponseEntity<String> updateProfileImage(@PathVariable int userId,
			@RequestParam("profileImage") MultipartFile profileImage) throws IOException {

		int result = userService.updateProfileImage(userId, profileImage);
		if (result <= 0) {
			throw new UserNotFoundException("User not found with ID: " + userId);
		}
		return ResponseEntity.ok("Profile image updated successfully");
	}

	@GetMapping("/BM")
	public ResponseEntity<List<User>> fetchBM() {
		List<User> users = userService.fetchBankManagers();
		if (users.isEmpty()) {
			throw new UserNotFoundException("No bank managers found");
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/BE/{branchId}")
	public ResponseEntity<List<User>> fetchBEByBranch(@PathVariable String branchId) {
		List<User> users = userService.fetchBankEmployees(branchId);
		if (users.isEmpty()) {
			throw new UserNotFoundException("No bank employees found for branch ID: " + branchId);
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/customer/{branchId}")
	public ResponseEntity<List<User>> fetchCustomerByBranch(@PathVariable String branchId) {
		List<User> users = userService.fetchCustomers(branchId);
		if (users.isEmpty()) {
			throw new UserNotFoundException("No customers found for branch ID: " + branchId);
		}
		return ResponseEntity.ok(users);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> removeUser(@PathVariable int userId) {
		int result = userService.removeUser(userId);
		if (result <= 0) {
			throw new UserNotFoundException("User not found with ID: " + userId);
		}
		return ResponseEntity.ok("User deleted successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginRequest(@RequestBody Map<String, String> requestBody) {
		String usernameFromUI = requestBody.get("username");
		String passwordFromUI = requestBody.get("password");

		try {

			if (usernameFromUI == null || passwordFromUI == null) {
				throw new InvalidCredentialsException("Username and password are required");
			}

			Optional<User> optionalUser = userService.fetchUserByUsername(usernameFromUI);
			User userFromDB = optionalUser.get();

			if (!userFromDB.isApproved()) {
				throw new ApprovalPendingException("Cannot login, your authorization is pending");
			}

			String passSaltFromDb = userFromDB.getPassSalt();
			String passHashFromDb = userFromDB.getPassHash();

			if (!userService.matchPasswords(passwordFromUI, passSaltFromDb, passHashFromDb)) {
				throw new InvalidCredentialsException("Invalid password");
			}
			
			System.out.println(userFromDB);

			return ResponseEntity.ok(Map.of("message", "Login successful", "userDetails", userFromDB));
		} catch (EmptyResultDataAccessException e) {
			throw new InvalidCredentialsException("invalid username");
		}
	}

	@PutMapping("/updatePassword/{username}")
	public ResponseEntity<String> updatePassword(@PathVariable String username,
			@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
		int isUpdated = userService.updatePassword(username, oldPassword, newPassword);
		if (isUpdated <= 0) {
			throw new InvalidCredentialsException("Old password is incorrect or user not found");
		}
		return ResponseEntity.ok("Password updated successfully.");
	}

	@PutMapping("/forgetPassword/{username}")
	public ResponseEntity<?> forgetPassword(@PathVariable String username, @RequestParam String dateOfBirth,
			@RequestParam String phone, @RequestParam String newPassword) {
		try {
			Optional<User> optionalUser = userService.fetchUserByUsername(username);
			User user = optionalUser.get();

			if (!dateOfBirth.equals(user.getDateOfBirth().toString())) {
				throw new DataValidationException("Incorrect date of birth");
			}

			if (!phone.equals(user.getPhone())) {
				throw new DataValidationException("Incorrect phone number");
			}

			int result = userService.forgetPassword(username, newPassword);
			if (result <= 0) {
				throw new UserNotFoundException("Failed to update password for user: " + username);
			}
			return ResponseEntity.ok("Password updated successfully!");
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("user not found with username: " + username);
		}

	}

	@PutMapping("/changeApproval/{userId}")
	public ResponseEntity<String> changeApprovalStatus(@PathVariable int userId, @RequestParam int approverId) {
		boolean isUpdated = userService.changeApprovalStatus(userId, approverId);
		if (!isUpdated) {
			throw new UserNotFoundException("Failed to update approval status for user ID: " + userId);
		}
		return ResponseEntity.ok("Approval status updated successfully.");
	}
}
