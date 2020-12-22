package com.favorites.domain;

import com.favorites.domain.enums.LetterType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 私信实体类
 * 
 */
@Entity
public class Letter extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//私信条目的id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//发送者的id
	@Column(nullable = false)
	private Long sendUserId;
	//发送或者接收的文本
	@Column(nullable = false, length = 65535, columnDefinition = "Text")
	private String content;
	//接收者的id
	@Column(nullable = false)
	private Long receiveUserId;
	//
	@Column(nullable = true)
	private Long pid;
	//信件的种类（ORIGINAL,REPLY）发送或者接收
	@Enumerated(EnumType.STRING)
	@Column(nullable = true)
	private LetterType type;
	//letter的创建时间
	@Column(nullable = false)
	private Long createTime;
	@Transient
	private String sendType;


	public Letter() {
		super();
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Long sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(Long receiveUserId) {
		this.receiveUserId = receiveUserId;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public LetterType getType() {
		return type;
	}

	public void setType(LetterType type) {
		this.type = type;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
}