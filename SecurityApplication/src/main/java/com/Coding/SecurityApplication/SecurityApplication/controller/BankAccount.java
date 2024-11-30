package com.Coding.SecurityApplication.SecurityApplication.controller;

import java.util.Objects;

public class BankAccount {

    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }


    // Equals method implementation
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BankAccount otherAccount = (BankAccount) obj;
        return Objects.equals(accountNumber, otherAccount.accountNumber)
                && Objects.equals(accountHolderName, otherAccount.accountHolderName)
                && Double.compare(balance, otherAccount.balance) == 0;
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount("Savings", "Test", 2022);
        BankAccount account2 = new BankAccount("Savings", "Test", 2022);
        System.out.println(account1.equals(account2)); // returns true
    }
}
