package com.favorites.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  浏览记录实体
 */
@Entity
public class LookRecord extends Entitys implements Serializable{

    private static final long serialVersionUID = 1L;

    //浏览记录id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //浏览用户的id
    @Column(nullable = false)
    private Long userId;
    //被浏览收藏条目的id
    @Column(nullable = false)
    private Long collectId;
    //记录时间
    @Column(nullable = false)
    private Long createTime;
    //最近修改时间
    @Column(nullable = false)
    private Long lastModifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
