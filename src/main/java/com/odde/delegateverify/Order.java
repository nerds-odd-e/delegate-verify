package com.odde.delegateverify;

public class Order {

    private int id;
    private int amount;

    public Order(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
