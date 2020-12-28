package com.favorites.repository;

import com.favorites.domain.UrlLibrary;
import com.favorites.domain.enums.CollectType;
import com.favorites.domain.enums.IsDelete;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.favorites.domain.Collect;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.zip.CheckedOutputStream;

@Service("collectRepository")
public interface ICollectRepository extends JpaRepository<Collect, Long>{
    /*通过id查询*/
    Collect findById(long id);

    /*通过用户id查询*/
    List<Collect> findAllByUserId(long userId);

    /*查询所有*/
    List<Collect> findAllByIsDelete(IsDelete delete);

    /*设置访问权限*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Collect set type=:type where id=:id")
    int setUserName(@Param("type") CollectType type, @Param("id") long id);

    /*修改备注*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update Collect set description=:description where id=:id")
    int setDescription(@Param("description") String description, @Param("id") long id);


}
