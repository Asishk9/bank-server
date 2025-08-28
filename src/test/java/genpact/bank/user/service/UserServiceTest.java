package genpact.bank.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import genpact.bank.user.entity.User;
import genpact.bank.user.repository.UserDao;
import genpact.bank.user.utils.Utils;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserDao userDao;

	@InjectMocks
	private UserServiceImpl userService;

	private User testUser;
	private User testCustomer;

	@BeforeEach
	void setUp() {
		testUser = new User();
		testUser.setUserId(1);
		testUser.setUsername("testuser");
		testUser.setPassword("password123");
		testUser.setFullName("Test User");
		testUser.setEmail("test@example.com");
		testUser.setRole("BANK EMPLOYEE");

		testCustomer = new User();
		testCustomer.setUserId(2);
		testCustomer.setUsername("customer1");
		testCustomer.setPassword("tempPass123");
		testCustomer.setFullName("John Doe");
		testCustomer.setEmail("john@example.com");
		testCustomer.setRole("CUSTOMER");
		testCustomer.setBranchId(1);
	}

	@Test
	void testRegisterUser() {

		when(userDao.insertUser(any(User.class))).thenReturn(1);

		int result = userService.registerUser(testUser);

		// Assert
		assertEquals(1, result);
	//	System.out.println(testUser.getPassSalt());
		assertNotNull(testUser.getPassSalt());
		assertNotNull(testUser.getPassHash());
		assertTrue(testUser.getUserId()==1);
		verify(userDao).insertUser(testUser);
	}

	@Test
	void testRegisterCustomer() {

		when(userDao.insertUser(any(User.class))).thenReturn(1);

		int result = userService.registerCustomer(testCustomer);

		// Assert
		assertEquals(1, result);
		assertNotNull(testCustomer.getUsername());
		assertNotNull(testCustomer.getPassword());
		assertNotNull(testCustomer.getPassSalt());
		assertNotNull(testCustomer.getPassHash());
		verify(userDao).insertUser(testCustomer);
	}

	@Test
	void testFetchAllUsers() {

		List<User> expectedUsers = Arrays.asList(testUser, testCustomer);
		when(userDao.getAllUsers()).thenReturn(expectedUsers);

		List<User> actualUsers = userService.fetchAllUsers();

		// Assert
		assertEquals(2, actualUsers.size());
		assertEquals(expectedUsers, actualUsers);
		verify(userDao).getAllUsers();
	}

	@Test
	void testFetchUserByUsername() {

		when(userDao.getUserByUsername("testuser")).thenReturn(Optional.of(testUser));

		Optional<User> result = userService.fetchUserByUsername("testuser");

		// Assert
		assertTrue(result.isPresent());
		assertEquals(testUser, result.get());
		verify(userDao).getUserByUsername("testuser");
	}

	@Test
	void testFetchUserByUserId() {

		when(userDao.getUserByUserId(1)).thenReturn(testUser);

		User result = userService.fetchUserByUserId(1);

		// Assert
		assertEquals(testUser, result);
		verify(userDao).getUserByUserId(1);
	}

	@Test
	void testModifyUser() {

		when(userDao.updateUser(testUser, 1)).thenReturn(1);

		int result = userService.modifyUser(testUser, 1);

		// Assert
		assertEquals(1, result);
		verify(userDao).updateUser(testUser, 1);
	}

	@Test
	void testRemoveUser() {

		when(userDao.deleteUser(1)).thenReturn(1);

		int result = userService.removeUser(1);

		// Assert
		assertEquals(1, result);
		verify(userDao).deleteUser(1);
	}

	@Test
	void testFetchBankManagers() {

		List<User> managers = Arrays.asList(testUser);
		when(userDao.getBankManagers()).thenReturn(managers);

		List<User> result = userService.fetchBankManagers();

		// Assert
		assertEquals(1, result.size());
		verify(userDao).getBankManagers();
	}

	@Test
	void testFetchBankEmployees() {

		String branchId = "1";
		List<User> employees = Arrays.asList(testUser);
		when(userDao.getBankEmployees(branchId)).thenReturn(employees);

		List<User> result = userService.fetchBankEmployees(branchId);

		// Assert
		assertEquals(1, result.size());
		verify(userDao).getBankEmployees(branchId);
	}

	@Test
	void testFetchCustomers() {

		String branchId = "1";
		List<User> customers = Arrays.asList(testCustomer);
		when(userDao.getCustomers(branchId)).thenReturn(customers);

		List<User> result = userService.fetchCustomers(branchId);

		// Assert
		assertEquals(1, result.size());
		verify(userDao).getCustomers(branchId);
	}

	@Test
    void testMatchPasswords() {
        
        String password = "correctPassword";
        String salt = Utils.generateSalt();
        String tempPassword = salt + password;
        String hash = Utils.generateHash(tempPassword);
        
        // Assert
        assertTrue(userService.matchPasswords(password, salt, hash));
        assertFalse(userService.matchPasswords("wrongPassword", salt, hash));
    }

	@Test
	void testUpdatePassword_Success() {

		String username = "testuser";
		String oldPassword = "oldPass123";
		String newPassword = "newPass456";

		User user = new User();
		String salt = Utils.generateSalt();
		String tempPassword = salt + oldPassword;
		String hash = Utils.generateHash(tempPassword);
		user.setPassSalt(salt);
		user.setPassHash(hash);

		when(userDao.getUserByUsername(username)).thenReturn(Optional.of(user));
		when(userDao.changePassword(any(User.class), eq(username))).thenReturn(1);

		int result = userService.updatePassword(username, oldPassword, newPassword);

		// Assert
		assertEquals(1, result);
		verify(userDao).changePassword(any(User.class), eq(username));
	}

	@Test
	void testUpdatePassword_Failure_WrongOldPassword() {

		String username = "testuser";
		String oldPassword = "wrongOldPass";
		String newPassword = "newPass456";

		User user = new User();
		String salt = Utils.generateSalt();
		String tempPassword = salt + "correctOldPass";
		String hash = Utils.generateHash(tempPassword);
		user.setPassSalt(salt);
		user.setPassHash(hash);

		when(userDao.getUserByUsername(username)).thenReturn(Optional.of(user));

		int result = userService.updatePassword(username, oldPassword, newPassword);

		// Assert
		assertEquals(0, result);
		verify(userDao, never()).changePassword(any(), any());
	}

	@Test
	void testForgetPassword() {

		String username = "testuser";
		String newPassword = "newPass123";

		User user = new User();
		when(userDao.getUserByUsername(username)).thenReturn(Optional.of(user));
		when(userDao.changePassword(any(User.class), eq(username))).thenReturn(1);

		int result = userService.forgetPassword(username, newPassword);

		// Assert
		assertEquals(1, result);
		assertNotNull(user.getPassSalt());
		assertNotNull(user.getPassHash());
		verify(userDao).changePassword(user, username);
	}

	@Test
	void testChangeApprovalStatus() {

		when(userDao.updateApprovalStatus(1, 2)).thenReturn(true);

		boolean result = userService.changeApprovalStatus(1, 2);

		// Assert
		assertTrue(result);
		verify(userDao).updateApprovalStatus(1, 2);
	}

//    @Test
//    void testGetUserImages() {
//        
//        Map<String, byte[]> mockImages = mock(Map.class);
//        when(userDao.getUserImages(1)).thenReturn(mockImages);
//        
//       
//        Map<String, byte[]> result = userService.getUserImages(1);
//        
//        // Assert
//        assertEquals(mockImages, result);
//        verify(userDao).getUserImages(1);
//    }

//  @Test
//  void testUpdateProfileImage() {
//      
//      MultipartFile mockFile = mock(MultipartFile.class);
//      when(userDao.updateImage(1, mockFile)).thenReturn(1);
//      
//     
//      int result = userService.updateProfileImage(1, mockFile);
//      
//      // Assert
//      assertEquals(1, result);
//      verify(userDao).updateImage(1, mockFile);
//  }

}