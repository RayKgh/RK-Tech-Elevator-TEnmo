package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {
    boolean create(int userId);

    Account getAccountById(int userId);
    BigDecimal getAccountBalance(int userId);


}
