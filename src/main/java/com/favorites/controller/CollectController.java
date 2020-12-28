package com.favorites.controller;

import com.favorites.domain.Collect;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.favorites.service.Impl.CollectService;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    //收藏
    @RequestMapping("/docollect")
    public String collect(Collect collect){
        collectService.saveCollect(collect);
        return "保存成功";
    }

}
