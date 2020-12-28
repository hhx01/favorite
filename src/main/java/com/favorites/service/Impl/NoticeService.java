package com.favorites.service.Impl;

import com.favorites.domain.Notice;
import com.favorites.domain.User;
import com.favorites.domain.view.CollectSummary;
import com.favorites.domain.view.CollectView;
import com.favorites.domain.view.CommenView;
import com.favorites.repository.ICommentRepository;
import com.favorites.repository.INoticeRepository;
import com.favorites.repository.IPraiseRepository;
import com.favorites.repository.IuserRepository;
import com.favorites.service.INoticeService;
import com.favorites.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("noticeService")
public class NoticeService implements INoticeService {

    @Autowired
    private INoticeRepository noticeRepository;

    @Autowired
    private IPraiseRepository praiseRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IuserRepository iuserRepository;

    @Override
    public void saveNotice(String collectId,String type,Long userId,String operId){
        Notice notice = new Notice();
        if(StringUtils.isNotBlank(collectId)){
            notice.setCollectId(collectId);
        }
        notice.setReaded("unread");
        notice.setType(type);
        if(StringUtils.isNotBlank(operId)){
            notice.setOperId(operId);
        }
        notice.setUserId(userId);
        notice.setCreateTime(System.currentTimeMillis());
        noticeRepository.save(notice);
    }

    @Override
    public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable) {

        Page<CollectView> views = noticeRepository.findViewByUserIdAndType(userId, type, pageable);
        return convertCollect(views, type);
    }

    private List<CollectSummary> convertCollect(Page<CollectView> views, String type) {
        List<CollectSummary> summarys=new ArrayList<CollectSummary>();
        for (CollectView view : views) {
            CollectSummary summary=new CollectSummary(view);
            if("at".equals(type)){
                summary.setCollectTime(DateUtils.getTimeFormatText(view.getLastModifyTime())+" at了你");
            }else if("comment".equals(type)){
                CommenView comment = commentRepository.findReplyUser(Long.valueOf(view.getOperId()));
                if(comment == null){
                    continue;
                }
                summary.setUserId(comment.getUserId());
                summary.setUserName(comment.getUserName());
                summary.setProfilePicture(comment.getProfilePicture());
                if(comment.getReplyUserId() != null && comment.getReplyUserId() != 0){
                    User replyUser = iuserRepository.findById(comment.getReplyUserId().longValue());
                    summary.setRemark("回复@"+replyUser.getUserName()+": "+comment.getContent());
                }else{
                    summary.setRemark(comment.getContent());
                }
                summary.setCollectTime(DateUtils.getTimeFormatText(comment.getCreateTime()));
            }else if("praise".equals(type)){
                CommenView comment = praiseRepository.findPraiseUser(Long.valueOf(view.getOperId()));
                if(comment == null){
                    continue;
                }
                summary.setUserId(comment.getUserId());
                summary.setUserName(comment.getUserName());
                summary.setProfilePicture(comment.getProfilePicture());
                summary.setCollectTime(DateUtils.getTimeFormatText(comment.getCreateTime())+" 赞了你的收藏");
            }
            summarys.add(summary);
        }
        return summarys;
    }
}