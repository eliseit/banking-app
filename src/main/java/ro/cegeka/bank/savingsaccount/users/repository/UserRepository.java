package ro.cegeka.bank.savingsaccount.users.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ro.cegeka.bank.savingsaccount.users.User;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByName(String name);

}
