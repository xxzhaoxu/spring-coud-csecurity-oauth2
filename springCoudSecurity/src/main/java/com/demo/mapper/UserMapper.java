package com.demo.mapper;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * <h1>查询用户的同时，查询出用户拥有的所有角色</h1>
     * @param username
     * @return
     */
    User  selectByUserName(@Param("username") String username);
}