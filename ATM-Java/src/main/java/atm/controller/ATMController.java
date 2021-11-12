package atm.controller;

import atm.Exception.NoMatchingAccount;
import atm.Exception.NotCorrectPW;
import atm.data.dto.ApiFormat;
import atm.data.dto.PostRequestBody;
import atm.data.entity.Account;
import atm.data.entity.Card;
import atm.service.AccountService;
import atm.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Validated
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ATMController {

    private final CardService cardService;
    private final AccountService accountService;

    @PostMapping("/show")
    public ResponseEntity<ApiFormat> getAccount(
            @RequestParam String id,
            @Valid @RequestBody PostRequestBody postRequestBody
            ) throws Exception {

        log.info("request body : {}", postRequestBody.getAccountBank());

        Card card = cardService.isCorrectPinNumber(id, postRequestBody.getPinNumber())
                .orElseThrow(()->new NotCorrectPW("Not Correct PassWord"));

        Account account = accountService.findAccount(postRequestBody.getAccountBank(), card)
                .orElseThrow(()->new NoMatchingAccount("No Matching Account For RequestBody"));

        ApiFormat.Response res = ApiFormat.Response.builder()
                .cardId(id)
                .accountBank(account.getAccountBank())
                .currentMoney(account.getBalance())
                .build();

        return new ResponseEntity<>(
                new ApiFormat(true, res),
                HttpStatus.OK
        );
    }

    @PostMapping("/deposit")
    public ResponseEntity<ApiFormat> doDeposit(
            @RequestParam String id,
            @Valid @RequestBody PostRequestBody postRequestBody
    ) throws Exception {

        Card card = cardService.isCorrectPinNumber(id, postRequestBody.getPinNumber())
                .orElseThrow(()->new NotCorrectPW("Not Correct PassWord"));

        Account account = accountService.updateBalance(postRequestBody.getAccountBank(),
                card,
                postRequestBody.getMoney());


        ApiFormat.Response res = ApiFormat.Response.builder()
                .cardId(id)
                .accountBank(account.getAccountBank())
                .currentMoney(account.getBalance())
                .build();

        return new ResponseEntity<>(
                new ApiFormat(true, res),
                HttpStatus.OK
        );
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiFormat> doWithdraw(
            @RequestParam String id,
            @Valid @RequestBody PostRequestBody postRequestBody
    ) throws Exception {

        Card card = cardService.isCorrectPinNumber(id, postRequestBody.getPinNumber())
                .orElseThrow(()->new NotCorrectPW("Not Correct PassWord"));

        Account account = accountService.updateBalance(postRequestBody.getAccountBank(),
                card,
                -postRequestBody.getMoney());

        ApiFormat.Response res = ApiFormat.Response.builder()
                .cardId(id)
                .accountBank(account.getAccountBank())
                .currentMoney(account.getBalance())
                .build();

        return new ResponseEntity<>(
                new ApiFormat(true, res),
                HttpStatus.OK
        );
    }
}
