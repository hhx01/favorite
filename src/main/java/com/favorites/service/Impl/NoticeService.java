package com.favorites.service.Impl;

import com.favorites.domain.Notice;
import com.favorites.domain.enums.NoticeType;
import com.favorites.repository.INoticeRepository;
import com.favorites.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeService implements INoticeService {
    
    @Autowired
    private INoticeRepository noticeRepository;
    @Override
    public void saveNotice(String collectId, NoticeType type, Long userId) {
        Notice notice = new Notice();
        notice.setCollectId(collectId);
        notice.setType(type);
        notice.setUserId(userId);
        notice.setReaded("unread");
        notice.setCreateTime(System.currentTimeMillis());
        noticeRepository.save(notice);
    }
}
