package atm.service;

import atm.Exception.NoMatchingAccount;
import atm.data.entity.Account;
import atm.data.entity.AccountBank;
import atm.data.entity.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccount(AccountBank accountBank, Card card) {
        return accountRepository.findAccountByAccountBankAndCard(accountBank, card);
    }

    @Transactional
    public Account updateBalance(AccountBank accountBank, Card card, Long money) throws Exception {
        Account account = accountRepository.findAccountByAccountBankAndCard(accountBank, card)
                .orElseThrow(()->new NoMatchingAccount("No Matching Account For RequestBody"));
        account.updateBalance(money);
        return account;
    }

}
