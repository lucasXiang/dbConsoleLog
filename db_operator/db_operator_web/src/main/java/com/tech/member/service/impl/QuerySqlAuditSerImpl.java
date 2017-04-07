package com.tech.member.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.QuerySqlAuditObj;
import com.tech.member.entity.UserObj;
import com.tech.member.model.QuerySqlAuditDao;
import com.tech.member.service.QuerySqlAuditSer;

@Service
public class QuerySqlAuditSerImpl implements QuerySqlAuditSer{
	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private QuerySqlAuditDao querySqlAuditDao;
	@Override
	public List<DBOperObj> toAuditSqlInfoList(QuerySqlAuditObj auditObj) throws Exception {
		List<DBOperObj> objs=null;
		try{
			objs=querySqlAuditDao.toAuditSqlInfoList(auditObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		
		return objs;
	}
	@Override
	public int countToAuditSqlList(QuerySqlAuditObj auditObj) throws Exception {
		int n=0;
		try{
			n=querySqlAuditDao.countToAuditSqlList(auditObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return n;
	}
	@Override
	public boolean updateOneSqlAuditStatus(QuerySqlAuditObj auditObj) throws Exception {
		boolean ifSuccess=false;
		try{
			querySqlAuditDao.updateOneSqlAuditStatus(auditObj);
			
			ifSuccess=true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		return ifSuccess;
	}

}
