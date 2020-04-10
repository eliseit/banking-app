package ro.cegeka.bank.savingsaccount.accounts.model;

import lombok.NoArgsConstructor;
import ro.cegeka.bank.savingsaccount.users.User;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String currency;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private LocalDate startingWith;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @ManyToOne
    private User user;

    private Account(AccountBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.currency = builder.currency;
        this.amount = builder.amount;
        this.type = builder.type;
        this.startingWith = builder.startingWith;
        this.frequency = builder.frequency;
    }

    public long getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public AccountType getType() {
        return type;
    }

    public LocalDate getStartingWith() {
        return startingWith;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public static class AccountBuilder {

        private long id;

        private User user;

        private String currency;

        private BigDecimal amount;

        private AccountType type;

        private LocalDate startingWith;

        private Frequency frequency;

        public static AccountBuilder account() {
            return new AccountBuilder();
        }

        public AccountBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public AccountBuilder withCurrency(String currency) {
            CurrencyUnit currencyUnit = Monetary.getCurrency(currency);
            this.currency = currencyUnit.getCurrencyCode();
            return this;
        }

        public AccountBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public AccountBuilder withType(AccountType type) {
            this.type = type;
            return this;
        }

        public AccountBuilder withStartingDate(LocalDate startingWith) {
            this.startingWith = startingWith;
            return this;
        }

        public AccountBuilder withFrequency(Frequency frequency) {
            this.frequency = frequency;
            return this;
        }

        private Account buildInternal() {

            return new Account(this);
        }

        public final Account build() {
            return buildInternal();
        }
    }
}
