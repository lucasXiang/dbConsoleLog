package com.tech.member.service;

import java.util.List;
import java.util.Map;

import com.tech.member.entity.SmsTempObj;

public interface SmsTempSer {
	/**
	 * 根据查询的参数获取短信模板管理的基本信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月31日  下午5:22:24
	 * @param smsTempObj
	 * @return
	 * @throws Exception
	 */
	public List<SmsTempObj> getSmsTempInfos(SmsTempObj paramObj) throws Exception;
	/**
	 * 根据查询参数 统计短信模板 的数据量
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  上午9:13:47
	 * @param paramObj
	 * @return
	 * @throws Exception
	 */
	public int countSmsTempInfos(SmsTempObj paramObj) throws Exception;
	/**
	 * 根据主键值id删除短信模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午1:05:21
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean delSmsTempInfo(int id) throws Exception;
	/**
	 * 插入短信模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午2:11:10
	 * @param paramObj
	 * @throws Exception
	 */
	public boolean insertSmsTemp(SmsTempObj paramObj) throws Exception;
	/**
	 * 根据短信模板id获取模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午2:36:59
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public SmsTempObj getSmsTempById(String templateId) throws Exception;
	/**
	 * 根据模板id更新模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午2:58:44
	 * @param paramObj
	 * @return
	 * @throws Exception
	 */
	public boolean modifyOneSmsTemp(SmsTempObj paramObj) throws Exception;
	/**
	 * 获取短信模板的所有模板类型
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午3:11:10
	 * @return
	 * @throws Exception
	 */
	public List<Map> getSendChannels() throws Exception;
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
