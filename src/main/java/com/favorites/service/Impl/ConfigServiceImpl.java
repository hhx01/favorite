package com.favorites.service.Impl;

import com.favorites.comm.Const;
import com.favorites.domain.Config;
import com.favorites.domain.enums.CollectType;
import com.favorites.repository.IConfigRepository;
import com.favorites.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements IConfigService{

    @Autowired
    private IConfigRepository configRepository;
    @Override
    public void saveConfig(Config config) {
        Config newConfig = new Config();
        newConfig.setUserId(config.getUserId());
        newConfig.setCreateTime(System.currentTimeMillis());
        if(config.getDefaultCollectType()==null||config.getDefaultCollectType().equals(CollectType.PUBLIC)){
            newConfig.setDefaultCollectType(CollectType.PUBLIC);
        }
        else {
            newConfig.setDefaultCollectType(CollectType.PRIVATE);
        }
        if(config.getDefaultFavorties().isBlank()){newConfig.setDefaultFavorties(Const.DEFAULT_FAVORITES);}
        else {newConfig.setDefaultFavorties(config.getDefaultFavorties());}
        newConfig.setLastModifyTime(System.currentTimeMillis());

        configRepository.save(newConfig);
    }

    @Override
    public void updateDefaultFavorites(String favoritesName,long userid) {
        configRepository.setDefaultFavorites(favoritesName,userid);
        configRepository.setLastModifyTime(System.currentTimeMillis(),userid);
    }

    @Override
    public void updateDefaultColllectType(CollectType type,long userid) {
        configRepository.setDefaultCollectType(type,userid);
        configRepository.setLastModifyTime(System.currentTimeMillis(),userid);
    }
}
