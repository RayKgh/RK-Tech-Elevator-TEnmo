package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TenmoController {
    private UserDao userDao;
    private AccountDao accountDao;

    public TenmoController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping(path = "/{id}/balance")
    public AccountDto getBalanceById(@PathVariable int id) {
        final Account account = accountDao.getAccountById(id);
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no account with that ID");
        }
        final AccountDto accountDto = new AccountDto();
        accountDto.setUserId(account.getUserId());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
}
