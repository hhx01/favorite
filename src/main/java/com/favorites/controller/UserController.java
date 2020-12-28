package com.favorites.controller;
import com.favorites.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
   // @RequestMapping(value = "/regist", method = RequestMethod.POST)
    //public
    @RequestMapping("/login")
    public String login(){
        return "hello";
    }
}
