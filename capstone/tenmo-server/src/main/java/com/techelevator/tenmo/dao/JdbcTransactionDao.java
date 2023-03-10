package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Transaction getTransactionsByTransactionId(int id) {
        Transaction transaction = null;
        String sql = "SELECT transaction_id, sender_id, recipient_id, transfer_amt, timestamp, is_requested, status FROM transaction WHERE transaction_id = ?;";
        final SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            transaction = mapRowToTransaction(result);
        }

        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int id) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        String sql = "SELECT transaction_id, sender_id, recipient_id, transfer_amt, timestamp, is_requested, status FROM transaction WHERE sender_id = ? OR recipient_id = ?;";
        final SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id, id);
        while (results.next()) {
            transactions.add(mapRowToTransaction(results));
        }
        return transactions;
    }

    private Transaction mapRowToTransaction(SqlRowSet rowSet) {
        final Transaction transaction = new Transaction(
                rowSet.getInt("transaction_id"),
                rowSet.getInt("sender_id"),
                rowSet.getInt("recipient_id"),
                rowSet.getBigDecimal("transfer_amt"),
                rowSet.getTimestamp("timestamp").toLocalDateTime(),
                rowSet.getBoolean("is_requested"),
                rowSet.getInt("status")
        );
        return transaction;
    }


}
