package ro.cegeka.bank.savingsaccount.users.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ro.cegeka.bank.savingsaccount.users.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {


}
