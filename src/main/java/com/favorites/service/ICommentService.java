package com.favorites.service;

import com.favorites.domain.Comment;

import java.util.List;

public interface ICommentService {

    public void addComment(Comment comment);

    public Long getCount(Long collectId);

    public List<Comment>  commentList(Long collectId);

    public void delectComment(Long id);

}
