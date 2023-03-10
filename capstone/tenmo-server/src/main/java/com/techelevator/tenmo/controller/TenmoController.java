package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoController {
    private UserDao userDao;
    private AccountDao accountDao;
    private TransactionDao transactionDao;

    public TenmoController(UserDao userDao, AccountDao accountDao, TransactionDao transactionDao) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transactionDao = transactionDao;
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

    @RequestMapping (path = "/users", method = RequestMethod.GET)
    public List<User> listUsers() {return userDao.findAll();}

    @ResponseStatus (HttpStatus.CREATED)
    @RequestMapping (path = "/transfer", method = RequestMethod.POST)
    public void transfer(@Valid @RequestBody TransactionDto newTransaction, Principal currentUser) {

        final User sender = userDao.findByUsername(currentUser.getName());
        final Account senderAccount = accountDao.getAccountByUserId(sender.getId());
        final User recipient = userDao.findByUsername(newTransaction.getRecipientName());
        final Account recipientAccount = accountDao.getAccountByUserId(recipient.getId());
        final BigDecimal transferAmt = newTransaction.getTransferAmt();

        if(currentUser.getName().equalsIgnoreCase(recipient.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You can't send money to yourself, ya dingus!");
        }
        if(senderAccount.getBalance().compareTo(transferAmt) == -1) {;
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are too poor to send this amount, peasant!");
        }
        if(transferAmt.compareTo(BigDecimal.ZERO) != 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfers must be greater than zero!");
        }

        transactionDao.create(sender.getId(), recipient.getId(), transferAmt);
        accountDao.withdraw(sender.getId(), transferAmt, senderAccount.getBalance());
        accountDao.deposit(recipient.getId(), transferAmt, recipientAccount.getBalance());

    }





}
