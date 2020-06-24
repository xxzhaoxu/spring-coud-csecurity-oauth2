package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 35086
 */
@RestController
public class UserController {

    @GetMapping("/user/save")
    public void save(){
        System.out.println("save");
    }

    @GetMapping("/user/find")
    public void find(){
        System.out.println("find");
    }
}
