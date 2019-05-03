package com.teachingbase.dao;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BaseMapper {

    /**
     * 根据currentUser的公司名获取基地列表。
     * @param companyName
     * @return
     */
    public List<Base> getBaseListByCompanyName(String companyName);

    public List getBaseListBySearchParams(Map params);
}
