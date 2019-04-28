package com.teachingbase.dao;

import com.teachingbase.domain.Role;
import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {

    /**
     *
     * @param username
     * @return 返回role_code
     */
    @Select("select DISTINCT role.role_code from user,role where user.role_id = role.role_id and user.username = #{username}")
    public String getRoleCodeByUsername(String username);
}
