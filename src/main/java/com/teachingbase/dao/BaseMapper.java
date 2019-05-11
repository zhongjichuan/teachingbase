package com.teachingbase.dao;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.Company;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.*;
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

    public List getBaseTeachersByCompanyName(String companyName);

    @Select("select Max(base_id) from base")
    public String getMaxBaseId();


    @Insert("insert into base_teacher values(#{baseId},#{teacherId})")
    public int saveBaseTeacher(@Param("baseId") String baseId, @Param("teacherId") String teacherId);

    @Select("select * from company where company_name=#{companyName}")
    public Company getCompanyByCompanyName(String companyName);

    public Base getBaseByBaseId(String baseId);

    public int updateBase(Base base);

    @Update("update base_teacher set teacher_id = #{teacherId} where base_id = #{baseId} ")
    public int updateBaseTeacher(@Param("baseId") String baseId,@Param("teacherId") String teacherId);


    /**
     * 辅导员操作
     */
    /**
     * 根据currentUser的学院名获取基地列表。
     * @param collegeName
     * @return
     */
    public List<Base> getBaseListByCollegeName(String collegeName);

    public List getBaseListBySearchParamsAndCollege(Map params);

    @Insert("insert into base(base_id,base_name,base_description,base_status,base_createTime,base_updateTime) " +
            "values(#{baseId},#{baseName},#{baseDescription},0,now(),now())")
    public int addBase(Base base);

    @Insert("insert into company_base values(#{companyId},#{baseId})")
    public int saveBaseCompany(@Param("baseId") String baseId, @Param("companyId") String companyId);

    @Insert("insert into college_base values(#{collegeId},#{baseId})")
    public int saveBaseCollege(@Param("baseId") String baseId, @Param("collegeId") String collegeId);

    @Update("update base set base.base_name = #{baseName},base.base_description=#{baseDescription},base_updateTime = now() where base_id=#{baseId}")
    public int updateBaseByCounselor(Base base);

    @Delete("delete from base where base_id = #{baseId}")
    public int delBaseByBaseId(String baseId);

    @Delete("delete from company_base where company_base.base_id = #{baseId}")
    public int delCompanyBaseByBaseId(String baseId);

    @Delete("delete from college_base where college_base.base_id = #{baseId}")
    public int delCollegeBaseByBaseId(String baseId);

    @Delete("delete from base_teacher where base_teacher.base_id = #{baseId}")
    public int delTeacherBaseByBaseId(String baseId);

}
