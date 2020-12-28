package com.favorites.controller;

import com.favorites.domain.Favorites;
import com.favorites.domain.User;
import com.favorites.domain.view.CollectSummary;
import com.favorites.service.ILookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lookRecord")
public class LookRecordController {

    @Autowired
    private ILookRecordService lookRecordService;

    @RequestMapping(value="/save/{collectId}")
    public int saveLookRecord(@PathVariable("collectId") long collectId, Long baseUserId) {
        lookRecordService.saveLookRecord(baseUserId,collectId);
        return 1;
    }

    @RequestMapping(value = "/getrecord")
    public List<CollectSummary> getRecord(Long baseUserId , Pageable pageable){
        return lookRecordService.getLookRecords(baseUserId , pageable);
    }

    @RequestMapping(value="/delete/{collectId}")
    public int deleteLookRecord(@PathVariable("collectId") long collectId, Long baseUserId) {
        lookRecordService.deleteLookRecord(baseUserId,collectId);
        return 1;
    }

    @RequestMapping(value="/deleteAll")
    public int deleteAll(Long baseUserId) {
        lookRecordService.deleteLookRecordByUserID(baseUserId);
        return 1;
    }

    @RequestMapping(value="/standard/{baseUserId}")
    public String getLookRecordStandard(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "15") Integer size,
                                        @PathVariable("baseUserId") Long baseUserId) {

        Sort sort = new Sort(Sort.Direction.DESC, "lastModifyTime");
        Pageable pageable = PageRequest.of(page, size,sort);
//        model.addAttribute("type", "lookRecord");
        List<CollectSummary> collects = null;
        collects =lookRecordService.getLookRecords(baseUserId,pageable);
        model.addAttribute("collects", collects);
        return "lookRecord/standard";
    }

}
