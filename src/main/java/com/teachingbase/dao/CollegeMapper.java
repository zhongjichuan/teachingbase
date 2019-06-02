package com.teachingbase.dao;

import com.teachingbase.domain.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollegeMapper {

    @Select("select college_id,college_name from college_class")
    public List<College> getCollegeList();
}
