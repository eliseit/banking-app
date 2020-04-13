package ro.cegeka.bank.savingsaccount.accounts.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;
import ro.cegeka.bank.savingsaccount.accounts.model.Frequency;
import ro.cegeka.bank.savingsaccount.accounts.repository.AccountRepository;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.math.BigDecimal.TEN;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto.accountDto;
import static ro.cegeka.bank.savingsaccount.accounts.model.Account.AccountBuilder.account;
import static ro.cegeka.bank.savingsaccount.accounts.model.AccountType.DEBIT;
import static ro.cegeka.bank.savingsaccount.users.User.UserBuilder.user;

@SpringBootTest
class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private AccountMapper mapper;

    @Test
    public void createSavingsAccount() {

        AccountDto createAccountDto = accountDto()
                .withType(DEBIT)
                .withCurrency("RON")
                .withAmount(TEN)
                .withFrequency(Frequency.BI_WEEKLY)
                .withStartingDate(LocalDate.of(1987, 7, 24));

        User cicero = user()
                .withName("Cicero")
                .build();
        Set<Account> accounts = new HashSet<>();
        accounts.add(account()
                .withType(DEBIT)
                .withAmount(TEN)
                .withCurrency("RON")
                .build());

        cicero.setAccounts(accounts);

        Mockito.when(userService.getUserByName("Cicero"))
                .thenReturn(Optional.of(cicero));

        accountService.createSavingsAccount(createAccountDto);

        verify(mapper, times(1)).toDto(any());
    }
}
