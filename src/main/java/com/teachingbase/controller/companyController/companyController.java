package com.teachingbase.controller.companyController;


import com.teachingbase.domain.*;
import com.teachingbase.service.*;
import com.teachingbase.util.SessionContextUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiresRoles("manager")
@RequestMapping("/company")
@Controller
public class companyController {

    @Autowired
    public BaseService baseService;
    @Autowired
    public TeacherService teacherService;
    @Autowired
    public UserService userService;
    @Autowired
    public CollegeService collegeService;
    @Autowired
    public CompanyService companyService;

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

    /**
     * 跳转到添加基地页面
     * @param model
     * @return
     */
    @RequestMapping("/addBase")
    public String addBase(Model model) {
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        List teachers = baseService.getBaseTeachersByCompanyName(companyName);
        String baseIdMax = baseService.getMaxBaseId();
        int i = Integer.parseInt(baseIdMax);
        int baseId = i + 1;
        List<College> collegeList = collegeService.getCollegeList();

        model.addAttribute("companyName",companyName);
        model.addAttribute("teachers",teachers);
        model.addAttribute("collegeList",collegeList);
        model.addAttribute("baseId",baseId);
        return "company/add_base";
    }

    /**
     * 添加基地将会对多张表进行insert,使用事务管理
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/saveBase")
    public boolean saveBase(Base base,String teacherId,String collegeId) throws ParseException {
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        Company company = companyService.getCompanyByCompanyName(companyName);
        boolean result = baseService.addBaseByManager(base,collegeId,teacherId,company.getCompanyId());
        return true;
    }

    @RequestMapping("/editBase/{baseId}")
    public String editBase(@PathVariable("baseId")String baseId,Model model) {
        Base base = baseService.getBaseByBaseId(baseId);
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        List teachers = baseService.getBaseTeachersByCompanyName(companyName);
        model.addAttribute("base",base);
        model.addAttribute("teachers",teachers);
        return "company/edit_base";
    }

    @ResponseBody
    @RequestMapping(value = "/updateBase",method = RequestMethod.POST)
    public boolean updateBase(Base base,String teacherId) {
        System.out.println(base+teacherId);
        if (base.getBaseStatus()==null){
            base.setBaseStatus("0");
        }
        boolean b = baseService.updateBase(base, teacherId);
        return b;
    }


    /************实训教师********************/
    @RequestMapping("/trainingTeachers")
    public String trainingTeachers() {
        return "company/teachers_manage";
    }

    @RequestMapping("/getTeacherList")
    @ResponseBody
    public List getTeacherList() {
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        List list = teacherService.getTeacherListByCompany(companyName);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @RequestMapping(value = "/getTeacherList",method = RequestMethod.POST)
    @ResponseBody
    public List getTeacherListByParams(Teacher teacher) {
        String companyName = SessionContextUtil.getCurrentUser().getCompany();
        Map map = new HashMap();
        map.put("companyName",companyName);
        map.put("teacherId",teacher.getTeacherId());
        map.put("teacherName",teacher.getTeacherName());
        map.put("teacherPosition",teacher.getTeacherPosition());
        List list = teacherService.getTeacherListByParams(map);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @ResponseBody
    @RequestMapping("/getMaxTeacherId")
    public int getMaxTeacherId() {
        int id = teacherService.getMaxTeacherId();
        return id+1;
    }

    @ResponseBody
    @RequestMapping("/saveTeacher")
    public boolean saveTeacher(Teacher teacher) {
        boolean b = teacherService.addTeacher(teacher);
        return b;
    }
    @ResponseBody
    @RequestMapping("/editTeacher")
    public boolean editTeacher(Teacher teacher) {
        boolean b = teacherService.updateTeacher(teacher);
        return b;
    }

    @PostMapping("/modifyPassword")
    @ResponseBody
    public boolean modifyPassword(String username,String password){
        boolean result = userService.updatePasswordByUsername(username, password);
        return result;
    }

    @PostMapping("/delTeacher/{teacherId}")
    @ResponseBody
    public boolean delTeacher(@PathVariable("teacherId")String teacherId){
        boolean result = teacherService.delTeacher(teacherId);
        return result;
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
