package com.favorites.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * url保存实体
 */
@Entity
public class UrlLibrary extends Entitys implements Serializable{

    //url的id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    //url内容
    @Column(nullable = false, columnDefinition = "varchar(600)")
    private String url;
    //url的logo
    @Column(nullable = true,columnDefinition = "varchar(300)")
    private String logoUrl;
    //收藏url的主题
    @Column(nullable = false)
    private String title;
    //
    @Column(columnDefinition="INT default 0")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
