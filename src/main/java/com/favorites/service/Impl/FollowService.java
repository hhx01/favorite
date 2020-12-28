package com.favorites.service.Impl;

import com.favorites.domain.Follow;
import com.favorites.domain.enums.FollowStatus;
import com.favorites.repository.IFollowRepository;
import com.favorites.service.IFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("followService")
public class FollowService implements IFollowService {
    @Autowired
    private IFollowRepository followRepository;

    @Override
    public void getFollow(Long followedUserId , Long followUserId){
        Follow follow = followRepository.findByUserIdAndFollowId(followUserId, followedUserId);
        if(follow == null){
            follow = new Follow();
            follow.setFollowId(followedUserId);
            follow.setUserId(followUserId);
            follow.setStatus(FollowStatus.FOLLOW);
            follow.setCreateTime(System.currentTimeMillis());
            follow.setLastModifyTime(System.currentTimeMillis());
            followRepository.save(follow);
        }

        else if( null != follow && follow.getStatus() == FollowStatus.UNFOLLOW){

            followRepository.updateStatusById(FollowStatus.FOLLOW, System.currentTimeMillis(), follow.getId());
        }
    }

    @Override
    public void cancelFollow(Long followedUserId , Long followUserId){
        FollowStatus followStatus = FollowStatus.UNFOLLOW;
        Follow follow = followRepository.findByUserIdAndFollowId(followUserId, followedUserId);
        if(null != follow){
            followRepository.updateStatusById(followStatus, System.currentTimeMillis(), follow.getId());
        }
    }

    @Override
    public int judgeFollow(Long followedUserId , Long followUserId){
        Follow follow = followRepository.findByUserIdAndFollowId(followUserId, followedUserId);
        if(null !=follow && follow.getStatus() == FollowStatus.FOLLOW)  return 1;
        else return 0;
    }

    @Override
    public List<Long> myFollow(Long baseUserId){
        return followRepository.findMyFollowIdByUserId(baseUserId);
    }

    @Override
    public List<String> followMe(Long baseUserId){
        return followRepository.findFollowUserByUserId(baseUserId);
    }

    @Override
    public List<String> findUserByfollowId(Long followId){
        return followRepository.findFollowedUserByFollowId(followId);
    }

    @Override
    public List<String> findUsernameBybaseUserId(Long baseUserId){
        return followRepository.findByUserId(baseUserId);
    }
}
