package com.tech.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.member.constant.PubConstant;
import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.QuerySqlAuditObj;
import com.tech.member.entity.UserObj;
import com.tech.member.service.DBQueryLogSer;
import com.tech.member.service.QuerySqlAuditSer;

import net.sf.json.JSONObject;
@RequestMapping("/auditConsole")
@Controller
public class QuerySqlAuditController {
	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private QuerySqlAuditSer querySqlAuditSerImpl;
	@Autowired
	private DBQueryLogSer dbQueryLogSerImpl;
	/**
	 * 审核查询sql语义列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月24日  上午10:57:51
	 * @param auditObj
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/getToAuditQuerySqlList")
	public String getPageToAuditList(@ModelAttribute QuerySqlAuditObj auditObj,HttpServletRequest req,HttpServletResponse res){
		List<DBOperObj> objs=null;
		//传递当前审核人的信息至参数中
		auditObj.setCurUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		//给参数赋值初始状态：已提交
		auditObj.setAuditStatus(PubConstant.AUDIT_STATUS_INIT);
		try{
			objs=querySqlAuditSerImpl.toAuditSqlInfoList(auditObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("objs", objs);
		
		//获取数据源的选择范围
		List<ExcDataSourceObj> dataSourceObjs=null;
		try{
			dataSourceObjs=dbQueryLogSerImpl.getDataSourceInfos();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("dataSourceObjs", dataSourceObjs);
		
		// 获取sql语义的统计数目
		Map numMap = new HashMap();
		Integer n = 0;
		try {
			n = querySqlAuditSerImpl.countToAuditSqlList(auditObj);
			numMap.put("totalNum", n);

			numMap.put("pageNum", auditObj.getPageNum());
			numMap.put("pageSize", auditObj.getPageSize());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("numMap", numMap);
		
		return "query/querySqlToAuditList";
	}
	@RequestMapping("/refreshToAuditSqls")
	public void refreshToAuditSqls(@ModelAttribute QuerySqlAuditObj auditObj, HttpServletRequest req, HttpServletResponse res) {
		// 获取sql语义的列表
		List<DBOperObj> objsRes=null;
		//获取当前用户id属性值
		auditObj.setCurUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		try{
			objsRes=querySqlAuditSerImpl.toAuditSqlInfoList(auditObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		// 获取sql语义的统计数目
		Map numMap = new HashMap();
		numMap.put("objsRes", objsRes);
		int n = 0;
		try {
			n = querySqlAuditSerImpl.countToAuditSqlList(auditObj);
			numMap.put("totalNum", n);
			numMap.put("pageNum", auditObj.getPageNum());
			numMap.put("pageSize", auditObj.getPageSize());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		JSONObject jsonObj=JSONObject.fromObject(numMap);
		
		// 记录反馈的结果信息
		logger.info(jsonObj.toString());
		try {
			res.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/auditOneSqlInfo")
	public void auditOneSqlInfo(@ModelAttribute QuerySqlAuditObj obj,HttpServletRequest req,HttpServletResponse res){
		boolean ifSuccess=false;
		try{
			obj.setAuditorId(((UserObj)req.getSession().getAttribute("user")).getOperid());
			ifSuccess=querySqlAuditSerImpl.updateOneSqlAuditStatus(obj);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		try {
			if(ifSuccess){
				res.getWriter().write(PubConstant.RUN_SUCCESS);
			}else{
				res.getWriter().write(PubConstant.RUN_FAILURE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
