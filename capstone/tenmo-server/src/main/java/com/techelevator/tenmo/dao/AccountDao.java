package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {
    boolean create(int userId);

    Account getAccountByUserId(int userId);
    BigDecimal getAccountBalance(int userId);

    void withdraw(int senderId, BigDecimal transferAmt, BigDecimal senderBalance);

    void deposit(int recipientId, BigDecimal transferAmt, BigDecimal recipientBalance);


}
