package gr.aueb.cf.accountexceptions.dao;

import gr.aueb.cf.accountexceptions.dao.OverdraftAccount;
import gr.aueb.cf.accountexceptions.dao.JointAccount;
import gr.aueb.cf.accountexceptions.dao.Account;
import gr.aueb.cf.accountexceptions.exceptions.InsufficientAmountException;
import gr.aueb.cf.accountexceptions.exceptions.InsufficientBalanceException;
import gr.aueb.cf.accountexceptions.exceptions.SsnNotValidException;

public class BankApp {

    public static void main(String[] args) throws InsufficientAmountException, InsufficientBalanceException, SsnNotValidException {

        User bob = new User("Bob", "Marley", "CD123");
        User alice = new User("Alice","Wonderland","AB123");

        Account accountAlice = new Account("GR123", alice,1000);
        Account accountBob = new Account("GR456", bob, 3000);
        OverdraftAccount overdraftAccount = new OverdraftAccount("GR456",alice,2000);
        JointAccount jointAccount = new JointAccount(alice, bob,"GR457",1500 );

        accountAlice.deposit(500.0);
        accountBob.withdraw(2000,"CD123");
        overdraftAccount.withdraw(7000,"AB123");
        jointAccount.withdraw(1000,"AB123");


        System.out.println(accountAlice.toString());
        System.out.println(accountBob.toString());
        System.out.println(overdraftAccount.toString());
        System.out.println(jointAccount.toString());

    }
}
