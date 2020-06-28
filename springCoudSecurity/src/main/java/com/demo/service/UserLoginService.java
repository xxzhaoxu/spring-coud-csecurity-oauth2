package com.demo.service;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserLoginService implements UserDetailsService {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s==null||s.trim().length()<=0) {
            throw new UsernameNotFoundException("用户名为空");
        }
        User user  = userMapper.selectByUserName(s);
        if (user!=null){
            return user;
        }
        throw new UsernameNotFoundException("用户不存在!");
    }
}
