package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcTransactionDao implements TransactionDao{





    @Override
    public User getUserById(int userId) {
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToUser(results);
        } else {
            return null;
        }
    }


}
