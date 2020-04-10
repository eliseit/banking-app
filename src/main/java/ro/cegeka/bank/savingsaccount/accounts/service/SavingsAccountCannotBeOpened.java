package ro.cegeka.bank.savingsaccount.accounts.service;

public class SavingsAccountCannotBeOpened extends RuntimeException {
    public SavingsAccountCannotBeOpened(String message) {
        super(message);
    }
}
