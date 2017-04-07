package com.tech.member.entity;

public class UserInfo {
	private String id;
	private String userName;
	private String name;
	private String sex;
	private String roleNames;
	private String dbSids;
	private String smsMenus;
	
	/**
	 * 分页查询
	 */
	private Integer pageNum=1;
	private Integer pageSize=10;
	
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getDbSids() {
		return dbSids;
	}
	public void setDbSids(String dbSids) {
		this.dbSids = dbSids;
	}
	public String getSmsMenus() {
		return smsMenus;
	}
	public void setSmsMenus(String smsMenus) {
		this.smsMenus = smsMenus;
	}
	
	
}
