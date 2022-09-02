package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public User selectUser(@Param("username") String username,@Param("password") String password);
}
