package atm.controller;

import atm.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ATMController {

    private final AccountService accountService;

    @PostMapping("")
    public String getAccountInfoById(
            @RequestParam("id") long id) {
        return accountService.findById(id)
                .toString();
    }
}
