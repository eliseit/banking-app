package ro.cegeka.bank.savingsaccount.accounts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;
import ro.cegeka.bank.savingsaccount.accounts.repository.AccountRepository;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ro.cegeka.bank.savingsaccount.accounts.model.Account.AccountBuilder.account;
import static ro.cegeka.bank.savingsaccount.accounts.model.AccountType.SAVINGS;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {


    private static final String CICERO = "Cicero";

    private final AccountMapper mapper;
    private final AccountRepository repository;
    private final UserService userService;
    private final DateTimeValidator temporalValidator;

    public AccountServiceImpl(AccountMapper mapper, AccountRepository repository, UserService userService, DateTimeValidator temporalValidator) {
        this.mapper = mapper;
        this.repository = repository;
        this.userService = userService;
        this.temporalValidator = temporalValidator;
    }

    public AccountDto createSavingsAccount(AccountDto createSavingAccountDto) {

        // for the sake of simplicity we assume this user exists in DB
        User cicero = userService.getUserByName(CICERO).get();
        Set<Account> ciceroAccounts = cicero.getAccounts();

        checkIfSavingsAccountsAlreadyOpened(ciceroAccounts);

        temporalValidator.isWorkingDay()
                .and()
                .isBetween9AMand6PM();

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

    private void checkIfSavingsAccountsAlreadyOpened(Set<Account> ciceroAccounts) {
        Optional<Account> existingSavingsAccount = ciceroAccounts.stream()
                .filter(a -> a.getType().equals(SAVINGS))
                .findFirst();

        if (existingSavingsAccount.isPresent()) {
            throw new SavingsAccountAlreadyExists("Only One Savings account can be opened by an User!");
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
