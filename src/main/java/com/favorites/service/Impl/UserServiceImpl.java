package com.favorites.service.Impl;

import com.favorites.domain.Favorites;
import com.favorites.domain.User;
import com.favorites.domain.result.ExceptionMsg;
import com.favorites.domain.result.Response;
import com.favorites.service.IUserService;
import com.favorites.repository.IuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IuserRepository userRepository;
    //创建新用户
    @Override
    public String create(User user) {
        {
            try {
                User registUser = userRepository.findByEmail(user.getEmail());
                if (null != registUser) {
                    return "该邮箱已被注册";
                }
                User userNameUser = userRepository.findByUserName(user.getUserName());
                if (null != userNameUser) {
                    return "用户名已存在";
                }
                user.setPassWord(user.getPassWord());
                user.setCreateTime(System.currentTimeMillis());
                user.setLastModifyTime(System.currentTimeMillis());
                user.setProfilePicture("img/favicon.png");
                userRepository.save(user);
                // 添加默认收藏夹
                //Favorites favorites = favoritesService.saveFavorites(user.getId(), "未读列表");
                // 添加默认属性设置
                //configService.saveConfig(user.getId(),String.valueOf(favorites.getId()));
            } catch (Exception e) {
                // TODO: handle exception
                //logger.error("create user failed, ", e);
                return "";
            }
            return "";
        }
    }

    //登录
    @Override
    public String login(String id, String password) {
        return null;
    }

    //更新个人简介
    @Override
    public String updateIntroduction(String introduction) {
        return null;
    }
    //更新头像
    @Override
    public String updateHead(String dataUrl) {
        return null;
    }

    //更新昵称
    @Override
    public String updateNickName(String newNickName) {
        return null;
    }

    //修改密码
    @Override
    public String updatePwd(String oldPwd, String newPwd) {
        return null;
    }

    //忘记密码
    @Override
    public String resetPwd(String email, String userID, String newPwd) {
        return null;
    }
}
