package com.android.transactionhistoryexplorer.domain.model;

public class TransactionDetail {
    private int id;
    private String transactionCode;
    private String transactionHash;
    private double amount;
    private String currency;
    private double fee;
    private double balanceAfter;
    private String senderName;
    private String receiverName;
    private String provider;
    private long timestamp;
    private String note;
    private int status;//(0: Pending, 1:Success, 2: Failed)
    private String category;
    private String deviceId;
    private String ipAddress;
    private String location;

    public TransactionDetail(int id, String transactionCode, String transactionHash, double amount, String currency, double fee, double balanceAfter, String senderName, String receiverName, String provider, long timestamp, String note, int status, String category, String deviceId, String ipAddress, String location) {
        this.id = id;
        this.transactionCode = transactionCode;
        this.transactionHash = transactionHash;
        this.amount = amount;
        this.currency = currency;
        this.fee = fee;
        this.balanceAfter = balanceAfter;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.provider = provider;
        this.timestamp = timestamp;
        this.note = note;
        this.status = status;
        this.category = category;
        this.deviceId = deviceId;
        this.ipAddress = ipAddress;
        this.location = location;
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

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
