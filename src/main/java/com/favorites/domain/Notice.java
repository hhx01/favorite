package com.favorites.domain;

import com.favorites.domain.enums.NoticeType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 通知实体类
 * 
 */
@Entity
public class Notice extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//通知的id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//被通知的用户id
	@Column(nullable = false)
	private Long userId;
	//通知所在收藏条目的id
	@Column(nullable = true)
	private String collectId;
	//通知类型（@，评论，赞，私信）

	@Column(nullable = false)
	private String type;
	//
	@Column(nullable = true)
	private String operId;
	//是否已读
	@Column(nullable = false)
	private String readed;
	//通知时间
	@Column(nullable = false)
	private Long createTime;

	public Notice() {
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

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}