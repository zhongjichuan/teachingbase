package com.teachingbase.controller.userController;

import com.teachingbase.domain.User;
import com.teachingbase.service.UserService;
import com.teachingbase.util.SessionContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/passwordUpdate")
    public String passwordUpdate() {
        return "userCenter/pwd-modify";
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String oldPassword,String newPassword){
        User user = SessionContextUtil.getCurrentUser();
        String message = "";
        String newUrl = "/user/passwordUpdate";
        if (oldPassword.equals(user.getPassword())){
            boolean result = userService.updatePasswordByUsername(user.getUsername(), newPassword);
            if (result){
                message = "密码修改成功。";
                newUrl = "/logout";
            }else {
                message = "密码修改失败。";
            }
        }else {
            message = "原始密码错误,请重新填写。";
        }
        return "<script>alert('"+message+"');location.href='"+newUrl+"'</script>";
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }

    @RequestMapping("/getUser/{username}")
    public String getUser(@PathVariable("username") String username, Model model){
        User user = userService.getUserByUsername(username);
        model.addAttribute("user",user);
        return "counselor/student_details";
    }

}
