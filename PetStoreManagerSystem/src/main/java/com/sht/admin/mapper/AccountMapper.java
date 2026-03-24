package com.sht.admin.mapper;

import com.sht.admin.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AccountMapper {
    List<Account> listAll();
    List<Account> search(@Param("keyword") String keyword);
    Account findByUsername(@Param("username") String username);
    int update(Account account);
    int resetPassword(@Param("username") String username, @Param("password") String password);
    long count();
}
