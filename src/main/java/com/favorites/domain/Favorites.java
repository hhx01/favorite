package com.favorites.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 收藏夹实体
 *
 */
@Entity
public class Favorites extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	//收藏项目id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	//收藏用户id
	@Column(nullable = false)
	private Long userId;
	//收藏夹名称
	@Column(nullable = false)
	private String name;
	//收藏夹条目的数目
	@Column(nullable = false)
	private Long count;
	//收藏夹创建时间
	@Column(nullable = false)
	private Long createTime;
	//最近修改时间
	@Column(nullable = false)
	private Long lastModifyTime;
	//？
	@Column(nullable = false)
	private Long publicCount;
	
	public Long getPublicCount() {
		return publicCount;
	}
	public void setPublicCount(Long publicCount) {
		this.publicCount = publicCount;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
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

	@Override
	public String toString() {
		return "Favorites{" +
				"id=" + id +
				", userId=" + userId +
				", name='" + name + '\'' +
				", count=" + count +
				", createTime=" + createTime +
				", lastModifyTime=" + lastModifyTime +
				", publicCount=" + publicCount +
				'}';
	}
}