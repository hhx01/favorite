package com.favorites.service;

import com.favorites.domain.Favorites;

import javax.management.StandardEmitterMBean;
import java.util.List;

public interface IFavoritesSevice {
    //创建收藏夹
    public String createFavorites(Favorites favorites);
    //更新收藏夹名称
    public void updateName(String name,long id);
    //更新收藏夹count
    public void updateCount(Favorites favorites,String isPublic);
    //获取收藏夹
    public List<Favorites> getAllFavorites(long userid);
}
