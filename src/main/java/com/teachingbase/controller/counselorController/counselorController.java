package com.teachingbase.controller.counselorController;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.CollegeBase;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;
import com.teachingbase.service.*;
import com.teachingbase.util.SessionContextUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/counselor")
@RequiresRoles("counselor")
@Controller
public class counselorController {

    @Autowired
    public BaseService baseService;
    @Autowired
    public AdminService adminService;
    @Autowired
    public UserService userService;
    @Autowired
    public CompanyService companyService;

    @Autowired
    public ClassStudentService classStudentService;

    @Autowired
    public BaseStudentService baseStudentService;


    @RequestMapping("/baseManage")
    public String baseManage(Model model) {
        List<String> companies = adminService.getCompanies();
        model.addAttribute("companies",companies);
        return "counselor/counselor_base_manage";
    }

    @RequestMapping(value = "/getBaseList",method = RequestMethod.GET)
    @ResponseBody
    public List getBaseList() {
        String college = SessionContextUtil.getCurrentUser().getCollege();
        List list = baseService.getBaseListByCollegeName(college);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @RequestMapping(value = "/getBaseList",method = RequestMethod.POST)
    @ResponseBody
    public List getBaseListByParams(String baseId,String baseName,String companyName) {
        String college = SessionContextUtil.getCurrentUser().getCollege();
        Map map = new HashMap();
        map.put("collegeName", college);
        map.put("baseId",baseId);
        map.put("baseName",baseName);
        map.put("companyName",companyName);
        List list = baseService.getBaseListBySearchParamsAndCollege(map);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @RequestMapping("/addBase")
    public String addBase(Model model) {
        List<Company> companies = adminService.getCompanyBean();
        String baseIdMax = baseService.getMaxBaseId();
        int i = Integer.parseInt(baseIdMax);
        int baseId = i + 1;
        model.addAttribute("companies",companies);
        model.addAttribute("baseId",baseId);
        return "counselor/counselor_add_base";
    }

    /**
     * 添加基地将会对多张表进行insert,使用事务管理
     * @param base
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/saveBase")
    public boolean saveBase(Base base,String companyId){
        CollegeBase collegeBase = userService.getCollegeByCurrentUser(SessionContextUtil.getCurrentUser());
        boolean b = baseService.addBase(base, companyId, collegeBase.getCollegeId());
        return b;
    }

    @RequestMapping("/editBase/{baseId}")
    public String editBase(@PathVariable("baseId")String baseId, Model model) {
        Base base = baseService.getBaseByBaseId(baseId);
        Company company= companyService.getCompanyByBaseId(base);
        model.addAttribute("base",base);
        model.addAttribute("company",company);
        return "counselor/counselor_edit_base";
    }

    @ResponseBody
    @RequestMapping(value = "/updateBase",method = RequestMethod.POST)
    public boolean updateBase(Base base) {
        boolean b = baseService.updateBaseByCounselor(base);
        return b;
    }

    @ResponseBody
    @RequestMapping(value = "/delBaseByBaseId/{baseId}",method = RequestMethod.POST)
    public boolean delBaseByBaseId(@PathVariable("baseId") String baseId) {
        Base base = baseService.getBaseByBaseId(baseId);
        boolean b = baseService.delBaseByBaseId(base);
        return b;
    }

    /**
     * 辅导员学生管理
     */

    @RequestMapping("/studentManage")
    public String studentManage(Model model) {
        String college = SessionContextUtil.getCurrentUser().getCollege();
        List<String> classList = classStudentService.getClassNameByCollegeName(college);
        model.addAttribute("classList",classList);
        return "counselor/student_manage";
    }

    @ResponseBody
    @RequestMapping(value = "/getStudentList",method = RequestMethod.POST)
    public Map getStudentList(int curr, int limit) {
        curr=(curr-1)*limit;//根据当前页算出起始数据是第几条
        String collegeName = SessionContextUtil.getCurrentUser().getCollege();
        Map map = new HashMap();
        map.put("collegeName",collegeName);
        map.put("curr",curr);
        map.put("limit",limit);
        List studentList = classStudentService.getStudentsByCounselor(map);
        if (studentList == null||studentList.size()<=0){
            studentList.add(false);//用于前台判断list中是否有数据
            studentList.add("暂无数据");
        }
        int countStudent = classStudentService.getCountStudent(collegeName);
        Map resultMap = new HashMap();
        resultMap.put("studentList",studentList);
        resultMap.put("countStudent",countStudent);
        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/getStudentListBySearchParams",method = RequestMethod.POST)
    public Map getStudentListBySearchParams(int curr, int limit,String className,String username,String reportStatus) {
        curr=(curr-1)*limit;//根据当前页算出起始数据是第几条
        String collegeName = SessionContextUtil.getCurrentUser().getCollege();
        Map map = new HashMap();
        map.put("collegeName",collegeName);
        map.put("curr",curr);
        map.put("limit",limit);
        map.put("className",className);
        map.put("username",username);
        map.put("reportStatus",reportStatus);
        List studentList = classStudentService.getStudentsBySearchParams(map);
        if (studentList == null||studentList.size()<=0){
            studentList.add(false);//用于前台判断list中是否有数据
            studentList.add("暂无数据");
        }
        int countStudent = classStudentService.getCountStudentByParams(map);
        Map resultMap = new HashMap();
        resultMap.put("studentList",studentList);
        resultMap.put("countStudent",countStudent);
        return resultMap;
    }


    @RequestMapping("/enrolledStudents/{baseId}")
    public String enrolledStudent(@PathVariable("baseId")String baseId,Model model){
        Base base = classStudentService.getStudentOfBaseByBaseId(baseId);
        int count = baseStudentService.getCountStudentOfBase(baseId);
        if (base == null){
            base = baseService.getBaseByBaseId(baseId);
        }
        model.addAttribute("base",base);
        model.addAttribute("count",count);
        return "counselor/enrolledStudents";
    }

    @ResponseBody
    @RequestMapping(value = "/enrolledStudents",method = RequestMethod.POST)
    public Base enrolledStudentPost(String baseId,int curr,int limit,Model model){
        Map map = new HashMap();
        curr = (curr-1)*limit;
        map.put("baseId",baseId);
        map.put("curr",curr);
        map.put("limit",limit);
        Base base = classStudentService.getStudentOfBaseByBaseIdAndPage(map);
        int count = baseStudentService.getCountStudentOfBase(baseId);
        if (base == null){
            base = baseService.getBaseByBaseId(baseId);
        }
        model.addAttribute("count",count);
        return base;
    }

    @RequestMapping("/baseDetails/{baseId}")
    public String getBaseDetails(@PathVariable("baseId")String baseId,Model model){
        Base base = baseService.getBaseByBaseId(baseId);
        model.addAttribute("base",base);
        return "company/base_details";
    }

}
