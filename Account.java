package gr.aueb.cf.accountexceptions.dao;

import gr.aueb.cf.accountexceptions.exceptions.InsufficientAmountException;
import gr.aueb.cf.accountexceptions.exceptions.InsufficientBalanceException;
import gr.aueb.cf.accountexceptions.exceptions.SsnNotValidException;



/**
 * defines a class Account with business Logic
 */
public class Account extends IdentifiableEntity {

    private String iban;
    private User holder;
    private double balance;

    public Account() {}

    public Account(String iban, User holder, double balance) {
        this.iban = iban;
        this.holder = holder;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public User getHolder() {
        return new User(holder);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Business Logic

    public void deposit(double amount) throws  InsufficientAmountException {
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
        if ((ssn == null) || (holder.getSsn() == null)) {
            return false;
        }
        return holder.getSsn().equals(ssn);
    }



    /**
     * Gets the balance's amount of money.
     *
     * @return  the balance
     */
    public double getAccountBalance() {
        return getBalance();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + getId() +
                ", iban='" + iban + '\'' +
                ", lastname='" + holder.getLastname() + '\'' +
                ", firstname='" + holder.getFirstname() + '\'' +
                ", ssn='" + holder.getSsn() + '\'' +
                ", balance=" + balance +
                '}';
    }

}
