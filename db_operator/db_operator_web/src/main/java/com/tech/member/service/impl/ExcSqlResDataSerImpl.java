package com.tech.member.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.tech.member.entity.DBOperObj;
import com.tech.member.service.DBOperatorCheckSer;
import com.tech.member.service.ExcSqlResDataSer;

@Service
public class ExcSqlResDataSerImpl implements ExcSqlResDataSer {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DBOperatorCheckSer dbOperatorCheckSer;

	@Override
	public List getExcSqlRes(String excSql, DBOperObj obj) throws Exception {
		List result = null;
		JdbcTemplate jdbcTemplate = null;
		try {
			jdbcTemplate = dbOperatorCheckSer.getExcSqlJdbcTemplate(obj);
			// 拼接字符串 成分页查询 sql语句
			String sql = " select ww.* from ( " + " select rownum NO,w.* from (" + excSql + ") w " + " ) ww "+
			" where ww.NO > "+(obj.getPageNum()-1)*obj.getPageSize()+" and ww.NO < "+(obj.getPageNum()*obj.getPageSize()+1);

			result = jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}

		return result;
	}

	@Override
	public int countExcSqlRes(String excSql, DBOperObj obj) throws Exception {
		String sql = "SELECT COUNT(*) CN FROM (" + excSql + ")";
		JdbcTemplate jdbcTemplate = null;
		int n = 0;
		try {
			jdbcTemplate = dbOperatorCheckSer.getExcSqlJdbcTemplate(obj);
			n = ((BigDecimal) jdbcTemplate.queryForMap(sql).get("CN")).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}

		return n;
	}
	
	public List getAllExcSqlRes(String excSql, DBOperObj obj)throws Exception {
		String sql="select ww.* from ( select rownum r,w.* from ( "+excSql+" ) w ) ww where ww.r < ? ";
		Object[] objs=new Object[]{obj.getPageSize()};
		List result = null;
		JdbcTemplate jdbcTemplate = null;
		try {
			jdbcTemplate = dbOperatorCheckSer.getExcSqlJdbcTemplate(obj);
			result = jdbcTemplate.queryForList(sql,objs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}

		return result;
	}
}
