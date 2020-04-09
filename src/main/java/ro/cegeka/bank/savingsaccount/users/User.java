package ro.cegeka.bank.savingsaccount.users;

import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.cegeka.bank.savingsaccount.accounts.model.Account;

import javax.persistence.*;
import java.util.Set;

import static org.hibernate.validator.internal.util.CollectionHelper.newHashSet;

@Entity
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private Set<Account> accounts = newHashSet();

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.accounts = builder.accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public static class UserBuilder {


        public long id;
        public String name;
        public Set<Account> accounts;

        public static UserBuilder user() {
            return new UserBuilder();
        }

        public UserBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withAccounts(Set<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        private User buildInternal() {
            return new User(this);
        }

        public final User build() {
            return buildInternal();
        }
    }

}
