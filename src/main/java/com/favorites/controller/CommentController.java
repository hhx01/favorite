package com.favorites.controller;

import com.favorites.domain.Comment;
import com.favorites.service.ICommentService;
import com.favorites.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @RequestMapping(value = "/addcomment")
    public int addComment(Long baseUserId , String content , Long collectId){
        Comment comment = null;
        comment.setCollectId(collectId);
        comment.setCommentTime(DateUtils.getTimeFormatText(System.currentTimeMillis()));
        comment.setUserId(baseUserId);
        comment.setReplyUserId(null);
        comment.setContent(content);
        try {
            commentService.addComment(comment);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @RequestMapping(value = "/countcomments")
    public Long countComments(Long collectId) {
        return commentService.getCount(collectId);
    }

    @RequestMapping(value = "/getcomment")
    public List<Comment> getComment(Long collectId){
        return commentService.commentList(collectId);
    }

    @RequestMapping(value = "/delectcomment")
    public void delectComment(Comment comment){
        commentService.delectComment(comment.getId());
    }
}
