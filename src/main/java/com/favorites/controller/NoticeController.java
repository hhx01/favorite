package com.favorites.controller;

import com.favorites.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping(value = "/savenotice")
    public void saveNotice(String collectId,String type,Long userId,String operId){
        noticeService.saveNotice(collectId,type,userId,operId);
    }


}
