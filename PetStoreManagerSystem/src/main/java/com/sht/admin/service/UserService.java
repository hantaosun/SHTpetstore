package com.sht.admin.service;

import com.sht.admin.mapper.AccountMapper;
import com.sht.admin.pojo.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountMapper accountMapper;

    public List<Account> listAll() {
        return accountMapper.listAll();
    }

    public List<Account> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return accountMapper.listAll();
        }
        return accountMapper.search(keyword);
    }

    public Account findByUsername(String username) {
        return accountMapper.findByUsername(username);
    }

    public void update(Account account) {
        accountMapper.update(account);
    }

    public void resetPassword(String username, String newPassword) {
        accountMapper.resetPassword(username, newPassword);
    }

    public long count() {
        return accountMapper.count();
    }
}
