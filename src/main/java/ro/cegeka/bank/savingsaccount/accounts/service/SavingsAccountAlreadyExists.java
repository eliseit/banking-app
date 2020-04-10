package ro.cegeka.bank.savingsaccount.accounts.service;

public class SavingsAccountAlreadyExists extends RuntimeException {
    public SavingsAccountAlreadyExists(String message) {
        super(message);
    }
}
