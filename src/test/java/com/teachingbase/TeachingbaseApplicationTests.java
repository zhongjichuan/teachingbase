package com.teachingbase;

import com.teachingbase.dao.ResourceMapper;
import com.teachingbase.domain.Resource;
import com.teachingbase.domain.Tree;
import com.teachingbase.domain.User;
import com.teachingbase.service.AdminService;
import com.teachingbase.service.ResourceService;
import com.teachingbase.service.RoleService;
import com.teachingbase.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

}
