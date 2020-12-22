package com.favorites.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 点赞实体
 */
@Entity
public class Praise extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//保存id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//被点赞收藏条目id
	@Column(nullable = false)
	private Long collectId;
	//点赞用户的id（应该是）
	@Column(nullable = false)
	private Long userId;
	//点赞时间
	@Column(nullable = false)
	private Long createTime;

	public Praise() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCollectId() {
		return collectId;
	}

	public void setCollectId(Long collectId) {
		this.collectId = collectId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}