package atm.service;

import atm.data.entity.Account;
import atm.data.entity.AccountBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    void setUp() {
        account = accountRepository.findAccountById(1L)
                .orElse(new Account());
    }

    @Test
    @DisplayName("Find By Id Test")
    void findByIdTest() {
        assertEquals(AccountBank.SHINHAN, account.getAccountBank());
    }
}
