package com.example.accountmanager;

public class Account {
    public String id, name, balance, date, currency;

    public Account() {
    }

    public Account(String id, String name, String balance, String date, String currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.date = date;
        this.currency = currency;
    }
}
