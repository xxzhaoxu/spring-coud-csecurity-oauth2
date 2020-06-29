package com.demo.mapper;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 35086
 */
public interface UserMapper {


    /**
     * <h1>查询用户的同时，查询出用户拥有的所有角色</h1>
     * @param username
     * @return
     */
    User  selectByUserName(@Param("username") String username);
}