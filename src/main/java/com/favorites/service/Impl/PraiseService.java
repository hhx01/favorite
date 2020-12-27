package com.favorites.service.Impl;

import com.favorites.domain.Praise;
import com.favorites.repository.IPraiseRepository;
import com.favorites.service.IFollowService;
import com.favorites.service.IPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PraiseService implements IPraiseService {

    @Autowired
    private IPraiseRepository praiseRepository;

    @Override
    public Long countPraise(Long collectId){
        return praiseRepository.countByCollectId(collectId);
    }

    @Override
    public int judgePraise(Long userId , Long collectId){
        Praise praise = praiseRepository.findByUserIdAndCollectId(userId,collectId);
        if ( null != praise)     return 1;
        else return 0;
    }

    @Override
    public void addPraise(Long userId , Long collectId){
        Praise praise = praiseRepository.findByUserIdAndCollectId(userId,collectId);
        if(praise == null){
            praise = new Praise();
            praise.setCollectId(collectId);
            praise.setUserId(userId);
            praise.setCreateTime(System.currentTimeMillis());
            praiseRepository.save(praise);
        }
    }

    @Override
    public void cancelPraise(Long userId ,Long collectId){
        Praise praise = praiseRepository.findByUserIdAndCollectId(userId,collectId);
        if( null != praise){
            praiseRepository.deleteById(praise.getId());
        }
    }

}
