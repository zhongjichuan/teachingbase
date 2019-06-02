package com.teachingbase.config.shiro;

import com.teachingbase.dao.RoleMapper;
import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import com.teachingbase.domain.User;
import com.teachingbase.service.ResourceService;
import com.teachingbase.service.RoleService;
import com.teachingbase.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService  roleService;
    @Autowired
    private ResourceService resourceService;

    /*授权认证*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //参数principals:用户认证信息
        //SimpleAuthenticationInfo:认证方法返回封装认证信息中的第一个参数：用户信息（user）
        User user = (User)principals.getPrimaryPrincipal();
        String roleCode = roleService.getRoleCodeByUsername(user.getUsername());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前用户的菜单资源
        List<Tree<Resource>> treeList = resourceService.getResourceByUsername(user.getUsername());
        //将菜单资源存入session中。shiro的session和httpSession一致。
        SecurityUtils.getSubject().getSession().setAttribute("treeList",treeList);
        info.addRole(roleCode);
        return info;
    }

    /*身份认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();
        User user = userService.getUserByUsername(username);
        //1通过标识username查询数据库，是否存在该用户user.不存在返回null;存在则继续比对password
        if(user==null){
            return  null; //shiro底层会抛出UnKnowAccountException
        }
        //2为数据库密码，底层会再判断User的password与前台token中的password是否一致
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return info;
    }

    public String getName(){
        return "MyRealm";
    }

    //清除缓存
    //在角色或权限service中,delete或者update方法去调用realm的清除缓存方法.
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
