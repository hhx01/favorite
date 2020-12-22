package com.favorites.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 评论
 */
@Entity
public class Comment extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//评论id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	//
	private Long id;
	@Column(nullable = false)
	private Long collectId;
	//评论文本
	@Column(nullable = false, length = 65535, columnDefinition = "Text")
	private String content;
	//用户id
	@Column(nullable = false)
	private Long userId;
	//回复的用户id
	@Column(nullable = true)
	private Long replyUserId;
	//评论创建时间
	@Column(nullable = false)
	private Long createTime;
	@Transient
	private String commentTime;
	@Transient
	private String userName;
	@Transient
	private String replyUserName;
	@Transient
	private String profilePicture;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}