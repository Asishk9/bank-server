package genpact.bank.branch.service;

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

import genpact.bank.branch.entity.Branch;
import genpact.bank.branch.repository.BranchDao;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

	@Mock
	private BranchDao branchDao;

	@InjectMocks
	private BranchServiceImpl branchService;

	private Branch testBranch1;
	private Branch testBranch2;

	@BeforeEach
	void setUp() {
		testBranch1 = new Branch();
		testBranch1.setBranchId(1);
		testBranch1.setBranchName("Main Branch");
		testBranch1.setBranchCode("MB001");
		testBranch1.setRmId(101);

		testBranch2 = new Branch();
		testBranch2.setBranchId(2);
		testBranch2.setBranchName("Downtown Branch");
		testBranch2.setBranchCode("DT002");
		testBranch2.setRmId(102);
	}

	@Test
	void testAddBranch() {

		when(branchDao.insertBranch(any(Branch.class))).thenReturn(1);

		int result = branchService.addBranch(testBranch1);

		// Assert
		assertEquals(1, result);
		verify(branchDao).insertBranch(testBranch1);
	}

	@Test
	void testFetchBranchById() {

		when(branchDao.getBranchById(1)).thenReturn(Optional.of(testBranch1));

		Optional<Branch> result = branchService.fetchBranchById(1);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(testBranch1, result.get());
		verify(branchDao).getBranchById(1);
	}

	@Test
	void testFetchAllBranches() {

		List<Branch> expectedBranches = Arrays.asList(testBranch1, testBranch2);
		when(branchDao.getAllBranches()).thenReturn(expectedBranches);

		List<Branch> result = branchService.fetchAllBranches();

		// Assert
		assertEquals(2, result.size());
		assertTrue(result.containsAll(expectedBranches));
		verify(branchDao).getAllBranches();
	}

	@Test
	void testModifyBranch_Success() {

		Branch updatedBranch = new Branch();
		updatedBranch.setBranchName("Updated Branch Name");

		when(branchDao.updateBranch(updatedBranch, 1)).thenReturn(1);

		int result = branchService.modifyBranch(updatedBranch, 1);

		// Assert
		assertEquals(1, result);
		verify(branchDao).updateBranch(updatedBranch, 1);
	}

	@Test
	void testRemoveBranch_Success() {

		when(branchDao.deleteBranch(1)).thenReturn(1);

		int result = branchService.removeBranch(1);

		// Assert
		assertEquals(1, result);
		verify(branchDao).deleteBranch(1);
	}

	@Test
	void testFetchBranchesByRm() {

		List<Branch> expectedBranches = Arrays.asList(testBranch1);
		when(branchDao.getBranchByRmId(101)).thenReturn(expectedBranches);

		List<Branch> result = branchService.fetchBranchesByRm(101);

		// Assert
		assertEquals(1, result.size());
		assertEquals(testBranch1, result.get(0));
		verify(branchDao).getBranchByRmId(101);
	}

}