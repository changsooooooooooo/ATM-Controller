package data.dto;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private long pinNumber;
    private final List<Account> accountList = new ArrayList<>();

    private Card(long pinNumber) {
        this.pinNumber = pinNumber;
    }

    public static Card makeCard(final long pinNumber) {
        return new Card(pinNumber);
    }

    public void addAccount(final Account account) {
        accountList.add(account);
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public long getPinNumber() {
        return pinNumber;
    }
}
