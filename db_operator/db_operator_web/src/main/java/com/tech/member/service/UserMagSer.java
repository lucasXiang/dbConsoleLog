package com.tech.member.service;

import java.util.List;

import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.RegistInfo;
import com.tech.member.entity.UserInfo;

public interface UserMagSer {
	/**
	 * 获取功能菜单列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月5日  下午5:13:06
	 * @return
	 * @throws Exception
	 */
	public List<ParentFuncInfo> getFunTreeParams() throws Exception;
	/**
	 * 判断用户名是否重复
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月6日  上午9:28:29
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean ifHasUserName(String userName) throws Exception;
	/**
	 * 插入用户信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月6日  上午9:44:30
	 * @param registInfo
	 * @throws Exception
	 */
	public boolean insertUserInfo(RegistInfo registInfo) throws Exception;
	/**
	 * 获取数据源信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月6日  下午1:30:00
	 * @throws Exception
	 */
	public List<ExcDataSourceObj> getDataSourceInfos() throws Exception;
	/**
	 * 检索用户列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月7日  上午10:13:40
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	public List<UserInfo> getUsersList(UserInfo userInfo) throws Exception;
	/**
	 * 统计用户列表数目
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月7日  上午10:14:23
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	public int countUsers(UserInfo userInfo) throws Exception;
	/**
	 * 根据用户主键值更新用户的删除标志
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月7日  上午11:01:09
	 * @param userId
	 * @throws Exception
	 */
	public boolean updateUserDelflagById(String userId) throws Exception;
}
