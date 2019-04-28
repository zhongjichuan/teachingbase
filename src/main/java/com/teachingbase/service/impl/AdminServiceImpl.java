package com.teachingbase.service.impl;

import com.teachingbase.dao.AdminMapper;
import com.teachingbase.domain.User;
import com.teachingbase.service.AdminService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Override
    public List<User> getCounselorList() {
        return adminMapper.getCounselorList();

    }
    public List<User> getCounselorListBySearchParams(Map params){
        return adminMapper.getCounselorListBySearchParams(params);
    }

    @Override
    public List getColleges() {
        return adminMapper.getColleges();
    }

    @Override
    public String getMaxUsernameOfCounselor() {
        return adminMapper.getMaxUsernameOfCounselor();
    }

    @Override
    public int addCounselor(User user) {
        return adminMapper.addCounselor(user);
    }

    @Override
    public int delCounselors(String username) {
        return adminMapper.delCounselors(username);
    }

    public List<User> getManagerList(){
        return adminMapper.getManagerList();
    }

    public List<String> getCompanies(){
        return adminMapper.getCompanies();
    }

    public List<User> getManagerListBySearchParams(Map params){
        return adminMapper.getManagerListBySearchParams(params);
    }

    public String getMaxUsernameOfManager(){
        return adminMapper.getMaxUsernameOfManager();
    }

    public int addManager(User user){
        return adminMapper.addManager(user);
    }
}
