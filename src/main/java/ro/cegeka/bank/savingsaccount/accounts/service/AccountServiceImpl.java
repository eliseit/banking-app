package ro.cegeka.bank.savingsaccount.accounts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;
import ro.cegeka.bank.savingsaccount.accounts.repository.AccountRepository;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import java.time.Instant;
import java.time.LocalTime;
import java.util.*;

import static ro.cegeka.bank.savingsaccount.accounts.model.Account.AccountBuilder.account;
import static ro.cegeka.bank.savingsaccount.accounts.model.AccountType.SAVINGS;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    public static final String NINE_AM = "09:00:00";
    public static final String SIX_PM = "18:00:00";
    private static final String CICERO = "Cicero";

    private final AccountMapper mapper;
    private final AccountRepository repository;
    private final UserService userService;

    public AccountServiceImpl(AccountMapper mapper, AccountRepository repository, UserService userService) {
        this.mapper = mapper;
        this.repository = repository;
        this.userService = userService;
    }

    public AccountDto createSavingsAccount(AccountDto createSavingAccountDto) {

        // for the sake of simplicity we assume this user exists in DB
        User cicero = userService.getUserByName(CICERO).get();
        Set<Account> ciceroAccounts = cicero.getAccounts();

        checkIfSavingsAccountsAlreadyOpened(ciceroAccounts);

        isBetween9AMand6PM();
        isWorkingDay();

        Account newSavingsAccount = account()
                .withCurrency(createSavingAccountDto.currency)
                .withAmount(createSavingAccountDto.amount)
                .withType(SAVINGS)
                .withStartingDate(createSavingAccountDto.startingWith)
                .withFrequency(createSavingAccountDto.frequency)
                .build();
        ciceroAccounts.add(newSavingsAccount);

        return mapper.toDto(newSavingsAccount);
    }

    private void isWorkingDay() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(Instant.now()));

        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if ((dayOfTheWeek >= Calendar.MONDAY) && (dayOfTheWeek <= Calendar.FRIDAY)) {
            return;
        }

        throw new SavingsAccountCannotBeOpened("Accounts can't be open outside working days!");
    }

    private void isBetween9AMand6PM() {

        LocalTime now = LocalTime.now();

        if (now.isAfter(LocalTime.parse(NINE_AM)) && now.isBefore(LocalTime.parse(SIX_PM))) {
            return;
        }

        throw new SavingsAccountCannotBeOpened("Accounts can't be open during business hours");
    }

    private void checkIfSavingsAccountsAlreadyOpened(Set<Account> ciceroAccounts) {
        Optional<Account> existingSavingsAccount = ciceroAccounts.stream()
                .filter(a -> a.getType().equals(SAVINGS))
                .findFirst();

        if (existingSavingsAccount.isPresent()) {
            throw new SavingsAccountAlreadyExists();
        }
    }

    @Override
    public List<AccountDto> findAll() {
        List<AccountDto> allAccounts = new ArrayList<>();
        repository.findAll()
                .forEach(a -> allAccounts.add(
                        mapper.toDto(a)));
        return allAccounts;
    }
}
