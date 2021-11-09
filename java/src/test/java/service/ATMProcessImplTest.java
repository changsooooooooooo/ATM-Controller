package service;

import data.dto.Account;
import data.dto.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ATMProcessImplTest {

    private Card card;

    @BeforeEach
    void setUp() {
        card = Card.makeCard(1234l);
        card.addAccount(Account.makeAccount(1l, 0l));
        card.addAccount(Account.makeAccount(2l, 0l));
    }

    @Test
    @DisplayName("Is Correct Pin Number Test")
    void testIsCorrectPinNumber() {
        assertEquals(true, card.getPinNumber() == 1234l);
        assertEquals(false, card.getPinNumber() == 123l);
    }

    @Test
    @DisplayName("Show Balance Test")
    void doShowBalanceProcessTest() {
        long money = card.getAccountList().stream()
                .filter(account -> account.getId() == 1l)
                .map(account -> account.getBalance())
                .collect(Collectors.toList())
                .get(0);

        assertEquals(0l, money);
    }

    @Test
    @DisplayName("Deposit Test")
    void doDepositProcessTest() {
        long money = card.getAccountList().stream()
                .filter(account -> account.getId() == 1l)
                .map(account -> account.deposit(30l))
                .collect(Collectors.toList())
                .get(0);

        assertEquals(30l, money);
    }

    @Test
    @DisplayName("Withdraw Test")
    void doWithdrawTest() {

        long depositedMoney = card.getAccountList().stream()
                .filter(account -> account.getId() == 1l)
                .map(account -> account.deposit(70l))
                .collect(Collectors.toList())
                .get(0);

        long currentMoney = card.getAccountList().stream()
                .filter(account -> account.getId() == 1l)
                .map(account -> account.withdraw(30l))
                .collect(Collectors.toList())
                .get(0);

        assertEquals(70l, depositedMoney);
        assertEquals(40l, currentMoney);
    }

}
