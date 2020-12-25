package com.favorites.service;

import com.favorites.domain.User;

public interface IUserService {
    //用户注册，创建账户
    public String create(User user);

    //用户登录
    public String login(String id, String password);

    //修改个人介绍
    public String updateIntroduction(String introduction);

    //修改头像
    public String updateHead(String dataUrl);

    //修改昵称
    public String updateNickName(String newNickName);

    //修改密码
    public String updatePwd(String oldPwd, String newPwd);

    //忘记密码
    public String resetPwd(String email, String userID, String newPwd);
}
