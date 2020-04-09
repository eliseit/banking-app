package ro.cegeka.bank.savingsaccount.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;
import ro.cegeka.bank.savingsaccount.accounts.model.AccountType;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.repository.UserRepository;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static ro.cegeka.bank.savingsaccount.accounts.model.Account.AccountBuilder.account;
import static ro.cegeka.bank.savingsaccount.users.User.UserBuilder.user;

@Component
public class InitDatabase {

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {

            User userCicero = user()
                    .withName("Cicero")
                    .build();

            Set<Account> accounts = new HashSet<>();

            Account debitAccount = account()
                    .withUser(userCicero)
                    .withType(AccountType.DEBIT)
                    .withAmount(BigDecimal.valueOf(19153L))
                    .withCurrency("RON")
                    .build();
            Account creditAccountOne = account()
                    .withUser(userCicero)
                    .withType(AccountType.CREDIT)
                    .withCurrency("EUR")
                    .withAmount(BigDecimal.valueOf(79700L))
                    .build();
            Account creditAccountTwo = account()
                    .withUser(userCicero)
                    .withType(AccountType.CREDIT)
                    .withCurrency("RON")
                    .withAmount(BigDecimal.valueOf(28000L))
                    .build();

            accounts.add(debitAccount);
            accounts.add(creditAccountOne);
            accounts.add(creditAccountTwo);

            userCicero.setAccounts(accounts);

            userRepository.save(userCicero);
        };
    }
}
