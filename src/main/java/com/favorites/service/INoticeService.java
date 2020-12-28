package com.favorites.service;

import com.favorites.domain.Notice;

import org.springframework.data.domain.Pageable;

import com.favorites.domain.view.CollectSummary;

import java.util.List;

public interface INoticeService {


    public void saveNotice(String collectId,String type,Long userId,String operId);

    public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable);
}
