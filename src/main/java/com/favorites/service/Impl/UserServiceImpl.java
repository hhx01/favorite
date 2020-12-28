package com.favorites.service.Impl;

import com.favorites.domain.User;
import com.favorites.service.IUserService;
import com.favorites.repository.IuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.favorites.comm.Const;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
                user.setPassword(user.getPassword());
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
    public String login(User user, HttpServletResponse response) {
        try {
            //这里不是bug，前端userName有可能是邮箱也有可能是昵称。
            User loginUser = userRepository.findByUserNameOrEmail(user.getUserName(), user.getUserName());
            if (loginUser == null ) {
                return "用户不存在";
            }
            else if(!loginUser.getPassword().equals(user.getPassword())){
                return "密码错误";
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, loginUser.getId().toString()+Const.PASSWORD_KEY);
            cookie.setMaxAge(Const.COOKIE_TIMEOUT);
            cookie.setPath("/");
            response.addCookie(cookie);
            request.getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
            return "登录成功";
            /*String preUrl = "/";
            if(null != getSession().getAttribute(Const.LAST_REFERER)){
                preUrl = String.valueOf(getSession().getAttribute(Const.LAST_REFERER));
                if(preUrl.indexOf("/collect?") < 0 && preUrl.indexOf("/lookAround/standard/") < 0
                        && preUrl.indexOf("/lookAround/simple/") < 0){
                    preUrl = "/";
                }
            }
            if(preUrl.indexOf("/lookAround/standard/") >= 0){
                preUrl = "/lookAround/standard/ALL";
            }
            if(preUrl.indexOf("/lookAround/simple/") >= 0){
                preUrl = "/lookAround/simple/ALL";
            }
            return new ResponseData(ExceptionMsg.SUCCESS, preUrl);*/
        } catch (Exception e) {
            // TODO: handle exception
            return "登录失败";
        }

    }

    //更新个人简介
    @Override
    public String updateIntroduction(String introduction, String email) {
        userRepository.setUserIntroduction(introduction,email);
        return "修改成功";
    }
    //更新头像
    @Override
    public String updateHead(String dataUrl, String email) {
        userRepository.setProfilePicture(dataUrl,email);
        return "修改成功";
    }

    //更新昵称
    @Override
    public String updateNickName(String newNickName, String email) {
        userRepository.setUserName(newNickName,email);
        return "修改成功";
    }

    @Override
    public String updateBgp(String dataUrl, String email) {
        userRepository.setBackgroundPicture(dataUrl,email);
        return "修改成功";
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

    //获取user
    @Override
    public User findUser(long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    //查询所有user
    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }




}
