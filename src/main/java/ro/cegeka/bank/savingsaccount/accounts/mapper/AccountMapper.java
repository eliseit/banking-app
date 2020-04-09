package ro.cegeka.bank.savingsaccount.accounts.mapper;

import org.springframework.stereotype.Component;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;

import static ro.cegeka.bank.savingsaccount.accounts.endpoints.AccountDto.accountDto;

@Component
public class AccountMapper {


    public AccountDto toDto(Account account) {
        return accountDto()
                .withId(account.getId())
                .withCurrency(account.getCurrency())
                .withAmount(account.getAmount())
                .withType(account.getType())
                .withStartingDate(account.getStartingWith())
                .withFrequency(account.getFrequency());
    }
}
