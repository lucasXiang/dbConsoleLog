package com.tech.member.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.tech.member.constant.PubConstant;
import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.UserObj;
import com.tech.member.model.DBQueryLogDao;
import com.tech.member.model.UserMagDao;
import com.tech.member.service.DBQueryLogSer;
import com.tech.member.util.DateFormateUtil;
import com.tech.member.util.IDCreatedUtil;
@Service
public class DBQueryLogSerImpl implements DBQueryLogSer{
	
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DBQueryLogDao dbQueryLogDao;
	@Autowired
	private UserMagDao userMagDao;
	
	@Override
	public boolean insertDBQuerySql(DBOperObj obj) throws Exception{
		boolean isSucc=false;
		//给参数对象添加 主键值和时间戳
		obj.setId(IDCreatedUtil.getId());
		obj.setTs(DateFormateUtil.formateDateToString("yyyy-MM-dd HH:mm:ss"));
		obj.setAuditStatus(PubConstant.AUDIT_STATUS_INIT);
		
		try{
			dbQueryLogDao.insertDBQuerySql(obj);
			isSucc=true;
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return isSucc;
	}
	@Override
	public List<DBOperObj> queryQuerySql(DBOperObj obj) throws Exception{
		List<DBOperObj> res=null;
		try {
			res=dbQueryLogDao.queryQuerySql(obj);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	@Override
	public int countQuerySql(DBOperObj obj) throws Exception {
		int n=0;
		try{
			n=dbQueryLogDao.countQuerySql(obj);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return n;
	}
	@Override
	public DBOperObj queryQuerySqlById(String querySqlId) throws Exception {
		DBOperObj obj=null;
		try{
			obj=dbQueryLogDao.queryQuerySqlById(querySqlId);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return obj;
	}
	@Override
	public List<ExcDataSourceObj> getDataSourceInfos() throws Exception {
		List<ExcDataSourceObj> objs=null;
		try{
			objs=userMagDao.getDataSourceInfos();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return objs;
	}
	@Override
	public ExcDataSourceObj getDataSourceById(String id) throws Exception {
		ExcDataSourceObj obj=null;
		try{
			obj=dbQueryLogDao.getDataSourceById(id);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return obj;
	}
	@Override
	public List<UserObj> getToAuditorByDBSourId(String dbSourId) throws Exception {
		List<UserObj> toAuditors=null;
		try{
			toAuditors=dbQueryLogDao.getDBSourAuditor(dbSourId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return toAuditors;
	}
	@Override
	public Properties getPropConf() throws Exception {
		
		Properties prop=new Properties();
		prop.load(new FileInputStream(new File(this.getClass().getResource("/fileConf/querySqlConsole.properties").getPath())));
		return prop;
	}
	
	
}
