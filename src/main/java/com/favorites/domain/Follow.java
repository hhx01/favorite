package com.favorites.domain;

import com.favorites.domain.enums.FollowStatus;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 关注实体类
 * 
 */
@Entity
public class Follow extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//关注id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//被关注用户id
	@Column(nullable = false)
	private Long userId;
	//关注用户的id
	@Column(nullable = false)
	private Long followId;
	//关注状态
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private FollowStatus status;
	//创建时间
	@Column(nullable = false)
	private Long createTime;
	//最近修改时间
	@Column(nullable = false)
	private Long lastModifyTime;
	@Transient
	private String name;

	public Follow() {
		super();
	}

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

	public Long getFollowId() {
		return followId;
	}

	public void setFollowId(Long followId) {
		this.followId = followId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public FollowStatus getStatus() {
		return status;
	}

	public void setStatus(FollowStatus status) {
		this.status = status;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}