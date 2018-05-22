package com.sda.spring.service;

import com.sda.spring.entity.Account;

import java.util.List;

public interface AccountService {

    Account getAccountById(Integer id);
    List<Account> getAllAccounts();
    void save(Account account);
    void updateAccount(Account account);
    void deleteAccountById(Integer id);
}
