package com.favorites.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String homeView(Model model){
        System.out.println("hello home");
        return "hello home";
    }
}
