package gr.aueb.cf.accountexceptions.dao;

import gr.aueb.cf.accountexceptions.exceptions.InsufficientBalanceException;
import gr.aueb.cf.accountexceptions.exceptions.SsnNotValidException;

public class OverdraftAccount extends Account {

    public OverdraftAccount() {}

    public OverdraftAccount(String iban, User holder, double balance) {
        super(iban, holder, balance);
    }

    @Override
    public void withdraw(double amount, String ssn) throws InsufficientBalanceException, SsnNotValidException {
        try {
            if (!isSsnValid(ssn)) {
                throw new SsnNotValidException(ssn);
            }

            setBalance(getBalance() - amount);

        } catch (SsnNotValidException e) {
            System.err.println("Error: withdrawal");
            throw e;
        }

    }

    @Override
    public String toString() {
        return "Overdraft" + super.toString();
    }
}
