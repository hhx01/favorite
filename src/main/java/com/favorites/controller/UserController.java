package com.favorites.controller;
import com.favorites.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestBody(required = false) User user){
        System.out.println(user.toString());
        return "fail";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
