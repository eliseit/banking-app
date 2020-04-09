package ro.cegeka.bank.savingsaccount.accounts.endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import ro.cegeka.bank.savingsaccount.accounts.model.AccountType;
import ro.cegeka.bank.savingsaccount.accounts.model.Frequency;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    public String currency;
    public AccountType type;
    public Frequency frequency;
    public BigDecimal amount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate startingWith;
    private long id;

    public static AccountDto accountDto() {
        return new AccountDto();
    }

    public AccountDto withId(long id) {
        this.id = id;
        return this;
    }

    public AccountDto withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public AccountDto withType(AccountType type) {
        this.type = type;
        return this;
    }

    public AccountDto withFrequency(Frequency frequency) {
        this.frequency = frequency;
        return this;
    }

    public AccountDto withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public AccountDto withStartingDate(LocalDate startingWith) {
        this.startingWith = startingWith;
        return this;
    }
}
