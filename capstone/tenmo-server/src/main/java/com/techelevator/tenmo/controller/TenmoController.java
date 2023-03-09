package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AccountDto;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {
    private UserDao userDao;
    private AccountDao accountDao;

    public TenmoController(UserDao userDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @GetMapping(path = "/account")
    public AccountDto getAccount(Principal currentUser) {

        final User user = userDao.findByUsername(currentUser.getName());
        final Account account = accountDao.getAccountByUserId(user.getId());
        if (account == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The current user does not have an account");
        }

        final AccountDto accountDto = new AccountDto();
        accountDto.setUserId(account.getUserId());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
}
