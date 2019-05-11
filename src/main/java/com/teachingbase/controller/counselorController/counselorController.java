package com.teachingbase.controller.counselorController;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.CollegeBase;
import com.teachingbase.domain.Company;
import com.teachingbase.service.AdminService;
import com.teachingbase.service.BaseService;
import com.teachingbase.service.CompanyService;
import com.teachingbase.service.UserService;
import com.teachingbase.util.SessionContextUtil;
import org.apache.ibatis.annotations.Param;
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
        System.out.println(base+"========");
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

}
