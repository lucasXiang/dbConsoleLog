package com.tech.member.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tech.member.constant.PubConstant;
import com.tech.member.entity.DBOperObj;
import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.QuerySqlAuditObj;
import com.tech.member.entity.UserObj;
import com.tech.member.service.DBOperatorCheckSer;
import com.tech.member.service.DBQueryLogSer;
import com.tech.member.service.ExcSqlResDataSer;
import com.tech.member.service.QuerySqlAuditSer;
import com.tech.member.util.DataReadedInExcel;
import com.tech.member.util.JSonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 数据操作sql语句控制类
 * 
 * @author zhongqiang.xiang
 * @version 创建时间：2017年3月16日 上午10:20:06
 */
@RequestMapping("/DBConsole")
@Controller
public class DBOperatorController {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private DBQueryLogSer dbQueryLogSerImpl;
	@Autowired
	private DBOperatorCheckSer dbOperatorCheckSerImpl;
	@Autowired
	private ExcSqlResDataSer excSqlResDataSerImpl;
	@Autowired
	private QuerySqlAuditSer querySqlAuditSer;

	
	
	/**
	 * 获取提交查询sql语义的平台页面
	 * 
	 * @return
	 */
	@RequestMapping("/getConsolePage")
	public String getConsolePage(HttpServletRequest req,HttpServletResponse res) {
		try{
			//数据源信息
			List<ExcDataSourceObj> objs=dbQueryLogSerImpl.getDataSourceInfos();
			req.setAttribute("dataSourceObjs", objs);
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "query/dbQuerySqlSubmit";
	}
	@RequestMapping("/getToAuditors")
	public void getToAuditors(HttpServletRequest req,HttpServletResponse res){
		String dbSourId=req.getParameter("dbSourId");
		List<UserObj> toAuditors=null;
		try{
			toAuditors=dbQueryLogSerImpl.getToAuditorByDBSourId(dbSourId);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		try {
			res.getWriter().write(JSONArray.fromObject(toAuditors).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/submitDBQuerySQL")
	public void auditDBSQL(@ModelAttribute DBOperObj obj, HttpServletRequest req, HttpServletResponse rep) {
		rep.setContentType("text/html;charset=UTF-8");
		Map<String, String> rtResult = new HashMap<String, String>();
		//获取当前操作员的用户主键值
		obj.setCurUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		
		boolean flag = false;
		boolean checkDataSource = false;
		boolean checkQuerySql = false;
		try {
			//获取数据源信息
			obj.setExcDataSourceObj(dbQueryLogSerImpl.getDataSourceById(obj.getExcDbId()));
			// 校验数据库数据源
			checkDataSource = dbOperatorCheckSerImpl.checkDataSourceInfo(obj);
			// 校验数据的查询sql语义
			checkQuerySql = dbOperatorCheckSerImpl.checkQuerySql(obj.getExcSql(),obj);
			if (checkDataSource && checkQuerySql) {
				// 将sql语义存入数据库中
				if (dbQueryLogSerImpl.insertDBQuerySql(obj)) {
					rtResult.put("code", PubConstant.RUN_SUCCESS);
					rtResult.put("msg", "您提交的sql查询语义已保存成功！");
					flag = true;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		if (!flag) {
			if (!checkDataSource) {
				rtResult.put("code", PubConstant.RUN_FAILURE);
				rtResult.put("msg", "您提交的数据源配置信息有误，请修改后再提交....");
			} else if (!checkQuerySql) {
				rtResult.put("code", PubConstant.RUN_FAILURE);
				rtResult.put("msg", "您提交的sql查询语义有误，请修改后再提交....");
			}else{
				rtResult.put("code", PubConstant.RUN_FAILURE);
				rtResult.put("msg", "sql语义存入数据库失败！");
			}
		}
		// 记录反馈的结果信息
		logger.info(rtResult);
		try {
			rep.getWriter().write(JSonUtil.toJson(PubConstant.IS_TRUE, rtResult));
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 获取查询sql语义的列表
	 * 
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日 下午2:26:41
	 * @param obj
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping("/getDBQuerySqls")
	public String getDBQuerySqls(@ModelAttribute DBOperObj obj, HttpServletRequest req, HttpServletResponse res) {
		// 获取sql语义的列表
		List<DBOperObj> objsRes = null;
		//获取当前用户id属性值
		obj.setCurUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		//默认查询 状态：已通过 数据
		obj.setAuditStatus(PubConstant.AUDIT_PAGE_YES);
		try {
			objsRes = dbQueryLogSerImpl.queryQuerySql(obj);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("objs", objsRes);
		
		// 获取sql语义的统计数目
		Map numMap = new HashMap();
		int n = 0;
		try {
			n = dbQueryLogSerImpl.countQuerySql(obj);
			//获取分页设置的必要条件
			numMap.put("totalNum", n);
			numMap.put("pageNum", obj.getPageNum());
			numMap.put("pageSize", obj.getPageSize());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("numMap", numMap);
		
		//获取数据源的选择范围
		List<ExcDataSourceObj> dataSourceObjs=null;
		try{
			dataSourceObjs=dbQueryLogSerImpl.getDataSourceInfos();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("dataSourceObjs", dataSourceObjs);

		return "query/dbQuerySqlList";
	}
	/**
	 * ajax局部刷新数据列表
	 * @author zhongqiang.xiang
	 * @version 创建时间：2017年3月17日  下午4:54:47
	 * @param obj
	 * @param req
	 * @param res
	 */
	@RequestMapping("/refreshQuerySqls")
	public void refreshQuerySqls(@ModelAttribute DBOperObj obj, HttpServletRequest req, HttpServletResponse res) {
		// 获取sql语义的列表
		List<DBOperObj> objsRes = null;
		//获取当前用户id属性值
		obj.setCurUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		try {
			objsRes = dbQueryLogSerImpl.queryQuerySql(obj);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		Map numMap = new HashMap();
		numMap.put("objsRes", objsRes);
		
		// 获取sql语义的统计数目
		int n = 0;
		try {
			n = dbQueryLogSerImpl.countQuerySql(obj);
			numMap.put("totalNum", n);
			numMap.put("pageNum", obj.getPageNum());
			numMap.put("pageSize", obj.getPageSize());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		JSONObject jsonObj=JSONObject.fromObject(numMap);
		
		// 记录反馈的结果信息
		logger.info("refreshQuerySqls()"+jsonObj.toString());
		try {
			res.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/getOneSqlInfo")
	public String getOneSqlInfo(HttpServletRequest req, HttpServletResponse res){
		//获取sql语义的详细信息
		String querySqlId=req.getParameter("querySqlId");
		DBOperObj obj=null;
		try{
			obj=dbQueryLogSerImpl.queryQuerySqlById(querySqlId);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("obj", obj);
		
		if(req.getParameter("ifAuditPage").equals(PubConstant.AUDIT_PAGE_NO)){
			req.setAttribute("ifAuditPage", PubConstant.AUDIT_PAGE_NO);
		}else if(req.getParameter("ifAuditPage").equals(PubConstant.AUDIT_PAGE_YES)){
			req.setAttribute("ifAuditPage", PubConstant.AUDIT_PAGE_YES);
		}
		return "query/dbQuerySqlInfo";
	}
	@RequestMapping("/refreshExcSqlRes")
	public void refreshExcSqlRes(HttpServletRequest req, HttpServletResponse res){
		//获取sql语义的详细信息
		String querySqlId=req.getParameter("excSqlId");
		DBOperObj obj=null;
		try{
			obj=dbQueryLogSerImpl.queryQuerySqlById(querySqlId);
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		//设置分页查询
		obj.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
		obj.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
		
		String resultCode=PubConstant.RUN_FAILURE;
		List list=null;
		int n=0;
		try{
			list=excSqlResDataSerImpl.getExcSqlRes(obj.getExcSql(), obj);
			n=excSqlResDataSerImpl.countExcSqlRes(obj.getExcSql(), obj);
			
			resultCode=PubConstant.RUN_SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		Map result=new HashMap();
		if(resultCode.equals(PubConstant.RUN_SUCCESS)){
			result.put("excSqlRes", list);
			result.put("countExcSqlRes", n);
			
			//更改该sql语义数据的执行状态 （改为 已执行 ：3）
			QuerySqlAuditObj auditObj=new QuerySqlAuditObj();
			auditObj.setId(querySqlId);
			auditObj.setAuditStatus(PubConstant.AUDIT_STATUS_EXCUTED);
			try {
				querySqlAuditSer.updateOneSqlAuditStatus(auditObj);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
		result.put("code", resultCode);
		logger.info("refreshExcSqlRes()"+JSONObject.fromObject(result).toString());
		try {
			res.getWriter().write(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/downData2Excel")
	public void downData2Excel(HttpServletRequest req, HttpServletResponse res){
		String fileName=req.getParameter("fileName");
		res.setContentType("application/x-download");
		res.setHeader(
		        "Content-disposition",
		        "attachment;filename="
		                + fileName);
		//获取sql语义的详细信息
		String querySqlId=req.getParameter("excSqlId");
		DBOperObj obj=null;
		try{
			obj=dbQueryLogSerImpl.queryQuerySqlById(querySqlId);
			//设置该查询结果的输出量
			obj.setPageSize(Integer.valueOf(dbQueryLogSerImpl.getPropConf().getProperty(PubConstant.SQL_EXCUTED_MAX_SIZE)));
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		List list=null;
		try{
			list=excSqlResDataSerImpl.getAllExcSqlRes(obj.getExcSql(), obj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		OutputStream outs = null;
		try {
			outs=res.getOutputStream();
			HSSFWorkbook excel=DataReadedInExcel.writeList2Excel(list);
			
			excel.write(outs);
			
			//更改该sql语义数据的执行状态 （改为 已执行 ：3）
			QuerySqlAuditObj auditObj=new QuerySqlAuditObj();
			auditObj.setId(querySqlId);
			auditObj.setAuditStatus(PubConstant.AUDIT_STATUS_EXCUTED);
			try {
				querySqlAuditSer.updateOneSqlAuditStatus(auditObj);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(outs!=null)
					outs.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}
