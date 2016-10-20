package com.tom.atm;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Transaction {
    String date;
    int amount;
    int type;
    public Transaction(){

    }

    public Transaction(String date, int amount, int type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
