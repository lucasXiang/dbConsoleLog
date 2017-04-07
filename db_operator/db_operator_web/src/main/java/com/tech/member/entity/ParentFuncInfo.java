package com.tech.member.entity;

import java.util.List;

public class ParentFuncInfo {
	private String pid;
	private String pfuncValue;
	private String pfuncUrl;
	private String ppri;
	
	
	private List<FuncInfoObj> chFuncObjs;
	
	
	
	public String getPpri() {
		return ppri;
	}

	public void setPpri(String ppri) {
		this.ppri = ppri;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPfuncValue() {
		return pfuncValue;
	}

	public void setPfuncValue(String pfuncValue) {
		this.pfuncValue = pfuncValue;
	}

	public String getPfuncUrl() {
		return pfuncUrl;
	}

	public void setPfuncUrl(String pfuncUrl) {
		this.pfuncUrl = pfuncUrl;
	}

	public List<FuncInfoObj> getChFuncObjs() {
		return chFuncObjs;
	}

	public void setChFuncObjs(List<FuncInfoObj> chFuncObjs) {
		this.chFuncObjs = chFuncObjs;
	}
	
	
}
