package genpact.bank.loan.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import genpact.bank.loan.entity.Account;
import genpact.bank.loan.entity.Loan;
import genpact.bank.loan.repository.LoanDao;
import genpact.bank.loan.utils.CreateAccountUtil;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {

    @Mock
    private LoanDao loanDao;

    @Mock
    private CreateAccountUtil createAccountUtil;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Loan testLoan;
    private Account testAccount;

    @BeforeEach
    void setUp() {
        testLoan = new Loan();
        testLoan.setLoanId(1);
        testLoan.setCustomerId(100);
        testLoan.setLoanAmount(10000);
        testLoan.setInterestRate(5.0);
        testLoan.setLoanTenure(12);
        testAccount = new Account();
        testAccount.setAccountId(12345);
    }

    @Test
    void testFetchSingleLoan() {
        when(loanDao.getSingleLoan(1)).thenReturn(Optional.of(testLoan));
        
        Optional<Loan> result = loanService.fetchSingleLoan(1);
        
        assertTrue(result.isPresent());
        assertEquals(testLoan, result.get());
        verify(loanDao, times(1)).getSingleLoan(1);
       
    }

    @Test
    void testCreateLoan() {
        when(createAccountUtil.createAccount(100, 1)).thenReturn(12345);
        when(loanDao.insertLoan(any(Loan.class))).thenReturn(1);
        
        int result = loanService.createLoan(testLoan, 1);
        
        assertEquals(1, result);
        assertEquals(12345, testLoan.getLoanAccountId());
        assertTrue(testLoan.getInstallmentPerMonth() > 0);
    //    System.out.println("Installment per month in test: "+testLoan.getInstallmentPerMonth());
        verify(createAccountUtil, times(1)).createAccount(100, 1);
        verify(loanDao, times(1)).insertLoan(testLoan);
    }

    @Test
    void testFetchAllLoans() {
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("loanId", 1);
        List<Map<String, Object>> expected = Arrays.asList(loanMap);
        
        when(loanDao.getAllLoans()).thenReturn(expected);
        
        List<Map<String, Object>> result = loanService.fetchAllLoans();
        
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).get("loanId"));
        verify(loanDao, times(1)).getAllLoans();
    }

    
    @Test
    void testFetchBranchLoans() {
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("branchId", 1);
        List<Map<String, Object>> expected = Arrays.asList(loanMap);
        
        when(loanDao.getBranchLoans(1)).thenReturn(expected);
        
        List<Map<String, Object>> result = loanService.fetchBranchLoans(1);
        
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).get("branchId"));
        verify(loanDao, times(1)).getBranchLoans(1);
    }

    @Test
    void testFetchCustomerLoans() {
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("customerId", 100);
        List<Map<String, Object>> expected = Arrays.asList(loanMap);
        
        when(loanDao.getCustomerLoans(100)).thenReturn(expected);
        
        List<Map<String, Object>> result = loanService.fetchCustomerLoans(100);
        
        assertEquals(1, result.size());
        assertEquals(100, result.get(0).get("customerId"));
        verify(loanDao, times(1)).getCustomerLoans(100);
    }

    @Test
    void testManageStatusApproved() {
        when(loanDao.getSingleLoan(1)).thenReturn(Optional.of(testLoan));
        when(loanDao.updateLoan(1, testLoan)).thenReturn(1);
        
        int result = loanService.manageStatus(1, "Approved");
        
        assertEquals(1, result);
        assertEquals("Approved", testLoan.getStatus());
        assertNotNull(testLoan.getApprovedAt());
        verify(loanDao, times(1)).getSingleLoan(1);
        verify(loanDao, times(1)).updateLoan(1, testLoan);
    }

    @Test
    void testManageStatusRejected() {
        when(loanDao.getSingleLoan(1)).thenReturn(Optional.of(testLoan));
        when(loanDao.updateLoan(1, testLoan)).thenReturn(1);
        
        int result = loanService.manageStatus(1, "Rejected");
        
        assertEquals(1, result);
        assertEquals("Rejected", testLoan.getStatus());
        assertNull(testLoan.getApprovedAt());
        verify(loanDao, times(1)).getSingleLoan(1);
        verify(loanDao, times(1)).updateLoan(1, testLoan);
    }

    @Test
    void testFetchCustomerDetails() {
        Map<String, Object> expected = new HashMap<>();
        expected.put("customerId", 100);
        
        when(loanDao.getCustomerDetails(100)).thenReturn(expected);
        
        Map<String, Object> result = loanService.fetchCustomerDetails(100);
        
        assertEquals(100, result.get("customerId"));
        verify(loanDao, times(1)).getCustomerDetails(100);
    }

    @Test
    void testGetAccountDetails() {
        when(loanDao.getAccountDetails(12345)).thenReturn(Optional.of(testAccount));
        
        Optional<Account> result = loanService.getAccountDetails(12345);
        
        assertTrue(result.isPresent());
        assertEquals(testAccount, result.get());
        verify(loanDao, times(1)).getAccountDetails(12345);
    }

   
}