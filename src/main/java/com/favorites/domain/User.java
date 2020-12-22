package com.favorites.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体
 */
@Entity
public class User extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//用户主键id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//用户名
	@Column(nullable = false, unique = true)
	private String userName;
	//密码
	@Column(nullable = false)
	private String passWord;
	//email
	@Column(nullable = false, unique = true)
	private String email;
	//用户头像
	@Column(nullable = true)
	private String profilePicture;
	//个人描述
	@Column(nullable = true,length = 65535,columnDefinition="Text")
	private String introduction;
	//创建时间
	@Column(nullable = false)
	private Long createTime;
	//最近修改时间
	@Column(nullable = false)
	private Long lastModifyTime;
//	//
//	@Column(nullable = true)
//	private String outDate;
//	//
//	@Column(nullable = true)
//	private String validataCode;
	//用户主页背景图
	@Column(nullable = true)
	private String backgroundPicture;

	public User() {
		super();
	}
	public User(String email, String nickName, String passWord, String userName) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.userName = userName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
//	public String getOutDate() {
//		return outDate;
//	}
//	public void setOutDate(String outDate) {
//		this.outDate = outDate;
//	}
//	public String getValidataCode() {
//		return validataCode;
//	}
//	public void setValidataCode(String validataCode) {
//		this.validataCode = validataCode;
//	}
	public String getBackgroundPicture() {
		return backgroundPicture;
	}
	public void setBackgroundPicture(String backgroundPicture) {
		this.backgroundPicture = backgroundPicture;
	}
}