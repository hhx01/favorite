package com.favorites.repository;

import com.favorites.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

public interface IuserRepository extends JpaRepository<User, Long> {
    /*
    * 通过id查找
     */
    User findById(long id);
    /*
    *   通过用户名查找
     */
    User findByUserName(String userName);
    /*
    *   通过用户名或用户邮箱查询（由于用户登陆时输入可能是邮箱或者用户名）
     */
    User findByUserNameOrEmail(String username, String email);
    /*
    *   通过用户邮箱查询
     */
    User findByEmail(String email);

    /*设置用户名*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set userName=:userName where email=:email")
    int setUserName(@Param("userName") String userName, @Param("email") String email);

    /*设置密码*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set passWord=:passWord where email=:email")
    int setNewPassword(@Param("passWord") String passWord, @Param("email") String email);

    /*设置个人简介*/
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User set introduction=:introduction where email=:email")
    int setUserIntroduction(@Param("introduction") String introduction, @Param("email") String email);

    /*设置用户头像*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set profilePicture=:profilePicture where id=:id")
    int setProfilePicture(@Param("profilePicture") String profilePicture, @Param("id") Long id);

    /*设置个人主页背景*/
    @Modifying(clearAutomatically=true)
    @Transactional
    @Query("update User set backgroundPicture=:backgroundPicture where id=:id")
    int setBackgroundPicture(@Param("backgroundPicture") String backgroundPicture, @Param("id") Long id);
}
