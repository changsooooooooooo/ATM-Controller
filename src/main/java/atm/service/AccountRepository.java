package atm.service;

import atm.data.entity.Account;
import atm.data.entity.AccountBank;
import atm.data.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByAccountBankAndCard(AccountBank accountBank, Card card);
}
