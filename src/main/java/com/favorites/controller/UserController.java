package com.favorites.controller;
import com.favorites.comm.Const;
import com.favorites.utils.FileUtil;
import com.favorites.domain.Collect;
import com.favorites.domain.Favorites;
import com.favorites.domain.User;
import com.favorites.domain.result.ExceptionMsg;
import com.favorites.domain.result.ResponseData;
import com.favorites.service.ICollectService;
import com.favorites.service.IFavoritesSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.favorites.service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Autowired
    private ICollectService collectService;
    @Autowired
    private IFavoritesSevice favoritesSevice;

    /*@Value("${static.url}")
    private String staticUrl;
    @Value("${file.profilepictures.url}")
    private String fileProfilepicturesUrl;
    @Value("${file.backgroundpictures.url}")
    private String fileBackgroundpicturesUrl;*/

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public String regist(@RequestBody(required = false) User user){
        String result = userService.create(user);
        return result;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user,HttpServletResponse response){
        String result = userService.login(user,response);
        return result;
    }

    /*//修改背景
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public String uploadImg(String dataUrl,String email){

        try {
            String filePath=staticUrl+fileBackgroundpicturesUrl;
            String fileName= UUID.randomUUID().toString()+".png";
            String savePath = fileBackgroundpicturesUrl+fileName;
            String image = dataUrl;
            String header ="data:image";
            String[] imageArr=image.split(",");
            if(imageArr[0].contains(header)){
                image=imageArr[1];
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(image);
                FileUtil.uploadFile(decodedBytes, filePath, fileName);
                User user = userService.findUserByEmail(email);
                userService.updateBgp(dataUrl,email);
                user.setBackgroundPicture(savePath);
                //getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            }
            return "修改成功";
        } catch (Exception e) {
            return "修改失败";
        }

    }*/

    @RequestMapping("/all")
    public List<User> findAll(){
        return userService.findAllUser();
    }

    //获取个人首页信息
    @RequestMapping("/userinfo/{userId}")
    public String getMyInfo(Model model, @PathVariable("userId")long userId){
        List<Collect> collectsOfUser= collectService.getUserCollects(userId);
        User userInfo = userService.findUser(userId);
        int countOfCollects = collectsOfUser.size();
        List<Favorites> userFavorites = favoritesSevice.getAllFavorites(userId);
        /*
        * 获取用户关注与粉丝*/

        model.addAttribute("collectsOfUser",collectsOfUser);
        model.addAttribute("userInfo",userInfo);
        model.addAttribute("countOfCollects",countOfCollects);
        model.addAttribute("userFavorites",userFavorites);
        return "获取用户首页信息";
    }
}
