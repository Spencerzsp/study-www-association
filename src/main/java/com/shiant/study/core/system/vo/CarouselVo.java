package com.shiant.study.core.system.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.shiant.common.BaseVO;

public class CarouselVo extends BaseVO implements Serializable,Comparable<CarouselVo> {

	private static final long serialVersionUID = -1690420727875072538L;
	
	private Long id;
	private String title;
	private String orgName;
	private String url;
	private String fileUrl;
	private Integer order;
	private Date createDate;
	private Date updateDate;
	private String creater;
	private String updater;
	private boolean isDelete;
	private MultipartFile[] coverFile;
	
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
	public MultipartFile[] getCoverFile() {
		return coverFile;
	}
	public void setCoverFile(MultipartFile[] coverFile) {
		this.coverFile = coverFile;
	}
	
	@Override
	public int compareTo(CarouselVo vo) {
		return this.order - vo.getOrder();
	}
	
}
