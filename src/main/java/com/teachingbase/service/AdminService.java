package com.teachingbase.service;

import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AdminService {
    public List<User> getCounselorList();
    public List<User> getCounselorListBySearchParams(Map params);
    public List getColleges();
    public String getMaxUsernameOfCounselor();
    public int addCounselor(User user);
    public int delCounselors(String username);

    public List<User> getManagerList();
    public List<String> getCompanies();
    public List<User> getManagerListBySearchParams(Map params);
    public String getMaxUsernameOfManager();
    public int addManager(User user);
    public List<Company> getCompanyBean();
}
