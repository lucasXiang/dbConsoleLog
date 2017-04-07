package com.tech.member.model;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tech.member.base.QueryConsoleMapper;
import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.UserObj;
import com.tech.member.entity.UserParam;

@Repository
public interface DBLoginDao extends QueryConsoleMapper{
	/**
	 * 根据用户名和密码获取用户对象
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月24日  下午1:17:51
	 * @param userMap
	 * @return
	 * @throws Exception
	 */
	public UserObj queryUserByUserMap(UserParam userMap)  throws Exception;
	/**
	 * 根据用户信息的主键值获取 用户的权限列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月24日  下午1:18:59
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ParentFuncInfo> getFunctionTreeByFuncIds(String funcIds) throws Exception;
	/**
	 * 根据用户id获取模板操作功能权限
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月6日  下午5:33:48
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getFuncMenusByUserId(String id) throws Exception ;
}
