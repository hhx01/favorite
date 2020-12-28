package com.favorites.service.Impl;

import com.favorites.domain.Favorites;
import com.favorites.repository.IFavoritesRepository;
import com.favorites.service.IFavoritesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements IFavoritesSevice {

    @Autowired
    private IFavoritesRepository favoritesRepository;
    @Override
    public String createFavorites(Favorites favorites) {
        Favorites favorites1 = favoritesRepository.findByName(favorites.getName());
        if(favorites1!=null){return "该收藏夹已经存在，创建失败";}
        favorites1.setUserId(favorites.getUserId());
        if(favorites.getName().isBlank()){return "名称为空，请重新输入";}
        favorites1.setName(favorites.getName());
        favorites1.setCount(0l);
        favorites1.setCreateTime(System.currentTimeMillis());
        favorites1.setLastModifyTime(System.currentTimeMillis());
        favorites1.setPublicCount(0l);
        favoritesRepository.save(favorites1);
        return "创建成功";
    }

    @Override
    public void updateName(String name, long id) {
        favoritesRepository.setName(name,id);
        favoritesRepository.setLastModifyTime(System.currentTimeMillis(),id);
    }

    @Override
    public void updateCount(Favorites favorites, String isPublic) {
        favorites.setCount(favorites.getCount()+1);
        if(isPublic.equals("public")){favorites.setPublicCount(favorites.getPublicCount()+1);}
        favoritesRepository.save(favorites);
    }

    @Override
    public List<Favorites> getAllFavorites(long userid) {
        return favoritesRepository.findAllByUserId(userid);
    }
}
