package com.tech.member.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tech.member.base.SmsTempMapper;
import com.tech.member.entity.SmsTempObj;

@Repository
public interface SmsTempDao extends SmsTempMapper{
	/**
	 * 根据查询参数获取 短信模板 数据
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月31日  下午4:33:25
	 * @param smsTempObj
	 * @return
	 * @throws Exception
	 */
	public List<SmsTempObj> getSmsTempInfos(SmsTempObj paramObj) throws Exception;
	/**
	 * 统计 短信模板 数据量
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  上午9:13:15
	 * @param paramObj
	 * @return
	 * @throws Exception
	 */
	public int countSmsTempInfos(SmsTempObj paramObj) throws Exception;
	/**
	 * 根据主键值id删除短信模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午1:04:42
	 * @param id
	 * @throws Exception
	 */
	public void delSmsTempInfo(int id) throws Exception;
	/**
	 * 插入短信模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午2:10:44
	 * @param paramObj
	 * @throws Exception
	 */
	public void insertSmsTemp(SmsTempObj paramObj) throws Exception;
	/**
	 * 根据短信模板id获取模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午2:36:31
	 * @param templateId
	 * @return
	 * @throws Exception
	 */
	public SmsTempObj getSmsTempById(String templateId) throws Exception;
	/**
	 * 根据模板id更新模板信息
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午2:58:10
	 * @param paramObj
	 * @throws Exception
	 */
	public void modifyOneSmsTemp(SmsTempObj paramObj) throws Exception;
	/**
	 * 获取短信模板的所有模板类型
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年4月1日  下午3:10:15
	 * @throws Exception
	 */
	public List<Map> getSendChannels() throws Exception;
	
}
