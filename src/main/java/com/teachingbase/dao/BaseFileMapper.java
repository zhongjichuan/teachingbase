package com.teachingbase.dao;

import com.teachingbase.domain.BaseFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BaseFileMapper {

    public int addBaseFile(BaseFile baseFile);

    @Select("select * from base_file where base_id = #{baseId}")
    public BaseFile getBaseFileByBaseId(String baseId);
}
