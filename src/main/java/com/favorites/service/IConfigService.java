package com.favorites.service;

import com.favorites.domain.Config;
import com.favorites.domain.enums.CollectType;
import com.favorites.repository.IConfigRepository;

public interface IConfigService {
    //保存设置
    public void saveConfig(Config config);
    //修改默认收藏夹
    public void updateDefaultFavorites(String favoritesName, long userid);
    //修改默认收藏权限
    public void updateDefaultColllectType(CollectType type,long userid);

}
