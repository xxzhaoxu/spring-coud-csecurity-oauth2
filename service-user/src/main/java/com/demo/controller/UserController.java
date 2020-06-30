package com.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/user/save")
    public String save(){
        return "save";
    }
}
