package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class JdbcTransactionDao implements TransactionDao{

    private final JdbcTemplate jdbcTemplate;
    public JdbcTransactionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(int senderId, int recipientId, BigDecimal transferAmt) {
        String sql = "INSERT INTO transaction(\n" +
                "sender_id, recipient_id, transfer_amt)\n" +
                "VALUES (?, ?, ?) RETURNING transaction_id;";

        Integer newTransactionId;
        newTransactionId = jdbcTemplate.queryForObject(sql, Integer.class, senderId, recipientId, transferAmt);

        return (newTransactionId != null);
    }






}
