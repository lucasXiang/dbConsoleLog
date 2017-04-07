package com.tech.member.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.member.entity.SmsTempObj;
import com.tech.member.model.DBLoginDao;
import com.tech.member.model.SmsTempDao;
import com.tech.member.service.SmsTempSer;

@Service
public class SmsTempSerImpl implements SmsTempSer{
	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private SmsTempDao smsTempDao;
	@Autowired
	private DBLoginDao dbLoginDao;
	@Override
	public List<SmsTempObj> getSmsTempInfos(SmsTempObj paramObj) throws Exception {
		List<SmsTempObj> smsTempObjs=null;
		try{
			smsTempObjs=smsTempDao.getSmsTempInfos(paramObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return smsTempObjs;
	}
	@Override
	public int countSmsTempInfos(SmsTempObj paramObj) throws Exception {
		int count=0;
		try{
			count=smsTempDao.countSmsTempInfos(paramObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return count;
	}
	@Override
	public boolean delSmsTempInfo(int id) throws Exception {
		boolean res=false;
		try{
			smsTempDao.delSmsTempInfo(id);
			
			res=true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return res;
	}
	@Override
	public boolean insertSmsTemp(SmsTempObj paramObj) throws Exception {
		boolean res=false;
		try{
			smsTempDao.insertSmsTemp(paramObj);
			
			res=true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return res;
	}
	@Override
	public SmsTempObj getSmsTempById(String templateId) throws Exception {
		SmsTempObj obj=null;
		try{
			obj=smsTempDao.getSmsTempById(templateId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return obj;
	}
	@Override
	public boolean modifyOneSmsTemp(SmsTempObj paramObj) throws Exception {
		boolean res=false;
		try{
			smsTempDao.modifyOneSmsTemp(paramObj);
			
			res=true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return res;
	}
	@Override
	public List<Map> getSendChannels() throws Exception {
		List<Map> scs=null;
		try{
			scs=smsTempDao.getSendChannels();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return scs;
	}
	@Override
	public String getFuncMenusByUserId(String id) throws Exception {
		String smsRole=null;
		try{
			smsRole=dbLoginDao.getFuncMenusByUserId(id);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return smsRole;
	}
}
