package com.teachingbase.controller.userController;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import com.teachingbase.domain.User;
import com.teachingbase.service.ResourceService;
import com.teachingbase.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;


    @RequestMapping("/toLogin")
    public String toLogin(){

        return "common/login";
    }

    @RequestMapping("/login")
    public String login(Model model, User user, @RequestParam(value="rememberMe",required=false)Boolean rememberMe, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        if(rememberMe!=null&&rememberMe){
            System.out.println("jizhuwo");
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
            //没有报错则身份验证通过
            User currentUser = (User) subject.getPrincipal();
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", currentUser); //把当前用户信息存到session中

        }catch (UnknownAccountException e){
            model.addAttribute("errorMsg","用户账号不存在");
            return "common/login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("errorMsg","用户账号/密码错误");
            return "common/login";
        }
        return "redirect:/index";
    }

//    @RequestMapping("/logout")
//    public String logout(){
//        SecurityUtils.getSubject().logout(); //shiro的手动退出
//        return "redirect:/toLogin";
//    }
}
