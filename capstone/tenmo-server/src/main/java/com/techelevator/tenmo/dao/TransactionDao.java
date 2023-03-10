package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {

    boolean create(int senderId, int recipientId, BigDecimal transferAmt);
    //   List<User> findAll();
  //  User getUserById(int id);
  //  User findByUsername(String username);
  //  int findIdByUsername(String username);
  //  boolean create(String username, String password);


}
