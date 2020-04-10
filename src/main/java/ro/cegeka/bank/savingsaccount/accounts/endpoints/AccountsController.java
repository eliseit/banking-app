package ro.cegeka.bank.savingsaccount.accounts.endpoints;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.cegeka.bank.savingsaccount.accounts.endpoints.dto.AccountDto;
import ro.cegeka.bank.savingsaccount.accounts.mapper.AccountMapper;
import ro.cegeka.bank.savingsaccount.accounts.service.AccountService;
import ro.cegeka.bank.savingsaccount.users.User;
import ro.cegeka.bank.savingsaccount.users.domain.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(AccountsController.ACCOUNTS_URL)
public class AccountsController {

    public static final String ACCOUNTS_URL = "/accounts";
    private final AccountService accountService;
    private final UserService userService;
    private final AccountMapper mapper;

    public AccountsController(AccountService accountService, UserService userService, AccountMapper mapper) {
        this.accountService = accountService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping()
    public String getAccounts(@RequestParam(name = "name", required = false, defaultValue = "User") String name, Model model) {

        Optional<User> user = userService.getUserByName(name);

        if (user.isPresent()) {
            User ab = user.get();

            model.addAttribute("accounts", ab.getAccounts().stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toSet()));
        } else {
            model.addAttribute("accounts", new ArrayList());
        }

        model.addAttribute("name", name);
        model.addAttribute("account", new AccountDto());

        return "accounts";
    }

    @PostMapping()
    public String accountSubmitted(@ModelAttribute AccountDto account, Model model) {

        AccountDto persistedAccount = accountService.createSavingsAccount(account);

        model.addAttribute("account", persistedAccount);
        return "accounts_submitted";
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView processRuntimeException(final HttpServletRequest req, final RuntimeException ex) {

        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", ex.getMessage());
//        mav.addObject("exception", ex);

        mav.setViewName("error");
        return mav;
    }
}
