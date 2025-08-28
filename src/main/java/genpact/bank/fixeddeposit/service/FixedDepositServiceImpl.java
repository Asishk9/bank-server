package genpact.bank.fixeddeposit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import genpact.bank.fixeddeposit.repository.FixedDepositRepository;
import genpact.bank.user.entity.User;
import genpact.bank.user.repository.UserDao;
import genpact.bank.account.entity.Account;
import genpact.bank.account.repository.AccountRepository;
import genpact.bank.fixeddeposit.entity.FixedDeposit;

@Service
public class FixedDepositServiceImpl implements FixedDepositService {

    @Autowired
    private FixedDepositRepository repository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public FixedDepositServiceImpl(FixedDepositRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to calculate the maturity amount using compound interest formula.
     */
    private double calculateMaturityAmount(double principal, double rate, int tenureMonths) {
        rate = rate / 100; // Convert interest rate from percentage to decimal
        double timeInYears = tenureMonths / 12.0; // Convert tenure from months to years
        return principal * Math.pow(1 + rate, timeInYears); // Compound Interest Formula
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
          String  accountNumber = String.format("%010d", random.nextInt(1_000_000_000));           
        return accountNumber;
    }
//    @Override
//    public int createFixedDeposit(FixedDeposit fixedDeposit) {
//        // Calculate maturity amount
//        double maturityAmount = calculateMaturityAmount(
//            fixedDeposit.getDepositAmount(), 
//            fixedDeposit.getInterestRate(), 
//            fixedDeposit.getTenureMonths()
//        );
//        fixedDeposit.setMaturityAmount(maturityAmount);
//
//        // Save the fixed deposit
//        return repository.save(fixedDeposit);
//    }
    
    @Override
    public int createFixedDeposit(FixedDeposit fixedDeposit, int userId) {
        // Generate a random account number
        String randomAccountNumber = generateRandomAccountNumber();

        User user = userDao.getUserByUserId(userId);
        // Create an account for the fixed deposit
        Account fdAccount = new Account();
        fdAccount.setAccountNo(randomAccountNumber);
        fdAccount.setCustomerId(userId);
        fdAccount.setAccountType("fixed_deposit");
        fdAccount.setBranchId(user.getBranchId());
        fdAccount.setBalance(fixedDeposit.getDepositAmount());
        fdAccount.setInterestRate(fixedDeposit.getInterestRate());
        fdAccount.setActive(true);

        // Save the FD account to the accounts table
        accountRepository.save(fdAccount);

        // Fetch the generated account ID using the unique account number
        String sql = "SELECT account_id FROM accounts WHERE account_no = ?";
        int accountId = jdbcTemplate.queryForObject(sql, new Object[]{randomAccountNumber}, Integer.class);

        // Link the account ID to the fixed deposit
        fixedDeposit.setAccountId(accountId);


        // Calculate the maturity amount
        double maturityAmount = calculateMaturityAmount(
            fixedDeposit.getDepositAmount(),
            fixedDeposit.getInterestRate(),
            fixedDeposit.getTenureMonths()
        );
        fixedDeposit.setMaturityAmount(maturityAmount);
        fixedDeposit.setStatus("Active");
        
        LocalDate maturityDate = LocalDate.now().plusMonths(fixedDeposit.getTenureMonths());
        fixedDeposit.setMaturityDate(maturityDate);

        return repository.save(fixedDeposit);
    }


    @Override
    public FixedDeposit getFixedDepositById(int fdId) {
        return repository.findById(fdId);
    }

    @Override
    public List<FixedDeposit> getAllFixedDeposits() {
        return repository.findAll();
    }
    @Override
    public List<FixedDeposit> getFixedDepositsByCustomer(int customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public int updateFixedDeposit(FixedDeposit fixedDeposit) {
        // Recalculate maturity amount in case deposit details are updated
        double maturityAmount = calculateMaturityAmount(
            fixedDeposit.getDepositAmount(), 
            fixedDeposit.getInterestRate(), 
            fixedDeposit.getTenureMonths()
        );
        fixedDeposit.setMaturityAmount(maturityAmount);

        // Update the fixed deposit
        return repository.updateFixedDeposit(fixedDeposit);
    }

    @Override
    public int deleteFixedDeposit(int fdId) {
        return repository.delete(fdId);
    }

	@Override
	public List<FixedDeposit> getFdByBranchId(int branchId) {
		return repository.findByBranchId(branchId);
	}
}