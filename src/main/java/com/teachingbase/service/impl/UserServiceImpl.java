package com.teachingbase.service.impl;

import com.teachingbase.dao.UserMapper;
import com.teachingbase.domain.CollegeBase;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;
import com.teachingbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }

    @Override
    public boolean updatePasswordByUsername(String username, String password) {
        int result = userMapper.updatePasswordByUsername(username, password);
        if (result > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        int result = userMapper.updateUser(user);
        if (result > 0){
            return true;
        }
        return false;
    }

    @Override
    public CollegeBase getCollegeByCurrentUser(User user){
        return userMapper.getCollegeByCurrentUser(user);
    }


}
