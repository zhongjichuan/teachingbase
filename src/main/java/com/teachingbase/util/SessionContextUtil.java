package com.teachingbase.util;

import com.teachingbase.domain.User;
import org.apache.shiro.SecurityUtils;

public class SessionContextUtil {

    /**
     * 获得当前用户
     * @return
     */
    static public User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    /**
     * 判断当前用户是否拥有某个角色
     * @param roleString
     * @return
     */
    public static boolean isHasRole(String roleString){
        return SecurityUtils.getSubject().hasRole(roleString);
    }
}
