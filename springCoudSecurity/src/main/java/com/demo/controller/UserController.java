package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 35086
 */
@RestController
public class UserController {

    /**
     * 1. 可以get访问 获取授权码
     * http://localhost:8015/oauth/authorize?client_id=c1&response_type=code&redirect_uri=http://localhost:8015/
     * 2. 必须post访问 获取token
     * http://localhost:8025/oauth/token?grant_type=authorization_code&code=授权码&redirect_uri=http://localhost:8025/&client_id=c1&client_secret=secret
     */
    @GetMapping("/user/save")
    public void save(){
        System.out.println("save");
    }

    @GetMapping("/user/find")
    public void find(){
        System.out.println("find");
    }
}
