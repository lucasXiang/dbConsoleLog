package com.tech.member.service;

import java.util.List;

import com.tech.member.entity.DBOperObj;

public interface ExcSqlResDataSer {
	/**
	 * 获取 要执行的查询sql语义的结果数据
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午11:20:44
	 * @param excSql
	 * @return
	 * @throws Exception
	 */
	public List getExcSqlRes(String excSql,DBOperObj obj) throws Exception;
	/**
	 * 统计要执行的sql语义返回的数据数目
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午11:21:59
	 * @param excSql
	 * @return
	 * @throws Exception
	 */
	public int countExcSqlRes(String excSql,DBOperObj obj) throws Exception;
	/**
	 * 获取要执行的sql语句的运行结果的所有数据
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月23日  下午6:02:51
	 * @param excSql
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List getAllExcSqlRes(String excSql, DBOperObj obj)throws Exception;
}
