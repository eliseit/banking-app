package ro.cegeka.bank.savingsaccount.accounts.service;

import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createSavingsAccount(AccountDto createSavingsAccountDto);

    List<AccountDto> findAll();
}
