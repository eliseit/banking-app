package ro.cegeka.bank.savingsaccount.accounts.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.model.AccountType;
import ro.cegeka.bank.savingsaccount.accounts.model.Frequency;
import ro.cegeka.bank.savingsaccount.accounts.repository.AccountRepository;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto.accountDto;
import static ro.cegeka.bank.savingsaccount.users.User.UserBuilder.user;

@SpringBootTest
class AccountServiceImplTest {

    public AccountService accountService;

    @Mock
    private AccountMapper mapper;

    @Mock
    private AccountRepository repository;

    @Mock
    private UserService userService;

    @Test
    public void givenValidPreconditions_whenSavingsAccountIsOpen_thenAccountIsSaved() {

        DateTimeValidator temporalValidator = new DateTimeValidator(Clock.fixed(
                Instant.parse("2020-04-13T10:15:30.00Z"),
                ZoneOffset.UTC));

        accountService = new AccountServiceImpl(mapper, repository, userService, temporalValidator);

        AccountDto createAccountDto = accountDto()
                .withCurrency("RON")
                .withAmount(BigDecimal.TEN)
                .withType(AccountType.SAVINGS)
                .withStartingDate(LocalDate.of(1987, 7, 24))
                .withFrequency(Frequency.MONTHLY);

        User cicero = user()
                .withName("Cicero")
                .withAccounts(new HashSet<>())
                .build();

        when(userService.getUserByName("Cicero")).thenReturn(Optional.of(cicero));
        when(mapper.toDto(any())).thenCallRealMethod();

        AccountDto savingsAccount = accountService.createSavingsAccount(createAccountDto);

        assertThat(savingsAccount.getCurrency()).isEqualTo("RON");
        assertThat(savingsAccount.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(savingsAccount.getType()).isEqualTo(AccountType.SAVINGS);
        assertThat(savingsAccount.getStartingWith()).isEqualTo(LocalDate.of(1987, 7, 24));
        assertThat(savingsAccount.getFrequency()).isEqualTo(Frequency.MONTHLY);
    }

    @Test
    public void givenWeekendDay_whenAccountCreate_thenException() {

        DateTimeValidator temporalValidator = new DateTimeValidator(Clock.fixed(
                Instant.parse("2020-04-11T10:15:30.00Z"),
                ZoneOffset.UTC));

        accountService = new AccountServiceImpl(mapper, repository, userService, temporalValidator);

        assertThrows(SavingsAccountCannotBeOpened.class, () -> accountService.createSavingsAccount(accountDto()));
    }

    @Test
    public void givenWorkDayButInvalidTime_whenAccountCreate_thenException() {

        DateTimeValidator temporalValidator = new DateTimeValidator(Clock.fixed(
                Instant.parse("2020-04-13T19:15:30.00Z"),
                ZoneOffset.UTC));

        accountService = new AccountServiceImpl(mapper, repository, userService, temporalValidator);

        assertThrows(SavingsAccountCannotBeOpened.class, () -> accountService.createSavingsAccount(accountDto()));
    }

}
