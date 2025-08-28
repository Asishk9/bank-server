package genpact.bank.transactions.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import genpact.bank.transactions.entities.Transaction;
import genpact.bank.transactions.repositories.TransactionDao;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

	@Mock
	private TransactionDao transactionDao;

	@InjectMocks
	private TransactionServiceImpl transactionService;

	private Transaction testTransaction;

	@BeforeEach
	void setUp() {
		testTransaction = new Transaction();
		testTransaction.setTransactionId(1);
		testTransaction.setAccountId(12345);
		testTransaction.setTransactionType("Credit");
		testTransaction.setAmount(5000);
		testTransaction.setStatus("Completed");
		testTransaction.setFinalBalance(10000);
		testTransaction.setReferenceNumber("UTR123456");
		testTransaction.setTransactionMode("Online");

	}

	@Test
	void testGetAllTransactions() {
		List<Transaction> expectedTransactions = Arrays.asList(testTransaction);
		when(transactionDao.getAllTransactions()).thenReturn(expectedTransactions);

		List<Transaction> result = transactionService.getAllTransactions();
		assertEquals(1, result.size());
		assertEquals(testTransaction, result.get(0));
		// System.out.println(testTransaction);
		verify(transactionDao, times(1)).getAllTransactions();
	}

	@Test
	void testGetTransactionById() {
		when(transactionDao.getTransactionById(1)).thenReturn(testTransaction);

		Transaction result = transactionService.getTransactionById(1);
		assertNotNull(result);
		assertEquals(testTransaction, result);
		verify(transactionDao, times(1)).getTransactionById(1);
	}

	@Test
	void testAddTransaction() {
		doNothing().when(transactionDao).addTransaction(any(Transaction.class));

		transactionService.addTransaction(testTransaction);

		assertNotNull(testTransaction.getReferenceNumber());
		// System.out.println(testTransaction.getReferenceNumber());
		verify(transactionDao, times(1)).addTransaction(testTransaction);
	}

	@Test
	void testUpdateTransaction() {
		doNothing().when(transactionDao).updateTransaction(any(Transaction.class));

		transactionService.updateTransaction(testTransaction);

		verify(transactionDao, times(1)).updateTransaction(testTransaction);
	}

	@Test
	void testDeleteTransaction() {
		doNothing().when(transactionDao).deleteTransaction(1);

		transactionService.deleteTransaction(1);

		verify(transactionDao, times(1)).deleteTransaction(1);
	}

	@Test
	void testGetTransactionByRef() {
		when(transactionDao.getTransactionByRefId("UTR123456")).thenReturn(testTransaction);

		Transaction result = transactionService.getTransactionByRef("UTR123456");
		assertNotNull(result);
		assertEquals(testTransaction, result);
		verify(transactionDao, times(1)).getTransactionByRefId("UTR123456");
	}

	@Test
	void testGetTransactionsByAccountId() {
		List<Transaction> expectedTransactions = Arrays.asList(testTransaction);
		when(transactionDao.getTransactionByAccountId(12345)).thenReturn(expectedTransactions);

		List<Transaction> result = transactionService.getTransactionsByAccountId(12345);
		assertEquals(1, result.size());
		assertEquals(testTransaction, result.get(0));
		verify(transactionDao, times(1)).getTransactionByAccountId(12345);
	}

	@Test
	void testGetBranchTransactions() {
		List<Transaction> expectedTransactions = Arrays.asList(testTransaction);
		when(transactionDao.getBranchTransaction(1)).thenReturn(expectedTransactions);

		List<Transaction> result = transactionService.getBranchTransactions(1);
		assertEquals(1, result.size());
		assertEquals(testTransaction, result.get(0));
		verify(transactionDao, times(1)).getBranchTransaction(1);
	}
}