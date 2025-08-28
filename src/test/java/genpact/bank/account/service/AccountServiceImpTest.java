package genpact.bank.account.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import genpact.bank.account.entity.Account;
import genpact.bank.account.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setAccountId(1);
        testAccount.setAccountNo("1234567890");
        testAccount.setCustomerId(100);
        testAccount.setBalance(5000.00);
    }

    @Test
    void testGetAccountById() {
        // Arrange
        when(accountRepository.findById(1)).thenReturn(testAccount);

        // Act
        Account result = accountService.getAccountById(1);

        // Assert
        assertNotNull(result);
        assertEquals(testAccount, result);
        verify(accountRepository, times(1)).findById(1);
    }

    @Test
    void testCreateAccount() {
        // Arrange
        when(accountRepository.save(any(Account.class))).thenReturn(1);

        // Act
        int result = accountService.createAccount(testAccount);

        // Assert
        assertEquals(1, result);
        verify(accountRepository, times(1)).save(testAccount);
    }

    @Test
    void testGetAllAccounts() {
        // Arrange
        Account account1 = new Account();
        account1.setAccountId(1);
        account1.setAccountNo("1234567890");

        Account account2 = new Account();
        account2.setAccountId(2);
        account2.setAccountNo("0987654321");

        List<Account> accountList = Arrays.asList(account1, account2);
        when(accountRepository.findAll()).thenReturn(accountList);

        // Act
        List<Account> result = accountService.getAllAccounts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAccount() {
        // Arrange
        when(accountRepository.delete(1)).thenReturn(1);

        // Act
        int result = accountService.deleteAccount(1);

        // Assert
        assertEquals(1, result);
        verify(accountRepository, times(1)).delete(1);
    }

    @Test
    void testWithdraw() {
        // Arrange
        when(accountRepository.withdraw(1, 500)).thenReturn(1);

        // Act
        int result = accountService.withdraw(1, 500);

        // Assert
        assertEquals(1, result);
        verify(accountRepository, times(1)).withdraw(1, 500);
    }

    @Test
    void testDeposit() {
        // Arrange
        when(accountRepository.deposit(1, 1000)).thenReturn(1);

        // Act
        int result = accountService.deposit(1, 1000);

        // Assert
        assertEquals(1, result);
        verify(accountRepository, times(1)).deposit(1, 1000);
    }

    @Test
    void testGenerateUniqueAccountNumber() {
        // Arrange
        when(accountRepository.checkAccountExists(anyString())).thenReturn(false);

        // Act
        String result = accountService.generateUniqueAccountNumber();

        // Assert
        assertNotNull(result);
        assertEquals(10, result.length());
        verify(accountRepository, atLeastOnce()).checkAccountExists(result);
    }
}