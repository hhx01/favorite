package com.favorites.service;

import com.favorites.domain.Notice;
import com.favorites.domain.enums.NoticeType;
import com.favorites.repository.INoticeRepository;

import java.util.List;

public interface INoticeService {
    public void saveNotice(String collectId, NoticeType type, Long userId);

}
