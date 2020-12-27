package com.favorites.service;


import java.util.List;

public interface IFollowService {
    public void getFollow(Long followedUserId , Long followUserId);

    public void cancelFollow(Long followedUserId , Long followUserId);

    public int judgeFollow(Long followedUserId , Long followUserId);

    public List<Long> myFollow(Long baseUserId);

    public List<String> followMe(Long baseUserId);

    public List<String> findUserByfollowId(Long followId);

    public List<String> findUsernameBybaseUserId(Long baseUserId);
}
