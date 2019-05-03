package com.teachingbase.dao;


import com.teachingbase.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeacherMapper {

    public Teacher getTeacherByBaseId(String BaseId);
}
