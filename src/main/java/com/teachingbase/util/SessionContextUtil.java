package com.teachingbase.util;

import com.teachingbase.domain.User;
import org.apache.shiro.SecurityUtils;

public class SessionContextUtil {

    static public User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
