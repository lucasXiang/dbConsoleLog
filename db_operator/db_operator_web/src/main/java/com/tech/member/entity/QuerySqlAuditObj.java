package com.tech.member.entity;

public class QuerySqlAuditObj {
	private String excReasons;
	private String excStaus;
	private String excDataSourId;
	private String excOper;
	
	/**
	 * 提交的起始日期
	 */
	private String startTime;
	private String endTime;
	
	/**
	 * 分页
	 */
	private Integer pageNum=1;
	private Integer pageSize=10;
	/**
	 * 审核人主键信息
	 */
	private String auditorId;
	
	/**
	 * 审核信息
	 */
	private String auditStatus;
	private String auditExp;
	/**
	 * sql语义信息主键值
	 */
	private String id;
	
	private String curUserId;
	
	
	public String getCurUserId() {
		return curUserId;
	}
	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditExp() {
		return auditExp;
	}
	public void setAuditExp(String auditExp) {
		this.auditExp = auditExp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}
	public String getExcReasons() {
		return excReasons;
	}
	public void setExcReasons(String excReasons) {
		this.excReasons = excReasons;
	}
	public String getExcStaus() {
		return excStaus;
	}
	public void setExcStaus(String excStaus) {
		this.excStaus = excStaus;
	}
	
	public String getExcDataSourId() {
		return excDataSourId;
	}
	public void setExcDataSourId(String excDataSourId) {
		this.excDataSourId = excDataSourId;
	}
	public String getExcOper() {
		return excOper;
	}
	public void setExcOper(String excOper) {
		this.excOper = excOper;
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
