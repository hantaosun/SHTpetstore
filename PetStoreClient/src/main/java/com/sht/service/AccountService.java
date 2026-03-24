package com.sht.service;

import com.sht.mapper.AccountMapper;
import com.sht.pojo.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService {

  private final AccountMapper accountMapper;

  public AccountService(AccountMapper accountMapper) {
    this.accountMapper = accountMapper;
  }

  public Account getAccount(String username) {
    return accountMapper.getAccountByUsername(username);
  }

  public Account getAccount(String username, String password) {
    return accountMapper.getAccountByUsernameAndPassword(username, password);
  }

  /**
   * Insert account.
   *
   * @param account
   *          the account
   */
  @Transactional
  public void insertAccount(Account account) {
    accountMapper.insertAccount(account);
    accountMapper.insertProfile(account);
    accountMapper.insertSignon(account);
  }

  /**
   * Update account.
   *
   * @param account
   *          the account
   */
  @Transactional
  public void updateAccount(Account account) {
    accountMapper.updateAccount(account);
    accountMapper.updateProfile(account);

    Optional.ofNullable(account.getPassword()).filter(password -> password.length() > 0)
        .ifPresent(password -> accountMapper.updateSignon(account));
  }

}