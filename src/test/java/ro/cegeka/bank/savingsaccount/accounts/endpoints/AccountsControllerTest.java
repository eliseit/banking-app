package ro.cegeka.bank.savingsaccount.accounts.endpoints;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;
import ro.cegeka.bank.savingsaccount.accounts.model.AccountType;
import ro.cegeka.bank.savingsaccount.accounts.model.Frequency;
import ro.cegeka.bank.savingsaccount.accounts.service.AccountService;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto.accountDto;
import static ro.cegeka.bank.savingsaccount.accounts.model.Account.AccountBuilder.account;
import static ro.cegeka.bank.savingsaccount.users.User.UserBuilder.user;

@ExtendWith(MockitoExtension.class)
class AccountsControllerTest {


    @InjectMocks
    public AccountsController accountsController;

    @Mock
    private AccountService accountService;
    @Mock
    private UserService userService;
    @Mock
    private AccountMapper mapper;

    @Test
    public void getAccounts() {

        User user = createUser();
        Account account = account()
                .withAmount(BigDecimal.TEN)
                .withCurrency("USD")
                .withFrequency(Frequency.MONTHLY)
                .withStartingDate(LocalDate.now())
                .withType(AccountType.DEBIT)
                .build();
        user.setAccounts(Set.of(account));

        when(userService.getUserByName("Cicero")).thenReturn(Optional.of(user));
        when(mapper.toDto(account)).thenCallRealMethod();

        accountsController.getAccounts("Cicero", new TestModel());

        verify(mapper, times(1)).toDto(account);
    }

    @Test
    public void submitAccount() {

        AccountDto accountDto = accountDto()
                .withAmount(BigDecimal.TEN)
                .withCurrency("USD")
                .withFrequency(Frequency.MONTHLY)
                .withStartingDate(LocalDate.of(1987, 7, 24))
                .withType(AccountType.SAVINGS);

        when(accountService.createSavingsAccount(accountDto)).thenReturn(accountDto);

        String view = accountsController.accountSubmitted(accountDto, new TestModel());

        assertThat(view).isEqualTo("accounts_submitted");
    }

    private User createUser() {
        return user().withName("Cicero").build();
    }

    private class TestModel implements Model {
        @Override
        public Model addAttribute(String s, Object o) {
            return null;
        }

        @Override
        public Model addAttribute(Object o) {
            return null;
        }

        @Override
        public Model addAllAttributes(Collection<?> collection) {
            return null;
        }

        @Override
        public Model addAllAttributes(Map<String, ?> map) {
            return null;
        }

        @Override
        public Model mergeAttributes(Map<String, ?> map) {
            return null;
        }

        @Override
        public boolean containsAttribute(String s) {
            return false;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Map<String, Object> asMap() {
            return null;
        }
    }
}
