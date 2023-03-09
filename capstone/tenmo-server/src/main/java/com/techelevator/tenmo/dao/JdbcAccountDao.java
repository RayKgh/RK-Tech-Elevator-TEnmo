package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean create(int id) {

        // create user
        String sql = "INSERT INTO account (user_id, balance) VALUES (?, 1000) RETURNING account_id";

        Integer newAccountId;
        newAccountId = jdbcTemplate.queryForObject(sql, Integer.class, id);

        return (newAccountId != null);
    }

    @Override
    public Account getAccountByUserId(int userId) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account WHERE user_id = ?;";
        final SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public BigDecimal getAccountBalance(int userId) {
        Account account = null;
        String sql = "SELECT balance FROM account WHERE user_id = ?;";
        final SqlRowSet result = jdbcTemplate.queryForRowSet(sql, Integer.class, userId);
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account.getBalance();
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        final Account account = new Account(
                rowSet.getInt("account_id"),
                rowSet.getInt("user_id"),
                rowSet.getBigDecimal("balance"));
        return account;
    }
}
