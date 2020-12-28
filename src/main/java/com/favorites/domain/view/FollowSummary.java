package com.favorites.domain.view;

import java.io.Serializable;

public class FollowSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String userName;

    private String profilePicture;

    private String introduction;

    public FollowSummary(FollowView view){
        this.id = view.getUserId();

        this.userName = view.getUserName();

        this.profilePicture = view.getPicture();

        this.introduction = view.getIntroduction();
    }

    public Long getUserId(){return id;}

    public void setUserId(Long userId){this.id = userId;}

    public String getUserName(){return userName;}

    public void setUserName(String userName){this.userName = userName;}

    public String getProfilePicture(){return profilePicture;}

    public void setProfilePicture(String profilePicture){this.profilePicture = profilePicture;}

    public String getIntroduction(){return introduction;}

    public void setIntroduction(String introduction){this.introduction = introduction;}


}
