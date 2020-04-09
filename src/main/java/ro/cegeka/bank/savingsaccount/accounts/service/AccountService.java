package ro.cegeka.bank.savingsaccount.accounts.service;

import ro.cegeka.bank.savingsaccount.accounts.endpoints.AccountDto;

public interface AccountService {

    AccountDto createSavingsAccount(AccountDto createSavingsAccountDto);
}
