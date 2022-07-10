package gr.aueb.cf.accountexceptions.dao;

import gr.aueb.cf.accountexceptions.exceptions.InsufficientAmountException;
import gr.aueb.cf.accountexceptions.exceptions.InsufficientBalanceException;
import gr.aueb.cf.accountexceptions.exceptions.SsnNotValidException;

public class JointAccount extends IdentifiableEntity {
    private User firstHolder;
    private User secondHolder;
    private String iban;
    private double balance;

    public JointAccount() {}

    public JointAccount(User firstHolder, User secondHolder, String iban, double balance) {
        this.firstHolder = firstHolder;
        this.secondHolder = secondHolder;
        this.iban = iban;
        this.balance = balance;
    }

    public User getFirstHolder() {
        return new User(firstHolder);
    }

    public User getSecondHolder() {
        return new User(secondHolder);
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Business Logic

    public void deposit(double amount) throws InsufficientAmountException {
        try {
            if (amount > 0) {
                balance += amount;
            } else {
                throw new InsufficientAmountException(amount);
            }
        } catch (InsufficientAmountException e) {
            System.err.println("Error: Insufficient amount");
            throw e;
        }

    }

    public void withdraw(double amount, String ssn) throws InsufficientBalanceException, SsnNotValidException {
        try {
            if (!isSsnValid(ssn)) {
                throw new SsnNotValidException(ssn);
            }

            if (amount <= balance) {
                balance -= amount;
            } else {
                throw new InsufficientBalanceException(balance,amount);
            }
        } catch (InsufficientBalanceException | SsnNotValidException e) {
            System.err.println("Error: withdrawal");
            throw e;
        }


    }

    protected boolean isSsnValid (String ssn) {
        if ((ssn == null) || (firstHolder.getSsn() == null) || (secondHolder.getSsn() == null)) {
            return false;
        }
        return firstHolder.getSsn().equals(ssn) || secondHolder.getSsn().equals(ssn);
    }


    @Override
    public String toString() {
        return "JointAccount{" +
                "firstHolder=" + firstHolder +
                ", secondHolder=" + secondHolder +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * Gets the balance's amount of money.
     *
     * @return  the balance
     */
    public double getAccountBalance() {
        return getBalance();
    }

}
