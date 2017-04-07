package com.tech.member.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.UserObj;
import com.tech.member.entity.UserParam;
import com.tech.member.model.DBLoginDao;
import com.tech.member.service.DBLoginSer;

@Service
public class DBLoginSerImpl implements DBLoginSer{
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DBLoginDao dbLoginDao ;
	@Override
	public UserObj queryUserByUserMap(UserParam userMap) throws Exception {
		UserObj objs=null;
		try{
			objs=dbLoginDao.queryUserByUserMap(userMap);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return objs;
	}
	@Override
	public List<ParentFuncInfo> getFunctionTreeByFuncIds(String funcIds) throws Exception {
		List<ParentFuncInfo> funcs=null;
		try{
			funcs=dbLoginDao.getFunctionTreeByFuncIds(funcIds);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return funcs;
	}
	
}
