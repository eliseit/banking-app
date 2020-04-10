package ro.cegeka.bank.savingsaccount.users.domain;

import ro.cegeka.bank.savingsaccount.users.User;

import java.util.Optional;

public interface UserService {


    Optional<User> getUserByName(String name);

}
