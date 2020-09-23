package com.shiant.study.core.system.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shiant.common.BaseEntity;

@Entity(name="t_sys_carousel")
public class Carousel extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 2997355432220175074L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="title")
	private String title;
	
	@Column(name="url")
	private String url;
	
	@Column(name="file_url")
	private String fileUrl;

	@Column(name="[order]")
	private Integer order;
	
	@Column(name="create_date")
	private Date createDate;

	@Column(name="update_date")
	private Date updateDate;
	
	@Column(name="creater")
	private String creater;
	
	@Column(name="updater")
	private String updater;
	
	@Column(name="is_delete")
	private boolean isDelete;
	
	@Column(name="org_name")
	private String orgName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
