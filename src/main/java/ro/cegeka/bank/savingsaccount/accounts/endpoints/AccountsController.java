package ro.cegeka.bank.savingsaccount.accounts.endpoints;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountsController {

    @GetMapping("/accounts")
    public String test(@RequestParam(name = "name", required = false, defaultValue = "User") String name, Model model) {
        model.addAttribute("name", name);
        return "accounts";
    }

}
