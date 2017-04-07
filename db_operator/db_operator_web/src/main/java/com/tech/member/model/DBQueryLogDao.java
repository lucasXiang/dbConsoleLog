package com.tech.member.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tech.member.base.QueryConsoleMapper;
import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.UserObj;

@Repository
public interface DBQueryLogDao extends QueryConsoleMapper{
	/**
	 * 获取数据源的审核人
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月6日  下午1:59:37
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<UserObj> getDBSourAuditor(String id) throws Exception;
	
	/**
	 * 将要查询的sql语义存入数据库中
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午1:18:38
	 * @param obj
	 */
	public void insertDBQuerySql(DBOperObj obj) throws Exception;
	/**
	 * 查询当前用户所提交的查询sql语义
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午3:02:01
	 * @return
	 * @throws Exception
	 */
	public List<DBOperObj> queryQuerySql(DBOperObj obj) throws Exception;
	/**
	 * 统计要查询的sql语义的总数目
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午3:48:12
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int countQuerySql(DBOperObj obj) throws Exception;
	/**
	 * 根据数据的主键值查找 sql语义的相关信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月20日  上午11:00:59
	 * @param querySqlId
	 * @return
	 * @throws Exception
	 */
	public DBOperObj queryQuerySqlById(String querySqlId) throws Exception;
	/**
	 * 根据数据源信息ID获取数据源完整信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月22日  下午5:09:51
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExcDataSourceObj getDataSourceById(String id) throws Exception;
}
