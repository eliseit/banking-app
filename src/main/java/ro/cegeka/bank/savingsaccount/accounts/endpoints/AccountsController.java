package ro.cegeka.bank.savingsaccount.accounts.endpoints;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.service.AccountService;

@Controller
@RequestMapping(AccountsController.ACCOUNTS_URL)
public class AccountsController {

    public static final String ACCOUNTS_URL = "/accounts";
    private final AccountService service;

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
