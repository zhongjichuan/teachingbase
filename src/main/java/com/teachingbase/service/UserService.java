package com.teachingbase.service;

import com.teachingbase.domain.User;

public interface UserService {

    public User getUserByUsername(String username);
    public boolean updatePasswordByUsername(String username,String password);
    public boolean updateUser(User user);
}
