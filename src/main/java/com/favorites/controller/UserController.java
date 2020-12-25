package com.favorites.controller;
import com.favorites.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.favorites.service.IUserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestBody(required = false) User user){
        String result = userService.create(user);
        System.out.println(result);
        return result;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
