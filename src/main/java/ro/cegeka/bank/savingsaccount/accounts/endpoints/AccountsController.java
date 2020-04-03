package ro.cegeka.bank.savingsaccount.accounts.endpoints;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class AccountsController {

    @GetMapping("/accounts")
    public String test(@RequestParam(name = "name", required = false, defaultValue = "User") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("account", new AccountDto());
        return "accounts";
    }

    @PostMapping("/accounts")
    public String accountSubmitted(@ModelAttribute AccountDto account, Model model) {

        account.type = "SAVINGS";
        System.out.println(account);

        if (account.getStartingWith() == null) {
            account.setStartingWith(new Date());
        }

        model.addAttribute("account", account);
        return "accounts_submitted";
    }
}
