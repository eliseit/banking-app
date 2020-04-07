package ro.cegeka.bank.savingsaccount.accounts.endpoints;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class AccountDto {

    private String currency;
    private String type;
    private String frequency;
    private BigDecimal amount;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startingWith;
}
