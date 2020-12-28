package com.favorites.controller;

import com.favorites.domain.Collect;
import com.favorites.domain.Favorites;
import com.favorites.service.ICollectService;
import com.favorites.service.IFavoritesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

import javax.annotation.Resource;
import javax.swing.text.Position;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ICollectService collectService;
    @Autowired
    private IFavoritesSevice favoritesSevice;

    //首页显示内容
    @RequestMapping(value = "/homeView",method = RequestMethod.POST)
    public List<Collect> homeView(long userId){
        List<Collect> result = collectService.getUserCollects(userId);
        return result;
    }

    //根据用户id获取收藏夹
    @RequestMapping(value = "/favorites",method = RequestMethod.POST)
    public List<Favorites> getFavorites(long userid){
        return favoritesSevice.getAllFavorites(userid);
    }

    //新建收藏夹
    @RequestMapping("/favorites/newFavorites")
    public String createFavorites(Favorites favorites){
       return favoritesSevice.createFavorites(favorites);
    }
}
