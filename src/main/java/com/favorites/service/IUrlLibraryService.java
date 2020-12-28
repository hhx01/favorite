package com.favorites.service;

import com.favorites.domain.UrlLibrary;
import com.favorites.repository.IUrlLibraryRepository;

public interface IUrlLibraryService {
    public void saveUrl(UrlLibrary url);
}
