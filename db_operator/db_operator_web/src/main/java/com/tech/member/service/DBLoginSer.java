package com.tech.member.service;

import java.util.List;

import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.UserObj;
import com.tech.member.entity.UserParam;

public interface DBLoginSer {
	/**
	 * 根据用户名查找用户信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月20日  下午2:51:41
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserObj queryUserByUserMap(UserParam userMap) throws Exception;
	/**
	 * 根据用户信息的主键值 获取该用户的功能权限列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月24日  下午1:21:34
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<ParentFuncInfo> getFunctionTreeByFuncIds(String funcIds) throws Exception; 
}
