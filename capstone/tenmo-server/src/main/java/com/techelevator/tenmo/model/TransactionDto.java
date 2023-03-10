package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class TransactionDto {
    @NotEmpty
    private String recipientName;
    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than zero")
    private BigDecimal transferAmt;
    private int transactionId;

    public TransactionDto(String recipientName, BigDecimal transferAmt, int transactionId) {
        this.recipientName = recipientName;
        this.transferAmt = transferAmt;
        this.transactionId = transactionId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public BigDecimal getTransferAmt() {
        return transferAmt;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setTransferAmt(BigDecimal transferAmt) {
        this.transferAmt = transferAmt;
    }

    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }


}
