package com.favorites.service.Impl;

import com.favorites.domain.LookRecord;
import com.favorites.domain.Praise;
import com.favorites.domain.view.CollectSummary;
import com.favorites.domain.view.CollectView;
import com.favorites.service.ILookRecordService;
import com.favorites.repository.IlookRecordRepository;
import com.favorites.repository.IPraiseRepository;
import com.favorites.repository.ICommentRepository;
import com.favorites.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("lookRecordService")
public class LookRecordService implements ILookRecordService {
    @Autowired
    private IlookRecordRepository lookRecordRepository;

    @Autowired
    private IPraiseRepository praiseRepository;

    @Autowired
    private ICommentRepository commentRepository;



    @Override
    public void saveLookRecord(Long userId,Long collectId) {
        if(userId != null && userId > 0 && collectId != null) {
            Integer count = lookRecordRepository.countByUserIdAndCollectId(userId, collectId);
            if (count > 0) {
                lookRecordRepository.updatelastModifyTime(userId, collectId, System.currentTimeMillis());
            } else {
                LookRecord lookRecord = new LookRecord();
                lookRecord.setUserId(userId);
                lookRecord.setCollectId(collectId);
                lookRecord.setCreateTime(System.currentTimeMillis());
                lookRecord.setLastModifyTime(System.currentTimeMillis());
                lookRecordRepository.save(lookRecord);
            }
        }

    }

    @Override
    public void deleteLookRecord(Long userId, Long collectId) {
        lookRecordRepository.deleteByUserIdAndCollectId(userId,collectId);
    }

    @Override
    public void deleteLookRecordByUserID(Long userId) {
        lookRecordRepository.deleteByUserId(userId);
    }

    @Override
    public List<CollectSummary> getLookRecords(Long userId, Pageable pageable) {
        Page<CollectView> views = null;

        views = lookRecordRepository.findViewByUserId(userId, pageable);

        return convertCollect(views,userId);
    }

    private List<CollectSummary> convertCollect(Page<CollectView> views,Long userId) {
        List<CollectSummary> summarys=new ArrayList<CollectSummary>();
        for (CollectView view : views) {
            CollectSummary summary=new CollectSummary(view);
            summary.setPraiseCount(praiseRepository.countByCollectId(view.getId()));
            summary.setCommentCount(commentRepository.countByCollectId(view.getId()));
            Praise praise=praiseRepository.findByUserIdAndCollectId(userId, view.getId());
            if(praise!=null){
                summary.setPraise(true);
            }else{
                summary.setPraise(false);
            }
            summary.setCollectTime(DateUtils.getTimeFormatText(view.getCreateTime()));
            summarys.add(summary);
        }
        return summarys;
    }

}
