package com.favorites.service.Impl;

import com.favorites.domain.Collect;
import com.favorites.domain.UrlLibrary;
import com.favorites.domain.enums.CollectType;
import com.favorites.domain.enums.IsDelete;
import com.favorites.repository.ICollectRepository;
import com.favorites.repository.IUrlLibraryRepository;
import com.favorites.service.ICollectService;
import com.favorites.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("collectService")
public class CollectService implements ICollectService{

    @Autowired
    private ICollectRepository collectRepository;

    //保存collect
    @Override
    public void saveCollect(Collect collect) {
        if (collect.getType()==null) {
            collect.setType(CollectType.PUBLIC);
        }else{
            collect.setType(CollectType.PRIVATE);
        }
        if(StringUtils.isNotBlank(collect.getNewFavorites())){
           // collect.setFavoritesId(createfavorites(collect));
        }
        if(StringUtils.isBlank(collect.getDescription())){
            collect.setDescription(collect.getTitle());
        }
        if(collect.getUrl().contains("?")){
            collect.setUrl(collect.getUrl().substring(0,collect.getUrl().indexOf("?")));
        }
        collect.setIsDelete(IsDelete.NO);
        collect.setCreateTime(System.currentTimeMillis());
        collect.setLastModifyTime(System.currentTimeMillis());
        collectRepository.save(collect);
        //favoritesService.countFavorites(collect.getFavoritesId());

    }

    @Override
    public List<Collect> getAllCollects() {
        return collectRepository.findAllByIsDelete(IsDelete.NO);
    }

    @Override
    public List<Collect> getUserCollects(long userId) {
        return collectRepository.findAllByUserId(userId);
    }
}
