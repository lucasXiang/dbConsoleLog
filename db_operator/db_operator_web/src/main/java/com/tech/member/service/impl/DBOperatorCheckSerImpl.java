package com.tech.member.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.tech.member.entity.DBOperObj;
import com.tech.member.service.DBOperatorCheckSer;

@Service
public class DBOperatorCheckSerImpl implements DBOperatorCheckSer {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public boolean checkDataSourceInfo(DBOperObj obj) throws Exception{
		JdbcTemplate jdbcTemple=null;
		boolean res=false;
		try {
			jdbcTemple = getExcSqlJdbcTemplate(obj);
			if(jdbcTemple!=null){
				res=true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		
		return res;

	}

	@Override
	public boolean checkQuerySql(String sql,DBOperObj obj) throws Exception {
		boolean res=false;
		JdbcTemplate jdbcTemplate=null;
		Connection conn=null;
		try{
			jdbcTemplate=getExcSqlJdbcTemplate(obj);
			
			conn=jdbcTemplate.getDataSource().getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			
			throw e;
		}finally{
			if(conn!=null){
				conn.rollback();
				conn.close();
			}
		}
		
		return res;
	}

	@Override
	public JdbcTemplate getExcSqlJdbcTemplate(DBOperObj obj) throws Exception {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		JdbcTemplate jdbcTemple=new JdbcTemplate();
		Connection conn=null;
		if(obj.getExcDataSourceObj()==null){
			return null;
		}
		try {
			dataSource.setUrl(obj.getExcDataSourceObj().getDburl());
			dataSource.setDriverClassName(obj.getExcDataSourceObj().getDriverClass());
			dataSource.setUsername(obj.getExcDataSourceObj().getUsername());
			dataSource.setPassword(obj.getExcDataSourceObj().getPwd());
			
			jdbcTemple.setDataSource(dataSource);
			
			conn=jdbcTemple.getDataSource().getConnection();
			
			if(conn!=null){
				return jdbcTemple;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

}
