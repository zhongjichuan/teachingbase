package com.teachingbase.dao;

import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface AdminMapper {

    @Select("select * from User where role_id = '201'")
    public List<User> getCounselorList();

    public List<User> getCounselorListBySearchParams(Map params);

    @Select("select distinct college_name from college_class")
    public List<String> getColleges();

    @Select("select max(username) from user where role_id='201'")
    public String getMaxUsernameOfCounselor();

    public int addCounselor(User user);

    @Delete("delete from User where username=#{username}")
    public int delCounselors(String username);

    @Select("select * from User where role_id = '401'")
    public List<User> getManagerList();

    @Select("select distinct company_name from company")
    public List<String> getCompanies();

    public List<User> getManagerListBySearchParams(Map params);

    @Select("select max(username) from user where role_id='401'")
    public String getMaxUsernameOfManager();

    public int addManager(User user);
}
