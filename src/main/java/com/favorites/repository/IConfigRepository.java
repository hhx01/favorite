package com.favorites.repository;

import com.favorites.domain.Config;
import com.favorites.domain.enums.CollectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IConfigRepository extends JpaRepository<Config, Long> {

    /*通过id查找*/
    Config findById(long configId);
    /*通过用户id查找*/
    Config findByUserId(long userId);
    /*设置默认收藏夹*/
    /*@Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Config set defaultFavorties=:favorites where userId=:userid")
    int setDefaultFavorites(@Param("defaultFavorties") String favorites, @Param("userid") long userid);*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Config set defaultFavorties=?1 where userId=?2")
    int setDefaultFavorites(String defaultFavorties, long userId);
    /*设置默认收藏类型*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Config set defaultCollectType=?1 where userId=?2")
    int setDefaultCollectType(CollectType type,long userid);
    /*删除收藏*/
    void deleteByUserId(long userid);

    /*设置修改时间*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Config set lastModifyTime=?1 where userId=?2")
    int setLastModifyTime(long time, long userid);
}
