package com.estsoft.demo.tdd;

public class Account {
    private int balance;

    public Account(int money) {
        balance = money;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int money) {
        balance = balance + money;
    }

    public void withdraw(int money) {
        if (balance < money) {
            throw new RuntimeException("출금 실패! 기존 잔액:" + balance + ", 출금 요청 금액:" + money);
        }
        balance = balance - money;
    }
}