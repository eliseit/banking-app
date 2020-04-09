package ro.cegeka.bank.savingsaccount.accounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;
import ro.cegeka.bank.savingsaccount.accounts.repository.AccountRepository;

import static ro.cegeka.bank.savingsaccount.accounts.model.Account.AccountBuilder.account;
import static ro.cegeka.bank.savingsaccount.accounts.model.AccountType.SAVINGS;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountMapper mapper;
    private AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountMapper mapper, AccountRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public AccountDto createSavingsAccount(AccountDto createSavingAccountDto) {
        Account accountFromDto = account()
                .withCurrency(createSavingAccountDto.currency)
                .withAmount(createSavingAccountDto.amount)
                .withType(SAVINGS)
                .withStartingDate(createSavingAccountDto.startingWith)
                .withFrequency(createSavingAccountDto.frequency)
                .build();


        return mapper.toDto(repository.save(accountFromDto));
    }
}
