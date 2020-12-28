package com.favorites.service.Impl;

import com.favorites.domain.UrlLibrary;
import com.favorites.repository.IUrlLibraryRepository;
import com.favorites.service.IUrlLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("urlLibraryService")
public class UrlLibraryServiceImpl implements IUrlLibraryService {

    @Autowired
    private IUrlLibraryRepository urlLibaryRepository;

    //保存url
    @Override
    public void saveUrl(UrlLibrary url) {
        UrlLibrary urlLibrary = urlLibaryRepository.findByUrl(url.getUrl());
        if(urlLibrary==null){
            urlLibaryRepository.save(url);
        }
        else {
            urlLibaryRepository.setCount(urlLibrary.getCount()+1,urlLibrary.getId());
        }
    }
}