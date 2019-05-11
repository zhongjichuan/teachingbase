package com.teachingbase.service;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;

import java.util.List;
import java.util.Map;

public interface BaseService {

    public List<Base> getBaseListByCompanyName(String companyName);

    public List getBaseListBySearchParams(Map params);

    public List getBaseTeachersByCompanyName(String companyName);

    public String getMaxBaseId();

    public boolean addBase(Base base,String companyId,String collegeId);

    public int saveBaseTeacher(String baseId,String teacherId);

    public int saveBaseCompany(String baseId,String companyId);

    public Company getCompanyByCompanyName(String companyName);

    public Base getBaseByBaseId(String baseId);

    public boolean updateBase(Base base,String teacherId);


    /**
     * 辅导员操作
     */
    public List<Base> getBaseListByCollegeName(String collegeName);

    public List getBaseListBySearchParamsAndCollege(Map params);

    public boolean updateBaseByCounselor(Base base);

    public boolean delBaseByBaseId(Base base);
}
