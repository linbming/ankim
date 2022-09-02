package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Override
    public boolean judgeUser(String username, String password) {
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        User user = mapper.selectUser(username, password);
        if(user == null){
            return false;
        }
        return true;
    }
}
