package ro.cegeka.bank.savingsaccount.accounts.endpoints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.cegeka.bank.savingsaccount.accounts.service.AccountService;

import java.math.BigDecimal;

@Controller
@RequestMapping(AccountsController.ACCOUNTS_URL)
public class AccountsController {

    public static final String ACCOUNTS_URL = "/accounts";

    public static final String SAVINGS = "SAVINGS";
    public static final BigDecimal DEFAULT_AMOUNT = BigDecimal.valueOf(5);

    private final AccountService service;

    @Autowired
    public AccountsController(AccountService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAccounts(@RequestParam(name = "name", required = false, defaultValue = "User") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("account", new AccountDto());
        return "accounts";
    }

    @PostMapping()
    public String accountSubmitted(@ModelAttribute AccountDto account, Model model) {

        System.out.println(account);
        AccountDto persistedAccount = service.createSavingsAccount(account);

        model.addAttribute("account", persistedAccount);
        return "accounts_submitted";
    }

}
