package com.favorites.controller;

import com.favorites.domain.Comment;
import com.favorites.domain.Notice;
import com.favorites.domain.result.ExceptionMsg;
import com.favorites.domain.result.ResponseData;
import com.favorites.domain.view.CollectSummary;
import com.favorites.domain.view.LetterSummary;
import com.favorites.repository.ICommentRepository;
import com.favorites.repository.INoticeRepository;
import com.favorites.service.ILetterService;
import com.favorites.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeRepository noticeRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Resource
    private INoticeService noticeService;

    @Resource
    private ILetterService letterService;

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public void reply(Comment comment , Long baseUserId) {
        comment.setUserId(baseUserId);
        comment.setCreateTime(System.currentTimeMillis());
        Comment saveCommon = commentRepository.save(comment);
        Notice notice = new Notice();
        notice.setCollectId(comment.getCollectId()+"");
        notice.setUserId(comment.getReplyUserId());
        notice.setType("comment");
        notice.setReaded("unread");
        notice.setOperId(saveCommon.getId().toString());
        notice.setCreateTime(System.currentTimeMillis());
        noticeRepository.save(notice);
    }

    @RequestMapping(value="/getNoticeNum")
    public ResponseData getNoticeNum(Long baseUserId){
        Map<String,Long> result = new HashMap<String, Long>();
        Long newAtMeCount = noticeRepository.countByUserIdAndTypeAndReaded(baseUserId, "at", "unread");
        Long newCommentMeCount = noticeRepository.countByUserIdAndTypeAndReaded(baseUserId, "comment", "unread");
        Long newPraiseMeCount = noticeRepository.countPraiseByUserIdAndReaded(baseUserId, "unread");
        Long newLetterNotice = noticeRepository.countByUserIdAndTypeAndReaded(baseUserId,"letter","unread");
        result.put("newAtMeCount",newAtMeCount);
        result.put("newCommentMeCount",newCommentMeCount);
        result.put("newPraiseMeCount",newPraiseMeCount);
        result.put("newLetterNotice",newLetterNotice);
        return new ResponseData(ExceptionMsg.SUCCESS,result);
    }

    //@页面
    @RequestMapping(value="/atMe/{baseUserId}")
    public String atMe(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "15") Integer size ,@PathVariable("baseUserId") Long baseUserId) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size,sort);
        List<CollectSummary> collects=noticeService.getNoticeCollects("at", baseUserId, pageable);
        model.addAttribute("collects", collects);
        noticeRepository.updateReadedByUserId("read",baseUserId,"at");
        return "notice/atme";
    }

    //评论页面
    @RequestMapping(value="/commentMe/{baseUserId}")
    public String commentMe(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "15") Integer size ,@PathVariable("baseUserId") Long baseUserId) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size,sort);
        List<CollectSummary> collects=noticeService.getNoticeCollects("comment", baseUserId, pageable);
        model.addAttribute("collects", collects);
        noticeRepository.updateReadedByUserId("read",baseUserId,"comment");
        return "notice/commentme";
    }

    //点赞页面
    @RequestMapping(value="/praiseMe/{baseUserId}")
    public String praiseMe(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "15") Integer size ,@PathVariable("baseUserId") Long baseUserId) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size,sort);
        List<CollectSummary> collects=noticeService.getNoticeCollects("praise", baseUserId, pageable);
        model.addAttribute("collects", collects);
        noticeRepository.updateReadedByUserId("read",baseUserId,"praise");
        return "notice/praiseme";
    }

    //私信页面
    @RequestMapping("/letter/letterMe/{baseUserId}")
    public String letterMe(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                           @RequestParam(value = "size", defaultValue = "15") Integer size ,@PathVariable("baseUserId") Long baseUserId){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size,sort);
        List<LetterSummary> letterList = letterService.findLetter(baseUserId,pageable);
        model.addAttribute("letterList",letterList);
        noticeRepository.updateReadedByUserId("read",baseUserId,"letter");
        return "notice/letterme";
    }
}
