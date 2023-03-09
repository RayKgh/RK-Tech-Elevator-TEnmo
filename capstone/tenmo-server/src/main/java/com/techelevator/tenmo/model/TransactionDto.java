package com.techelevator.tenmo.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class TransactionDto {
    @NotEmpty
    private String recipientName;
    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than zero")
    private BigDecimal transferAmt;

    public TransactionDto(String recipientName, BigDecimal transferAmt) {
        this.recipientName = recipientName;
        this.transferAmt = transferAmt;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public BigDecimal getTransferAmt() {
        return transferAmt;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setTransferAmt(BigDecimal transferAmt) {
        this.transferAmt = transferAmt;
    }


}
