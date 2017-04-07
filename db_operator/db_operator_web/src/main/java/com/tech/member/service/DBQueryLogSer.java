package com.tech.member.service;

import java.util.List;
import java.util.Properties;

import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.UserObj;

public interface DBQueryLogSer {
	/**
	 * 插入要查询的sql语义
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  上午11:12:54
	 * @param obj
	 * @return
	 */
	public boolean insertDBQuerySql(DBOperObj obj) throws Exception;
	/**
	 * 查询要执行的sql语义列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午2:30:34
	 * @param obj
	 * @return
	 */
	public List<DBOperObj> queryQuerySql(DBOperObj obj) throws Exception;
	/**
	 * 统计要查询的sql语义的总数目
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午3:49:43
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public int countQuerySql(DBOperObj obj) throws Exception;
	/**
	 * 根据数据的主键值获取数据信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月20日  上午10:29:08
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DBOperObj queryQuerySqlById(String querySqlId) throws Exception;
	/**
	 * 获取要连接的数据库信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月22日  下午4:03:19
	 * @return
	 * @throws Exception
	 */
	public List<ExcDataSourceObj> getDataSourceInfos() throws Exception;
	/**
	 * 根据数据源信息ID获取完整的数据源连接信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月22日  下午5:11:13
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ExcDataSourceObj getDataSourceById(String id) throws Exception;
	/**
	 * 根据数据源主键值获取要审核的审核人信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月27日  下午1:49:09
	 * @param dbSourId
	 * @return
	 * @throws Exception
	 */
	public List<UserObj> getToAuditorByDBSourId(String dbSourId) throws Exception;
	/**
	 * 读取配置文件里的配置信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月28日  下午4:59:06
	 * @return
	 * @throws Exception
	 */
	public Properties getPropConf() throws Exception;
}
