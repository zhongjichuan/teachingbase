package com.teachingbase.service;

import com.teachingbase.domain.CollegeBase;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;

public interface UserService {

    public User getUserByUsername(String username);
    public boolean updatePasswordByUsername(String username,String password);
    public boolean updateUser(User user);
    public CollegeBase getCollegeByCurrentUser(User user);
}
