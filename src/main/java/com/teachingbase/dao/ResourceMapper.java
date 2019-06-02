package com.teachingbase.dao;

import com.teachingbase.domain.Resource;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResourceMapper {

    @Select("SELECT resource.* FROM user,role,resource where user.username = #{username} AND user.role_id = role.role_id AND role.resource_id = resource.resource_id and resource.is_active=1 ")
    public List<Resource> getResourceByUsername(String username);

    @Select("SELECT resource.* FROM resource")
    public List<Resource> getAllResource();

    @Update("update resource set description = #{name} where resource_id = #{id}")
    public int updateNode(@Param("id") String id,@Param("name") String name);

    @Update("update resource set is_active = #{activeFlag} where resource_id = #{id}")
    public int updateResourceToActive(@Param("id") String id,@Param("activeFlag") String activeFlag);
}
