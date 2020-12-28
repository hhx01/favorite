package com.favorites.service.Impl;

import com.favorites.domain.Comment;
import com.favorites.domain.User;
import com.favorites.repository.ICommentRepository;
import com.favorites.repository.IuserRepository;
import com.favorites.service.ICommentService;
import com.favorites.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IuserRepository iuserRepository;

    @Override
    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> commentList(Long collectId){
        List<Comment> comments= commentRepository.findByCollectIdOrderByIdDesc(collectId);
        return convertComment(comments);
    }

    @Override
    public Long getCount(Long collectId){
        return commentRepository.countByCollectId(collectId);
    }

    @Override
    public void delectComment(Long id) {
        commentRepository.deleteById(id);
    }

    private List<Comment> convertComment(List<Comment> comments){

        for (Comment comment : comments) {
            User user = iuserRepository.findById((long)comment.getUserId());
            comment.setCommentTime(DateUtils.getTimeFormatText(comment.getCreateTime()));
            comment.setUserName(user.getUserName());
            comment.setProfilePicture(user.getProfilePicture());
            if(comment.getReplyUserId()!=null && comment.getReplyUserId()!=0){
                User replyUser = iuserRepository.findById((long)comment.getReplyUserId());
                comment.setReplyUserName(replyUser.getUserName());
            }
        }
        return comments;
    }
}
