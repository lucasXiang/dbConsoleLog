package com.tech.member.entity;

public class DBOperObj {
	/**
	 * 执行目的
	 */
	private String excReasons;
	/**
	 * 标记状态
	 */
	private String excStaus;
	/**
	 * 要执行的sql
	 */
	private String excSql;
	
	/**
	 * 主键值
	 */
	private String id;
	/**
	 * 时间戳
	 */
	private String ts;
	
	/**
	 * 提交人ID
	 */
	private String curUserId;
	private UserObj submitObj;
	/**
	 * 起始日期
	 */
	private String startTime;
	/**
	 * 截止日期
	 */
	private String endTime;
	/**
	 * 审核状态
	 */
	private String auditStatus;
	/**
	 * 审核日期
	 */
	private String auditTime;
	/**
	 * 审核说明
	 */
	private String auditExp;
	
	/**
	 * 分页查询
	 */
	private Integer pageNum=1;
	private Integer pageSize=10;
	
	/**
	 * 要查询的数据库源信息
	 */
	private String excDbId;
	private ExcDataSourceObj excDataSourceObj;
	/**
	 * 审核人信息
	 */
	private String auditor;
	private UserObj auditorObj;
	
	private String toAuditor;
	
	
	
	public String getToAuditor() {
		return toAuditor;
	}
	public void setToAuditor(String toAuditor) {
		this.toAuditor = toAuditor;
	}
	public UserObj getSubmitObj() {
		return submitObj;
	}
	public void setSubmitObj(UserObj submitObj) {
		this.submitObj = submitObj;
	}
	public UserObj getAuditorObj() {
		return auditorObj;
	}
	public void setAuditorObj(UserObj auditorObj) {
		this.auditorObj = auditorObj;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public ExcDataSourceObj getExcDataSourceObj() {
		return excDataSourceObj;
	}
	public void setExcDataSourceObj(ExcDataSourceObj excDataSourceObj) {
		this.excDataSourceObj = excDataSourceObj;
	}
	public String getExcDbId() {
		return excDbId;
	}
	public void setExcDbId(String excDbId) {
		this.excDbId = excDbId;
	}
	public String getAuditExp() {
		return auditExp;
	}
	public void setAuditExp(String auditExp) {
		this.auditExp = auditExp;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
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
	public String getExcSql() {
		return excSql;
	}
	public void setExcSql(String excSql) {
		this.excSql = excSql;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	
	public String getCurUserId() {
		return curUserId;
	}
	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
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
