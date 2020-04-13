package ro.cegeka.bank.savingsaccount.accounts.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.model.AccountType;
import ro.cegeka.bank.savingsaccount.accounts.model.Frequency;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto.accountDto;
import static ro.cegeka.bank.savingsaccount.users.User.UserBuilder.user;


@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    public AccountService accountService;

    @Mock
    private UserService userService;

    @Test
    public void happyPath() {

        AccountDto createAccountDto = accountDto()
                .withCurrency("RON")
                .withAmount(BigDecimal.TEN)
                .withType(AccountType.SAVINGS)
                .withStartingDate(LocalDate.of(1987, 7, 24))
                .withFrequency(Frequency.MONTHLY);

        User cicero = user()
                .withName("Cicero")
                .build();

        when(userService.getUserByName("Cicero")).thenReturn(Optional.of(cicero));

        AccountDto savingsAccount = accountService.createSavingsAccount(createAccountDto);


        assertThat(savingsAccount.getCurrency()).isEqualTo("RON");
        assertThat(savingsAccount.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(savingsAccount.getType()).isEqualTo(AccountType.SAVINGS);
        assertThat(savingsAccount.getStartingWith()).isEqualTo(LocalDate.of(1987, 7, 24));
        assertThat(savingsAccount.getFrequency()).isEqualTo(Frequency.MONTHLY);
    }


    @Test
    public void clockTest() {

        Instant getASundayBeforeNoon = Instant.now(Clock.fixed(
                Instant.parse("2020-04-11T11:44:00Z")
                , ZoneOffset.UTC)
        );

        System.out.println(getASundayBeforeNoon);

    }


}
