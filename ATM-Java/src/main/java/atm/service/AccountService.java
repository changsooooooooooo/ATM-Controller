package atm.service;

import atm.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account findById(long id) {
        return accountRepository.findById(id)
                .orElse(new Account());
    }

}
