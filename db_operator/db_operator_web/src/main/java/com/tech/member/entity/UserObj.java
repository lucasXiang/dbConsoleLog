package com.tech.member.entity;

public class UserObj {
	/**
	 * 用户名
	 */
	private String opernum;
	/**
	 * 密码
	 */
	private String operpwd;
	/**
	 * 用户 主键值
	 */
	private String operid;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 函数id
	 */
	private String funcIds;
	/**
	 * 验证码
	 */
	private String code;
	
	public String getFuncIds() {
		return funcIds;
	}
	public void setFuncIds(String funcIds) {
		this.funcIds = funcIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperid() {
		return operid;
	}
	public void setOperid(String operid) {
		this.operid = operid;
	}
	public String getOpernum() {
		return opernum;
	}
	public void setOpernum(String opernum) {
		this.opernum = opernum;
	}
	public String getOperpwd() {
		return operpwd;
	}
	public void setOperpwd(String operpwd) {
		this.operpwd = operpwd;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "UserObj [opernum=" + opernum + ", operpwd=" + operpwd + ", code=" + code + "]";
	}
	
	
}
