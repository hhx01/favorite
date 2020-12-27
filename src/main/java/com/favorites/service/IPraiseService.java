package com.favorites.service;

public interface IPraiseService {
    public Long countPraise(Long collectId);

    public void addPraise(Long userId , Long collectId);

    public void cancelPraise(Long userId , Long collectId);

    public int judgePraise(Long userId , Long collectId);
}
