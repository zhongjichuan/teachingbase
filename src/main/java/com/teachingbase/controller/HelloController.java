package com.teachingbase.controller;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import com.teachingbase.domain.User;
import com.teachingbase.service.ResourceService;
import com.teachingbase.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {

        return "userCenter/index";
    }


    @RequestMapping("/test")
    @ResponseBody
    public List<Tree<Resource>> test(Model model) {
       return resourceService.getResourceByUsername("100001");
    }


    @RequestMapping("/authorize")
    @RequiresPermissions("student:select")
    public String authorize(Model model) {
        return "test/add";
    }

    //authorize stest
    @RequestMapping("/addTest")
    public String add(){
        return "test/add";
    }
    @RequestMapping("/updateTest")
    public String update(){
        return "test/update";
    }
    @RequestMapping("/unauthorizedUrl")
    public String unauthorizedUrl(){
        return "error/unauthorized";
    }

    @RequestMapping("/passwordUpdate")
    public String passwordUpdate() {
        return "userCenter/pwd-modify";
    }
    @RequestMapping("/signUp")
    public String enter() {
        return "student/signUp";
    }
    @RequestMapping("/studentManage")
    public String studentManage() {
        return "counselor/student_manage";
    }
    @RequestMapping("/teachingBase")
    public String teachingBase() {
        return "counselor/teachingBase";
    }
    @RequestMapping("/trainingTeachers")
    public String trainingTeachers() {
        return "company/teachers_manage";
    }
    @RequestMapping("/addTeacher")
    public String addTeacher() {
        return "company/addTeacher";
    }
    @RequestMapping("/baseManage")
    public String baseManage() {
        return "company/base_manage";
    }
    @RequestMapping("/addBase")
    public String addBase() {
        return "company/add_base";
    }
    @RequestMapping("/editBase")
    public String editBase() {
        return "company/edit_base";
    }
    @RequestMapping("/baseDetails")
    public String baseDeatils() {
        return "company/base_details";
    }
    @RequestMapping("/enrolledStudents")
    public String enrolledStudents() {
        return "company/enrolledStudents";
    }
    @RequestMapping("/studentDetails")
    public String studentDetails() {
        return "counselor/student_details";
    }


    @RequestMapping("/teacherDetails")
    public String teacherDetails() {
        return "company/teacher_details";
    }
    @RequestMapping("/studentAttendance")
    public String studentAttendance() {
        return "company/student_attendance";
    }
    @RequestMapping("/registryManage")
    public String registryManage() {
        return "company/registry_manage";
    }


}
