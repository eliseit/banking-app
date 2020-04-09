package ro.cegeka.bank.savingsaccount.accounts.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {
}
