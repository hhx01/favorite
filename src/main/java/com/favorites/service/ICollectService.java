package com.favorites.service;

import com.favorites.domain.Collect;

import java.util.List;

public interface ICollectService {

    public void saveCollect(Collect collect);
    public List<Collect> getAllCollects();
    public List<Collect> getUserCollects(long userId);
}
