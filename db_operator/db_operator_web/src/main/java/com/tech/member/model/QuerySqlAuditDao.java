package com.tech.member.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tech.member.base.QueryConsoleMapper;
import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.QuerySqlAuditObj;

@Repository
public interface QuerySqlAuditDao extends QueryConsoleMapper{
	/**
	 * 根据检索条件搜索需要审核的查询sql语义
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午9:19:40
	 * @param auditObj
	 * @return
	 * @throws Exception
	 */
	public List<DBOperObj> toAuditSqlInfoList(QuerySqlAuditObj auditObj) throws Exception;
	/**
	 * 统计 要审核的 sql语义的数目
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午10:27:44
	 * @param auditObj
	 * @return
	 * @throws Exception
	 */
	public int countToAuditSqlList(QuerySqlAuditObj auditObj) throws Exception;
	/**
	 * 更新提交sql语义信息的审核信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  下午3:57:27
	 * @param auditObj
	 * @return
	 * @throws Exception
	 */
	public void updateOneSqlAuditStatus(QuerySqlAuditObj auditObj) throws Exception;
}
