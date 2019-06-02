package com.teachingbase;

import com.teachingbase.dao.BaseMapper;
import com.teachingbase.dao.ClassStudentMapper;
import com.teachingbase.dao.ResourceMapper;
import com.teachingbase.dao.TeacherMapper;
import com.teachingbase.domain.*;
import com.teachingbase.service.*;
import com.teachingbase.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeachingbaseApplicationTests {

    @Autowired
    ResourceMapper resourceMapper;
    @Autowired
    ResourceService resourceService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
    @Autowired
    public BaseService baseService;
    @Autowired
    public ClassStudentMapper classStudentMapper;

    @Test
    public void test1() {
        List<Resource> resourceList = resourceMapper.getResourceByUsername("300001");
        System.out.println(resourceList);
    }

    @Test
    public void test2() {
        List<Tree<Resource>> treeList = resourceService.getResourceByUsername("300001");
        System.out.println(treeList);
    }
    @Test
    public void test3() {
        String roleCode = roleService.getRoleCodeByUsername("300001");
        System.out.println(roleCode);
    }
    @Test
    public void test4() {
        Map map = new HashMap();
       map.put("college","建筑工程学院");
       //map.put("username","200003");
        //map.put("name","徐蓓");
        System.out.println(adminService.getCounselorListBySearchParams(map));
    }
    @Test
    public void test5() {
        List list = new ArrayList();
        list = adminService.getColleges();
        System.out.println(list);
    }
    @Test
    public void test6() {
        User user = new User();
        user.setUsername("200008");
        user.setPassword("123456");
        System.out.println(adminService.addCounselor(user));
    }

    @Autowired
    public TeacherService teacherService;
    @Test
    public void tets7(){
        teacherService.getTeacherListByCompany("阿里巴巴");
//        System.out.println(list);
    }
    @Autowired
    public TeacherMapper teacherMapper;
    @Test
    public void tets8(){
        int i = teacherMapper.countBaseTeacherByTeacherId("700003");
        System.out.println(i);
    }

 @Test
    public void test9(){
     Base b  = classStudentMapper.getStudentOfBaseByBaseId("602");
     System.out.println(b);
 }

 @Autowired
 public BaseMapper baseMapper;
 @Test
    public void test10(){
     baseMapper.delTeacherBaseByBaseId("603");
 }


    @Test
    public void test11(){
        String[] title = {"1","2","3","4"};
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        HSSFWorkbook workbook = ExcelUtil.generateExcel(title, list, "Excel_d11_16","某某老师");
        try {
            OutputStream out = new FileOutputStream("E://Excel.xls");
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Autowired
    public BaseFileService baseFileService;
    @Test
    public void Test12(){
        BaseFile baseFile = new BaseFile();
        baseFile.setBaseId("601");
        baseFile.setFilePath("Excel_20190521.xls");
        //baseFileService.addBaseFile(baseFile);

    }


}
