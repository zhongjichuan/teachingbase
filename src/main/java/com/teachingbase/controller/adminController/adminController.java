package com.teachingbase.controller.adminController;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import com.teachingbase.domain.User;
import com.teachingbase.service.AdminService;
import com.teachingbase.service.ResourceService;
import com.teachingbase.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiresRoles("admin")
public class adminController {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    ResourceService resourceService;

    @RequestMapping("/counselorManage")
    public String counselorManage(Model model) {
        List colleges = new ArrayList();
        colleges = adminService.getColleges();
        model.addAttribute("colleges",colleges);
        return "admin/counselor_manage";
    }

    @ResponseBody
    @RequestMapping(value = "/getCounselorList",method = RequestMethod.GET)
    public List getCounselorList(Model model) {
        List list = null;
        list = adminService.getCounselorList();
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }
    @ResponseBody
    @RequestMapping(value = "/getCounselorList",method = RequestMethod.POST)
    public List getCounselorList(String username,String name,String college) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("name",name);
        map.put("college",college);
        System.out.println(map);
        List list = adminService.getCounselorListBySearchParams(map);
        if (list.size()<=0||list == null){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }
    @ResponseBody
    @GetMapping("/getMaxUsernameOfCounselor")
    public Integer getMaxUsernameOfCounselor(){
        Integer newUsername = Integer.parseInt(adminService.getMaxUsernameOfCounselor())+1;
        return newUsername;
    }

    @PostMapping("/addCounselor")
    @ResponseBody
    public String addCounselor(User user){
        if (user.getName()==null||user.getName()==""||user.getPhone()==null||user.getPhone()==""||user.getCollege()==null||user.getCollege()==""){
            return "false";
        }
        int result = adminService.addCounselor(user);
        String flag = "";
        if (result>0){
            flag = "true";
        }else {
            flag = "false";
        }
        return flag;
    }

    @PostMapping("/delCounselors/{username}")
    @ResponseBody
    public boolean delCounselors(@PathVariable("username") String username){
        int result = adminService.delCounselors(username);
        if(result > 0){
            return true;
        }
        return false;
    }

    @GetMapping("/getUser/{username}")
    @ResponseBody
    public User getUserByUsername(@PathVariable("username") String username){
        User user = userService.getUserByUsername(username);
        return user;
    }
    @PostMapping("/editCounselor")
    @ResponseBody
    public boolean editCounselor(User user){
        if (user.getName()==null||user.getName()==""||user.getPhone()==null||user.getPhone()==""||user.getCollege()==null||user.getCollege()==""){
            return false;
        }
        boolean flag = userService.updateUser(user);
        return flag;
    }

    @PostMapping("/modifyPassword")
    @ResponseBody
    public boolean modifyPassword(String username,String password){
        boolean result = userService.updatePasswordByUsername(username, password);
        return result;
    }
    @RequestMapping("/companyManage")
    public String companyManage(Model model) {
        List companies = new ArrayList();
        companies = adminService.getCompanies();
        model.addAttribute("companies",companies);
        return "admin/company_manage";
    }

    @ResponseBody
    @RequestMapping(value = "/getManagerList",method = RequestMethod.GET)
    public List getManagerList(Model model) {
        List list = null;
        list = adminService.getManagerList();
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getManagerList",method = RequestMethod.POST)
    public List getManagerList(String username,String name,String company) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("name",name);
        map.put("company",company);
        System.out.println(map);
        List list = adminService.getManagerListBySearchParams(map);
        if (list.size()<=0||list == null){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @ResponseBody
    @GetMapping("/getMaxUsernameOfManager")
    public Integer getMaxUsernameOfManager(){
        Integer newUsername = Integer.parseInt(adminService.getMaxUsernameOfManager())+1;
        return newUsername;
    }

    @PostMapping("/addManager")
    @ResponseBody
    public String addManager(User user){
        if (user.getName()==null||user.getName()==""||user.getPhone()==null||user.getPhone()==""||user.getCompany()==null||user.getCompany()==""){
            return "false";
        }
        int result = adminService.addManager(user);
        String flag = "";
        if (result>0){
            flag = "true";
        }else {
            flag = "false";
        }
        return flag;
    }

    @PostMapping("/editManager")
    @ResponseBody
    public boolean editManager(User user){
        if (user.getName()==null||user.getName()==""||user.getPhone()==null||user.getPhone()==""||user.getCompany()==null||user.getCompany()==""){
            return false;
        }
        boolean flag = userService.updateUser(user);
        return flag;
    }

    @PostMapping("/delManager/{username}")
    @ResponseBody
    public boolean delManager(@PathVariable("username") String username){
        int result = adminService.delCounselors(username);
        if(result > 0){
            return true;
        }
        return false;
    }

    @RequestMapping("/tree")
    public String tree(){
        return "admin/tree";
    }

    @RequestMapping("/getNodes")
    @ResponseBody
    public Object getNodes(String username){
        List<Tree<Resource>> treeList = resourceService.getAllResource();
        return treeList;
    }

}
