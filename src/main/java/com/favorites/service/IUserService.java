package com.favorites.service;

import com.favorites.domain.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IUserService {
    //用户注册，创建账户
    public String create(User user);

    //用户登录
    public String login(User user, HttpServletResponse response);

    //修改个人介绍
    public String updateIntroduction(String introduction, String email);

    //修改头像
    public String updateHead(String dataUrl, String email);

    //修改昵称
    public String updateNickName(String newNickName, String email);

    //修改背景图片
    public String updateBgp(String dataUrl, String email);

    //修改密码
    public String updatePwd(String oldPwd, String newPwd);

    //忘记密码
    public String resetPwd(String email, String userID, String newPwd);

    public List<User> findAllUser();
}
