package com.chandu.ecommorce.entity;


public class TransactionDetails {
    private String orderID;
    private  String currency;
    private  Integer amount;
    private  String key;

    public TransactionDetails(String orderID, String currency, Integer amount, String key) {
        this.orderID = orderID;
        this.currency = currency;
        this.amount = amount;
        this.key = key;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
