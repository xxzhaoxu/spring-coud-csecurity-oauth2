package com.demo.entity;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 35086
 */
public class User implements UserDetails {
    /** 用户id */
    private Long id;

    /** 用户名 */
    private String username;

    /** 用户密码 */
    private String password;

    /** 角色列表 */
    private List<Role> authorities = new ArrayList<>();

    /** 指示是否未过期的用户的凭据(密码),过期的凭据防止认证 默认true 默认表示未过期 */
    private boolean credentialsNonExpired = true;

    /**
     * 账户是否未过期,过期无法验证 默认true表示未过期
     */
    private boolean accountNonExpired = true;

    /**
     * 用户是未被锁定,锁定的用户无法进行身份验证 默认true表示未锁定
     */
    private boolean accountNonLocked = true;

    /**
     * 是否可用 ,禁用的用户不能身份验证 默认true表示可用
     */
    private boolean enabled = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}