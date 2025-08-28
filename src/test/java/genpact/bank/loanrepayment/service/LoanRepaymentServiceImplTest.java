package genpact.bank.loanrepayment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import genpact.bank.loan.service.LoanService;
import genpact.bank.loanrepayment.entity.LoanRepayment;
import genpact.bank.loanrepayment.repository.LoanRepaymentDao;

@ExtendWith(MockitoExtension.class)
class LoanRepaymentServiceImplTest {

    @Mock
    private LoanRepaymentDao loanRepaymentDao;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanRepaymentServiceImpl loanRepaymentService;

    private LoanRepayment testRepayment;

    @BeforeEach
    void setUp() {
        testRepayment = new LoanRepayment();
        testRepayment.setLoanId(1);
        testRepayment.setAmountPaid(1000.0);
        testRepayment.setOutstandingBalance(5000.0);
    }

    @Test
    void testAddLoanRepayment_FirstInstallment() {
      
        when(loanRepaymentDao.getTotalRepaymentsCountOfLoan(1)).thenReturn(0);
        when(loanRepaymentDao.insertLoanRepayment(any(LoanRepayment.class))).thenReturn(1);

        int result = loanRepaymentService.addLoanRepayment(testRepayment);

        assertEquals(1, result);
        assertEquals(1, testRepayment.getInstallmentNumber());
        verify(loanRepaymentDao).getTotalRepaymentsCountOfLoan(1);
        verify(loanRepaymentDao).insertLoanRepayment(testRepayment);
        verify(loanService, never()).manageStatus(anyInt(), anyString());
    }

    @Test
    void testAddLoanRepayment_FinalPaymentClosesLoan() {
       
        testRepayment.setAmountPaid(5000.0);
        
       
        when(loanRepaymentDao.getTotalRepaymentsCountOfLoan(1)).thenReturn(3);
        when(loanRepaymentDao.insertLoanRepayment(any(LoanRepayment.class))).thenReturn(1);
        
        
        int result = loanRepaymentService.addLoanRepayment(testRepayment);
        
        
        assertEquals(1, result);
        assertEquals(4, testRepayment.getInstallmentNumber());
        assertEquals(0, testRepayment.getOutstandingBalance());
        verify(loanService).manageStatus(1, "Closed");
    }

    @Test
    void testGetSingleLoanRepaymentDetails() {
       
        when(loanRepaymentDao.getSingleLoanRepayment(1)).thenReturn(Optional.of(testRepayment));
        
      
        Optional<LoanRepayment> result = loanRepaymentService.getSingleLoanRepaymentDetails(1);
        
      
        assertTrue(result.isPresent());
        assertEquals(testRepayment, result.get());
        verify(loanRepaymentDao).getSingleLoanRepayment(1);
    }

    @Test
    void testFetchAllRepaymentsOfLoan() {
        
        Map<String, Object> repaymentMap = Collections.singletonMap("loanId", 1);
        when(loanRepaymentDao.getAllRepaymentsOfLoan(1)).thenReturn(List.of(repaymentMap));
        
       
        List<Map<String, Object>> result = loanRepaymentService.fetchAllRepaymentsOfLoan(1);
        
       
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).get("loanId"));
        verify(loanRepaymentDao).getAllRepaymentsOfLoan(1);
    }

    

    
}