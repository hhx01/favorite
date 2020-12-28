package com.favorites.controller;
import com.favorites.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.favorites.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestBody(required = false) User user){
        String result = userService.create(user);
        return result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user,HttpServletResponse response){
        String result = userService.login(user,response);
        return result;
    }

    @RequestMapping("/all")
    public List<User> findAll(){
        return userService.findAllUser();
    }

}
