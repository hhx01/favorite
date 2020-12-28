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
    @Query("update Favorites set lastModifyTime=:time where id=:id")
    int setLastModifyTime(@Param("lastModifyTime") Long time,@Param("id") long id);
    /*更新收藏夹名称*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Favorites set name=:name where id=:id")
    int setName(@Param("lastModifyTime")String name,@Param("id") long id);
    /*删除收藏夹*/
    void deleteByid(long id);
}
