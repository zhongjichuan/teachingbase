package com.teachingbase.dao;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourceMapper {

    @Select("SELECT resource.* FROM user,role,resource where user.username = #{username} AND user.role_id = role.role_id AND role.resource_id = resource.resource_id")
    public List<Resource> getResourceByUsername(String username);

    @Select("SELECT resource.* FROM resource")
    public List<Resource> getAllResource();
}
