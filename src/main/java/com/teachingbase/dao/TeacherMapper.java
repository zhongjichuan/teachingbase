package com.teachingbase.dao;


import com.teachingbase.domain.Teacher;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TeacherMapper {

    public Teacher getTeacherByBaseId(String BaseId);

    @Select("select teacher.* from teacher,company_teacher,company where company.company_name=#{companyName} " +
            "and company.company_id = company_teacher.company_id and teacher.teacher_id = company_teacher.teacher_id")
    public List<Teacher> getTeacherListByCompany(String companyName);

    public List<Teacher> getTeacherListByParams(Map map);

    @Select("select MAX(teacher.teacher_id) from teacher")
    public int getMaxTeacherId();


    @Insert("insert into teacher(teacher_id,teacher_name,teacher_position,phone,sex,teacher_description,email,age,create_time,update_time) " +
            "values(#{teacherId},#{teacherName},#{teacherPosition},#{phone},#{sex},#{teacherDescription},#{email},#{age},now(),now())")
    public int addTeacher(Teacher teacher);

    @Insert("insert into company_teacher values(#{companyId},#{teacherId})")
    public int addCompanyTeacher(@Param("companyId") String companyId, @Param("teacherId")String teacherId);

    @Insert("insert into user(username,name,role_id) values(#{username},#{name},'501')")
    public int addTeacherOfUserTable(User user);

    public Teacher getTeacherByTeacherId(String teacherId);

    public int updateTeacher(Teacher teacher);

    @Update("update user set name=#{teacherName} where username = #{username}")
    public int updateTeacherOfUserTable(@Param("teacherName")String teacherName,@Param("username")String name);

    @Delete("delete from teacher where teacher_id = #{teacherId}")
    public int delTeacher(String teacherId);

    @Delete("delete from User where username=#{username}")
    public int delTeacherOfUserTaber(String username);

    @Update("update base_teacher set teacher_id = '' where teacher_id=#{teacherId}")
    public int delBaseTeacher(String teacherId);

    @Delete("delete from company_teacher where teacher_id = #{teacherId}")
    public int delCompanyTeacher(String teacherId);

    @Select("select count(1) from base_teacher where teacher_id = #{teacherId}")
    public int countBaseTeacherByTeacherId(String teacherId);

    @Select("select * from teacher where teacher_id = #{teacher}")
    public Teacher getTeacherDetailsByTeacherId(String teacherId);
}
