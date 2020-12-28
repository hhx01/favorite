package com.favorites.service.Impl;

import com.favorites.domain.Letter;
import com.favorites.domain.User;
import com.favorites.domain.enums.LetterType;
import com.favorites.domain.view.LetterSummary;
import com.favorites.domain.view.LetterView;
import com.favorites.repository.ILetterRepository;
import com.favorites.repository.IuserRepository;
import com.favorites.service.ILetterService;
import com.favorites.service.INoticeService;
import com.favorites.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("letterService")
public class LetterService implements ILetterService {

    @Autowired
    private ILetterRepository iLetterRepository;

    @Autowired
    private IuserRepository userRepository;

    @Resource
    private INoticeService noticeService;

    @Override
    public void sentLetter(Letter letter){
        if("original".equals(letter.getSendType())){
            letter.setType(LetterType.ORIGINAL);
        }else{
            letter.setType(LetterType.REPLY);
            List<String> userNameList = StringUtil.getAtUser(letter.getContent());
            if(null != userNameList && userNameList.size() > 0){
                User receiveUser = userRepository.findByUserName(userNameList.get(0));
                if(null != receiveUser){
                    letter.setReceiveUserId(receiveUser.getId());
                }
                String content = letter.getContent().substring(0,letter.getContent().indexOf("@"));
                if(StringUtils.isBlank(content)){
                    content = letter.getContent().substring(letter.getContent().indexOf("@")+receiveUser.getUserName().length()+1,letter.getContent().length());
                    letter.setContent(content);
                }
            }
        }
        iLetterRepository.save(letter);
        if(null == letter.getPid()){
            letter.setPid(letter.getId());
            iLetterRepository.updatePidById(letter.getId(),letter.getId());
        }
        // 添加消息通知
        noticeService.saveNotice(null,"letter",letter.getReceiveUserId(),String.valueOf(letter.getId()));
    }

    @Override
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
