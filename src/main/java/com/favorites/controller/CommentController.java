package com.favorites.controller;

import com.favorites.domain.Collect;
import com.favorites.domain.Comment;
import com.favorites.domain.User;
import com.favorites.repository.ICollectRepository;
import com.favorites.repository.IuserRepository;
import com.favorites.service.ICommentService;
import com.favorites.service.INoticeService;
import com.favorites.utils.DateUtils;
import com.favorites.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @Autowired
    private IuserRepository userRepository;

    @Resource
    private INoticeService noticeService;

    @Autowired
    private ICollectRepository collectRepository;


    @RequestMapping(value = "/addcomment")
    public void addComment(Comment comment, Long baseUserId){
        User user = null;
        if (comment.getContent().indexOf("@") > -1) {
            List<String> atUsers = StringUtil.getAtUser(comment.getContent());
            if(atUsers!=null && atUsers.size()>0){
                user = userRepository.findByUserName(atUsers.get(0));
                if (null != user) {
                    comment.setReplyUserId(user.getId());
                }
                String content=comment.getContent().substring(0,comment.getContent().indexOf("@"));
                if(StringUtils.isBlank(content)){
                    content=comment.getContent().substring(comment.getContent().indexOf("@")+user.getUserName().length()+1,comment.getContent().length());
                }
                comment.setContent(content);
            }
        }
        comment.setUserId(baseUserId);
        comment.setCreateTime(System.currentTimeMillis());
        commentService.addComment(comment);
        if(null != user){
            // 保存消息通知(回复)
            noticeService.saveNotice(String.valueOf(comment.getCollectId()), "comment", user.getId(), String.valueOf(comment.getId()));
        }else{
            // 保存消息通知（直接评论）
            Collect collect = colloectRepository.findById((long)comment.getCollectId());
            if(null != collect){
                noticeService.saveNotice(String.valueOf(comment.getCollectId()), "comment", collect.getUserId(), String.valueOf(comment.getId()));
            }
        }
    }

    @RequestMapping(value = "/countcomments")
    public Long countComments(Long collectId) {
        return commentService.getCount(collectId);
    }

    @RequestMapping(value = "/getcomment")
    public List<Comment> getComment(Long collectId){
        List<Comment> comments = commentService.commentList(collectId);
        return comments;
    }

    @RequestMapping(value = "/delectcomment")
    public void delectComment(Comment comment){
        commentService.delectComment(comment.getId());
    }
}
