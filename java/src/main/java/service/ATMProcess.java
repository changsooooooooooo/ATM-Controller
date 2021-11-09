package service;

public interface ATMProcess {

    boolean isCorrectPinNumber(long pinNumber);
    long doShowBalanceProcess(long id);
    long doDepositProcess(long id, long money);
    long doWithdrawProcess(long id, long money);
}
