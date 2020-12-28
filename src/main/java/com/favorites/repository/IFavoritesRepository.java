package com.favorites.repository;

import com.favorites.domain.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IFavoritesRepository extends JpaRepository<Favorites, Long> {
    /*通过id查找收藏夹*/
    Favorites findById(long id);
    /*通过用户id查找收藏夹*/
    List<Favorites> findAllByUserId(long userId);
    /*通过name查找*/
    Favorites findByName(String name);
    /*更新修改时间*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Favorites set lastModifyTime=?1 where id=?2")
    int setLastModifyTime(Long time,long id);
    /*更新收藏夹名称*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Favorites set name=?1 where id=?2")
    int setName(String name, long id);
    /*删除收藏夹*/
    void deleteByid(long id);
}
