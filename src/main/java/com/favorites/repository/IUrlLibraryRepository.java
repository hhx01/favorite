package com.favorites.repository;

import com.favorites.domain.UrlLibrary;
import com.favorites.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("urlLibaryRepository")
public interface IUrlLibraryRepository extends JpaRepository<UrlLibrary, Long> {

    /*通过id查找*/
    UrlLibrary findById(long id);

    UrlLibrary findByUrl(String url);

    /*删除url*/
    @Transactional
    @Modifying
    @Query("delete from UrlLibrary where id=?1")
    void deleteById(long id);

    /*更新count*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query(value = "update UrlLibrary set count=:count where id=:id",nativeQuery = true)
    int setCount(@Param("count") int count, @Param("id") long id);
}
