package data.dto;

public class Account {

    private long id;
    private long balance;

    private Account(long id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public static Account makeAccount(final long id, final long balance) {
        return new Account(id, balance);
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public long withdraw(final long money) {
        balance -= money;
        return balance;
    }

    public long deposit(final long money) {
        balance += money;
        return balance;
    }
}
