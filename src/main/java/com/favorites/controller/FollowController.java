package com.favorites.controller;

import com.favorites.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private IFollowService followService;

    @RequestMapping(value = "/getfollow/{userId}")
    public int getFollow(@PathVariable("userId")Long userId , Long baseUserId){
        followService.getFollow(userId , baseUserId);
        return 1;    //成功则返回1
    }

    @RequestMapping(value = "/cancelfollow/{userId}")
    public int cancelFollow(@PathVariable("userId")Long userId , Long baseUserId){
        followService.cancelFollow(userId,baseUserId);
        return 1;    //成功则返回1
    }

    @RequestMapping(value = "/judgefollow/{userId}")
    public int judgefollow(@PathVariable("userId")Long userId , Long baseUserId){
        return followService.judgeFollow(userId,baseUserId);   //返回1是已关注，0是未关注
    }

    @RequestMapping(value = "/myfollowid")
    public List<Long> myfollowid(Long baseUserId){
        return followService.myFollow(baseUserId);      //返回我关注的所有followid
    }

    @RequestMapping(value = "/followme")
    public List<String> followme(Long baseUserId){
        return followService.followMe(baseUserId);      //返回关注我的用户名列表
    }

    @RequestMapping(value = "/myfollowuser")
    public List<String> myfollowuser(Long followId){
        return followService.findUserByfollowId(followId);      //返回关注的用户详细信息
    }

    @RequestMapping(value = "/myfollowusername")
    public List<String> myfollowusername(Long baseUserId){
        return followService.findUsernameBybaseUserId(baseUserId);   //返回我关注的所有用户名
    }
}
