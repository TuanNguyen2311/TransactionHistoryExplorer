package com.android.transactionhistoryexplorer.domain.model;

public class TransactionSummary {
    private int id;
    private String transactionCode;
    private double amount;
    private String currency;
    private String senderName;
    private String receiverName;
    private long timestamp;
    private String note;
    private int status;


    public TransactionSummary(int id, String transactionCode, double amount, String currency, String senderName, String receiverName, long timestamp, String note, int status) {
        this.id = id;
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.currency = currency;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.timestamp = timestamp;
        this.note = note;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
