package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User SelectUser(User user);
    int IUS(@Param("Email")String Email,@Param("Number") String Number);
    int IUT(@Param("Number") String Number);
    int changePassword(@Param("Email") String Email,@Param("Password") String Password);
}
