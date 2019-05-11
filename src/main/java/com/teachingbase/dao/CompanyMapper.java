package com.teachingbase.dao;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyMapper {

    @Select("select company.* from company_base,company where company_base.base_id = #{baseId} and company_base.company_id = company.company_id")
    public Company getCompanyByBaseId(Base base);

    @Select("select * from company where company_name=#{companyName}")
    public Company getCompanyByCompanyName(String companyName);


}
