package com.favorites.service.Impl;

import com.favorites.domain.Letter;
import com.favorites.domain.enums.LetterType;
import com.favorites.domain.view.LetterSummary;
import com.favorites.domain.view.LetterView;
import com.favorites.repository.ILetterRepository;
import com.favorites.service.ILetterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LetterService implements ILetterService {

    @Autowired
    private ILetterRepository iLetterRepository;

    public void sentLetter(Letter letter){
        letter.setType(LetterType.ORIGINAL);

        letter.setCreateTime(System.currentTimeMillis());
    }

    public List<LetterSummary> findLetter(Long userId, Pageable pageable){
        List<LetterView> viewList = iLetterRepository.findLetterByReceiveUserif(userId, pageable);
        List<LetterSummary> summaryList = new ArrayList<LetterSummary>();
        for(LetterView view : viewList){
            LetterSummary summary = new LetterSummary(view);
            summaryList.add(summary);
        }
        return summaryList;
    }

}
