package com.tech.member.entity;

import com.tech.member.constant.PubConstant;

public class SmsTempObj {
	private String templateId;
	private String head;
	private String body;
	private String sendChannel;
	private String remark;
	private String createUser;
	private String createTime;

	private String startTime;
	private String endTime;

	// 分页参数
	private Integer pageSize;
	private Integer pageNum;
	private Integer startRow;

	private String type;

	// 分页设置的初始化 ： 起始位置：0 、 pageSize:10、页面：第一页
	public void initPage() {
		setStartRow(PubConstant.PAGE_PARAMS_INIT_STARTROW);
		setPageSize(PubConstant.PAGE_PARAMS_INIT_PAGESIZE);
		setPageNum(PubConstant.PAGE_PARAMS_INIT_PAGENUM);
	}

	// 根据页面大小和页数 求查找的起始位置
	public void setStartRowByPageParam() {
		setStartRow((getPageNum() - 1) * getPageSize());
	}
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSendChannel() {
		return sendChannel;
	}

	public void setSendChannel(String sendChannel) {
		this.sendChannel = sendChannel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
