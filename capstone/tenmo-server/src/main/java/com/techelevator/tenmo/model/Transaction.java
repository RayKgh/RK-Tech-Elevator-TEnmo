package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private int senderId;
    private int recipientId;
    private BigDecimal transfer_amt;
    private LocalDateTime timestamp;
    private boolean isRequest;
    private int status;

    public Transaction(int transactionId, int senderId, int recipientId, BigDecimal transfer_amt, LocalDateTime timestamp, boolean isRequest, int status) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.transfer_amt = transfer_amt;
        this.timestamp = timestamp;
        this.isRequest = isRequest;
        this.status = status;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public BigDecimal getTransfer_amt() {
        return transfer_amt;
    }

    public void setTransfer_amt(BigDecimal transfer_amt) {
        this.transfer_amt = transfer_amt;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRequest() {
        return isRequest;
    }

    public void setRequest(boolean request) {
        isRequest = request;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
