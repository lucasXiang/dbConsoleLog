package com.tech.member.entity;

public class RegistInfo {
	private String userName;
	private String name;
	private String password;
	private String sex;
	private String funParamsStr;
	private String id;
	private String dbSoursStr;
	private String funcMenusStr;
	
	
	public String getFuncMenusStr() {
		return funcMenusStr;
	}
	public void setFuncMenusStr(String funcMenusStr) {
		this.funcMenusStr = funcMenusStr;
	}
	public String getDbSoursStr() {
		return dbSoursStr;
	}
	public void setDbSoursStr(String dbSoursStr) {
		this.dbSoursStr = dbSoursStr;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFunParamsStr() {
		return funParamsStr;
	}
	public void setFunParamsStr(String funParamsStr) {
		this.funParamsStr = funParamsStr;
	}
	
	
}
