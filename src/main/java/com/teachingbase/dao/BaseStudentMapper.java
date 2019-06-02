package com.teachingbase.dao;

import com.teachingbase.domain.BaseStudent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BaseStudentMapper {


    @Insert("insert into base_student(base_id,student_id) values(#{baseId},#{username})")
    public int insertBaseStudent(@Param("baseId") String baseId, @Param("username") String username);

    @Select("select * from base_student where base_id = #{baseId}")
    public List<BaseStudent> getBaseStudentByBaseId(String baseId);

    @Select("select * from base_student where student_id = #{studentId}")
    public List<BaseStudent> getBaseStudentByStudentId(String studentId);

    @Delete("delete from base_student where student_id = #{studentId}")
    public int delBaseStudentByStudentId(String studentId);

    @Select("select count(*) from base_student where base_student.base_id=#{baseId}")
    public int getCountStudentOfBase(String baseId);

    @Update("update base_student set report_status=#{reportStatus} where student_id=#{studentId}")
    public int updateBaseReportStatus(Map map);
}
