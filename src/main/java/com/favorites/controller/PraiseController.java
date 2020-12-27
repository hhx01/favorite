package com.favorites.controller;

import com.favorites.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/praise")
public class PraiseController {

    @Autowired
    private IPraiseService praiseService;

    @RequestMapping(value = "/count")
    public Long countPraiseByCollectId(Long collectId){
        return praiseService.countPraise(collectId);                     //返回此收藏获赞个数
    }

    @RequestMapping(value = "/judge")
    public int judgePraise(Long collectId,Long baseUserId){
        return praiseService.judgePraise(baseUserId,collectId);           //返回1表示当前用户已关注，0表示未关注
    }

    @RequestMapping(value = "/add")
    public int addPraise(Long collectId,Long baseUserId){
        praiseService.addPraise(baseUserId,collectId);
        return 1;                                  //返回1表示点赞成功
    }

    @RequestMapping(value = "/cancel")
    public int cancelPraise(Long collectId, Long baseUserId){
        praiseService.cancelPraise(baseUserId,collectId);
        return 1;                                  //返回1表示点赞取消
    }
}
