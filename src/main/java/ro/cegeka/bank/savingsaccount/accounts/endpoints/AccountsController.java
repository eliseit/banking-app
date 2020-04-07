package ro.cegeka.bank.savingsaccount.accounts.endpoints;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping(AccountsController.ACCOUNTS_URL)
public class AccountsController {

    public static final String ACCOUNTS_URL = "/accounts";

    public static final String SAVINGS = "SAVINGS";
    public static final BigDecimal DEFAULT_AMOUNT = BigDecimal.valueOf(5);

    @GetMapping()
    public String getAccounts(@RequestParam(name = "name", required = false, defaultValue = "User") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("account", new AccountDto());
        return "accounts";
    }

    @PostMapping()
    public String accountSubmitted(@ModelAttribute AccountDto account, Model model) {

        checkAndInit(account);

        model.addAttribute("account", account);
        return "accounts_submitted";
    }

    private void checkAndInit(final AccountDto account) {
        account.setType(SAVINGS);

        if (account.getStartingWith() == null) {
            account.setStartingWith(new Date());
        }

        if (account.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            account.setAmount(DEFAULT_AMOUNT);
        }
    }
}
