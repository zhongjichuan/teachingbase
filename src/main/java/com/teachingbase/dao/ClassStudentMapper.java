package com.teachingbase.dao;

import com.teachingbase.domain.Base;
import com.teachingbase.domain.ClassStudent;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ClassStudentMapper {

    @Select("select DISTINCT class_name from college_class,class_student where college_class.college_name = #{collegeName} " +
            "and college_class.college_id = class_student.college_id")
    public List<String> getClassNameByCollegeName(String collegeName);

    /**
     * 查询院中的学生及其基地报名状况
     */
    public List<User> getStudentsByCounselor(Map map);

    public List<User> getStudentsBySearchParams(Map map);

    public int getCountStudent(String collegeName);

    public int getCountStudentByParams(Map map);


    @Select("select class_student.* from class_student where class_student.student_id=#{studentId}")
    public ClassStudent getClassStudentByStudentId(String studentId);


    /**
     * 使用复杂的resultMap
     * @param baseId
     * @return
     */
    public Base getStudentOfBaseByBaseId(String baseId);


    public Base getStudentOfBaseByBaseIdAndPage(Map map );

}
