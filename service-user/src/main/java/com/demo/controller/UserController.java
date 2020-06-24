package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 35086
 */
@RestController
public class UserController {

    @GetMapping("/user/find")
    public String find(){
        return "find";
    }

    @GetMapping("/user/save")
    public String save(){
        return "save";
    }
}
