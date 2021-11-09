package service;

import data.dto.Card;

import java.util.stream.Collectors;

public class ATMProcessImpl implements ATMProcess{

    private Card card;

    public ATMProcessImpl(Card card) {
        this.card = card;
    }

    public boolean isCorrectPinNumber(final long pinNumber) {
        if(card.getPinNumber() == pinNumber) {
            return true;
        }
        return false;
    }

    public long doShowBalanceProcess(final long id) {
        return card.getAccountList().stream()
                .filter(account->account.getId()==id)
                .map(account->account.getBalance())
                .collect(Collectors.toList())
                .get(0);
    }

    public long doDepositProcess(final long id, final long money) {
        return card.getAccountList().stream()
                .filter(account->account.getId()==id)
                .map(account->account.deposit(money))
                .collect(Collectors.toList())
                .get(0);
    }

    public long doWithdrawProcess(final long id, final long money) {
        return card.getAccountList().stream()
                .filter(account->account.getId()==id)
                .map(account->account.withdraw(money))
                .collect(Collectors.toList())
                .get(0);
    }
}
