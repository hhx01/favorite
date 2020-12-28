package com.favorites.controller;

import com.favorites.domain.Letter;
import com.favorites.domain.view.LetterSummary;
import com.favorites.service.ILetterService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/letter")
public class LetterController {

    @Resource
    private ILetterService letterService;

    @RequestMapping("/sentLetter")
    public void sentLetter(Long reciveUserId,Long baseUserId , String content ){
        Letter letter = null;
        letter.setContent(content);
        letter.setCreateTime(System.currentTimeMillis());
        letter.setReceiveUserId(reciveUserId);
        letter.setSendUserId(baseUserId);
        letterService.sentLetter(letter);
    }

    //获取私信列表
    @RequestMapping("/findLetterList")
    public List<LetterSummary> findLetterList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "15") Integer size,Long baseUserId){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size,sort);
        List<LetterSummary> letterList = letterService.findLetter(baseUserId,pageable);
        return letterList;
    }
}
