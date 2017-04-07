package com.tech.member.service;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tech.member.entity.DBOperObj;

public interface DBOperatorCheckSer {
	/**
	 * 切换数据源的连接配置信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  上午9:27:51
	 */
	public boolean checkDataSourceInfo(DBOperObj obj) throws Exception;
	/**
	 * 校验查询sql语义的正确性
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  上午9:35:21
	 * @param sql
	 * @return
	 */
	public boolean checkQuerySql(String sql,DBOperObj obj) throws Exception;
	/**
	 * 获取要执行的sql语义的数据源
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午11:29:58
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public JdbcTemplate getExcSqlJdbcTemplate(DBOperObj obj) throws Exception;
}
