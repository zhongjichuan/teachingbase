package com.teachingbase.controller.studentController;

import com.teachingbase.dao.BaseMapper;
import com.teachingbase.domain.Base;
import com.teachingbase.domain.Teacher;
import com.teachingbase.domain.User;
import com.teachingbase.service.BaseService;
import com.teachingbase.service.BaseStudentService;
import com.teachingbase.service.TeacherService;
import com.teachingbase.util.ResponseUtil;
import com.teachingbase.util.SessionContextUtil;
import net.minidev.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiresRoles("student")
@RequestMapping("/student")
public class StudentController {

    @Autowired
    public BaseService baseService;
    @Autowired
    public BaseStudentService baseStudentService;

    @Autowired
    public TeacherService teacherService;
    @RequestMapping("/signUp")
    public String enter() {
        return "student/signUp";
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseList",method = RequestMethod.GET)
    public List getBaseList(){
        User user = SessionContextUtil.getCurrentUser();
        List list = baseService.getBaseListByStudent(user.getUsername());
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }
    @ResponseBody
    @RequestMapping(value = "/getBaseList",method = RequestMethod.POST)
    public List getBaseListByStudentAndParams(String baseId,String teacherName,String companyName){
        User user = SessionContextUtil.getCurrentUser();
        Map map = new HashMap();
        map.put("baseId",baseId);
        map.put("teacherName",teacherName);
        map.put("companyName",companyName);
        map.put("username", user.getUsername());
        List list = baseService.getBaseListByStudentAndParams(map);
        if (list == null||list.size()<=0){
            list.add(false);//用于前台判断list中是否有数据
            list.add("暂无数据");
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/cancel",method = RequestMethod.POST)
    public boolean cancel(String baseId,String studentId){
        boolean result = baseStudentService.delBaseStudentByStudentId(studentId,baseId);
        return result;
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public Boolean signUp(String baseId, String studentId, HttpServletResponse response) throws Exception {
        //判断报名人数与已报名人数
        Base base= baseService.getBaseByBaseId(baseId);
        String num = base.getBaseNum();
        String enrolmentNum = base.getBaseEnrolmentNum();
        int n = Integer.parseInt(num);
        int m = Integer.parseInt(enrolmentNum);
        JSONObject result = new JSONObject();
        if (m == n){
            result.put("flag","FULL");
        } else if (baseStudentService.insertBaseStudent(baseId,studentId)){
            result.put("flag","SUCCESS");
        }else {
            result.put("flag","FAIL");
        }
        ResponseUtil.write(response,result.toString());
        return null;
    }

    @RequestMapping(value = "/teacherDetails/{teacherId}")
    public String teacherDetails(@PathVariable("teacherId") String teacherId, Model model){
        Teacher teacher = teacherService.getTeacherDetailsByTeacherId(teacherId);
        model.addAttribute("teacher",teacher);
        return "company/teacher_details";
    }

    @RequestMapping("/baseDetails/{baseId}")
    public String getBaseDetails(@PathVariable("baseId")String baseId,Model model){
        Base base = baseService.getBaseByBaseId(baseId);
        model.addAttribute("base",base);
        return "company/base_details";
    }
}
