package com.tech.member.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.RegistInfo;
import com.tech.member.entity.UserInfo;
import com.tech.member.model.UserMagDao;
import com.tech.member.service.UserMagSer;
import com.tech.member.util.IDCreatedUtil;
import com.tech.member.util.Md5Encrypt;

@Service
public class UserMagSerImpl implements UserMagSer{
	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private UserMagDao userMagDao;
	
	@Override
	public List<ParentFuncInfo> getFunTreeParams() throws Exception {
		List<ParentFuncInfo> parFuns=null;
		try{
			parFuns=userMagDao.getFunTreeParams();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return parFuns;
	}
	@Override
	public boolean ifHasUserName(String userName) throws Exception {
		int resCn=0;
		try{
			resCn=userMagDao.ifHasUserName(userName);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		if(resCn>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean insertUserInfo(RegistInfo registInfo) throws Exception {
		if(registInfo.getId()==null||"".equals(registInfo.getId())){
			registInfo.setId(IDCreatedUtil.getId());
		}
		registInfo.setPassword(Md5Encrypt.md5(registInfo.getPassword()));
		boolean res=false;
		try{
			userMagDao.insertUserInfo(registInfo);
			
			res=true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return res;
	}
	@Override
	public List<ExcDataSourceObj> getDataSourceInfos() throws Exception {
		List<ExcDataSourceObj> dbSours=null;
		try{
			dbSours=userMagDao.getDataSourceInfos();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return dbSours;
	}
	@Override
	public int countUsers(UserInfo userInfo) throws Exception {
		int cn=0;
		try{
			cn=userMagDao.countUsers(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return cn;
	}
	@Override
	public List<UserInfo> getUsersList(UserInfo userInfo) throws Exception {
		List<UserInfo> userList=null;
		try{
			userList=userMagDao.getUsersList(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return userList;
	}
	@Override
	public boolean updateUserDelflagById(String userId) throws Exception {
		boolean res=false;
		try{
			userMagDao.updateUserDelflagById(userId);
			
			res=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return res;
	}
}
