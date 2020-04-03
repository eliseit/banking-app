package ro.cegeka.bank.savingsaccount.accounts.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Data
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Currency currency;

    private BigDecimal amount;

    private AccountType type;

    private Instant startingWith;

    private Frequency frequency;
}
