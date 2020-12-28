package com.favorites.controller;

import com.favorites.domain.Config;
import com.favorites.domain.User;
import com.favorites.domain.enums.CollectType;
import com.favorites.repository.IConfigRepository;
import com.favorites.service.IConfigService;
import com.favorites.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private IConfigService configService;

    @Autowired
    private IConfigRepository configRepository;

    @Autowired
    private IUserService userService;

    //获取用户config
    @RequestMapping(value = "/getConfig",method = RequestMethod.POST)
    public Config getConfig(long usetId){
        return configRepository.findByUserId(usetId);
    }

    //修改默认收藏夹
    @RequestMapping(value = "/updateFavorites",method = RequestMethod.PUT)
    public String updateFavorites(String favoritesName,long userId){
        configService.updateDefaultFavorites(favoritesName,userId);
        return "updateFavorites";
    }
    //修改默认权限
    @RequestMapping(value = "/updateType",method = RequestMethod.PUT)
    public String updateType(CollectType type, long userId){
        configService.updateDefaultColllectType(type,userId);
        return "updateFavorites";
    }

    //修改个人简介
    @RequestMapping(value = "/updateIntroduction",method = RequestMethod.PUT)
    public String updateIntroduction(String introduction, String email){
        userService.updateIntroduction(introduction,email);
        return "updateIntroduction";
    }

    //修改头像
    @RequestMapping(value = "/updateHead",method = RequestMethod.PUT)
    public String updateHeadImg(String dataurl,String email){
        userService.updateHead(dataurl,email);
        return "updateImg";
    }

    //修改昵称
    @RequestMapping(value = "/updateNickname",method = RequestMethod.PUT)
    public String updateName(String name,String email){
        userService.updateNickName(name,email);
        return "updateName";
    }

    //修改密码
    @RequestMapping(value = "/updatePwd",method = RequestMethod.PUT)
    public String updatePwd(String oldPwd,String newPwd){
        userService.updatePwd(oldPwd,newPwd);
        return "updatePwd";
    }

    //用户注销登录
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        return "logout";
    }
}
