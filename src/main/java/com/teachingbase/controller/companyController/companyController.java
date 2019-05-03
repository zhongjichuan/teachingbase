package com.teachingbase.controller.companyController;


import com.teachingbase.domain.Base;
import com.teachingbase.domain.User;
import com.teachingbase.service.BaseService;
import com.teachingbase.util.SessionContextUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresRoles("manager")
@RequestMapping("/company")
@Controller
public class companyController {

    @Autowired
    public BaseService baseService;

    @RequestMapping("/baseManage")
    public String baseManage() {
        return "company/base_manage";
    }

    @RequestMapping(value = "/getBaseList",method = RequestMethod.GET)
    @ResponseBody
    public List getBaseList() {
        String company = SessionContextUtil.getCurrentUser().getCompany();
        List list = baseService.getBaseListByCompanyName(company);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }
    @RequestMapping(value = "/getBaseList",method = RequestMethod.POST)
    @ResponseBody
    public List getBaseListByParams(String baseId,String baseName,String teacherName) {
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        Map map = new HashMap();
        map.put("companyName", companyName);
        map.put("baseId",baseId);
        map.put("baseName",baseName);
        map.put("teacherName",teacherName);
        List list = baseService.getBaseListBySearchParams(map);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }



    @RequestMapping("/trainingTeachers")
    public String trainingTeachers() {
        return "company/teachers_manage";
    }
    @RequestMapping("/addTeacher")
    public String addTeacher() {
        return "company/addTeacher";
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
    public String baseDetails() {
        return "company/base_details";
    }
    @RequestMapping("/enrolledStudents")
    public String enrolledStudents() {
        return "company/enrolledStudents";
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
