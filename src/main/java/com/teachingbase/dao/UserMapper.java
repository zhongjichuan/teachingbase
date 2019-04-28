package com.teachingbase.dao;

import com.teachingbase.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from User where username = #{username}")
    public User getUserByUsername(String username);

    @Update("update user set password = #{password} where username = #{username}")
    public int updatePasswordByUsername(@Param("username")String username, @Param("password")String password);

    @Update("update user set username = #{username},name=#{name},age=#{age},sex=#{sex},college=#{college},company=#{company},phone=#{phone},email=#{email},update_time=now() where username=#{username}")
    public int updateUser(User user);
}
