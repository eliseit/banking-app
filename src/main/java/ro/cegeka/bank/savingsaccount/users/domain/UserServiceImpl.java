package ro.cegeka.bank.savingsaccount.users.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return this.repository.findByName(name);
    }
}
