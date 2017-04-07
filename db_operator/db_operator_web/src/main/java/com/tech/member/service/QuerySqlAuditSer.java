package com.tech.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.QuerySqlAuditObj;

public interface QuerySqlAuditSer {
	/**
	 * 获取 待审核的sql语义 列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午9:59:50
	 * @param auditObj
	 * @return
	 * @throws Exception
	 */
	public List<DBOperObj> toAuditSqlInfoList(QuerySqlAuditObj auditObj) throws Exception;
	/**
	 * 获取 待审核的sql语义的 统计数目
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  上午10:28:33
	 * @param auditObj
	 * @return
	 * @throws Exception
	 */
	public int countToAuditSqlList(QuerySqlAuditObj auditObj) throws Exception;
	/**
	 * 更新 待审核的sql语义的审核信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月21日  下午3:59:32
	 * @param auditObj
	 * @return
	 * @throws Exception
	 */
	public boolean updateOneSqlAuditStatus(QuerySqlAuditObj auditObj) throws Exception;
}
