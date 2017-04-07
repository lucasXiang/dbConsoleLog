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
import com.tech.member.entity.SmsTempObj;
import com.tech.member.entity.UserObj;
import com.tech.member.service.SmsTempSer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/smsTempConsole")
public class SmsTempController {
	private Logger logger=Logger.getLogger(this.getClass());
	
	@Autowired
	private SmsTempSer smsTempSerImpl;
	
	@RequestMapping("/getSmsTempShowPage")
	public String getSmsTempShowPage(HttpServletRequest req,HttpServletResponse res){
		List<SmsTempObj> smsTempObjs=null;
		SmsTempObj paramObj=new SmsTempObj();
		//初始化 分页的 初始配置
		paramObj.initPage();
		try{
			smsTempObjs=smsTempSerImpl.getSmsTempInfos(paramObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("objs", smsTempObjs);
		//获取操作功能权限
		String smsRole=null;
		try{
			smsRole=smsTempSerImpl.getFuncMenusByUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("smsRole", smsRole);
		//获取模板类型
		List<Map> smsTypes=null;
		try{
			smsTypes=smsTempSerImpl.getSendChannels();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("smsTypes", JSONArray.fromObject(smsTypes));
		
		int count=0;
		try{
			count=smsTempSerImpl.countSmsTempInfos(paramObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		Map numMap = new HashMap();
		numMap.put("pageNum", paramObj.getPageNum());
		numMap.put("totalNum", count);
		numMap.put("pageSize", paramObj.getPageSize());
		req.setAttribute("numMap", numMap);
		
		return "query/smsTempList";
	}
	
	@RequestMapping("/refreshSmsTempShowPage")
	public void refreshSmsTempShowPage(@ModelAttribute SmsTempObj paramObj,HttpServletRequest req,HttpServletResponse res){
		//配置的起始位置
		paramObj.setStartRowByPageParam();
		Map numMap = new HashMap();
		List<SmsTempObj> smsTempObjs=null;
		try{
			smsTempObjs=smsTempSerImpl.getSmsTempInfos(paramObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		numMap.put("smsTempObjs", smsTempObjs);
		int count=0;
		try{
			count=smsTempSerImpl.countSmsTempInfos(paramObj);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		numMap.put("pageNum", paramObj.getPageNum());
		numMap.put("totalNum", count);
		numMap.put("pageSize", paramObj.getPageSize());
		//获取操作功能权限
		String smsRole=null;
		try{
			smsRole=smsTempSerImpl.getFuncMenusByUserId(((UserObj)req.getSession().getAttribute("user")).getOperid());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		numMap.put("smsRole", smsRole);
		try {
			res.getWriter().print(JSONObject.fromObject(numMap).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delOneSmsTempById")
	public void delOneSmsTempById(HttpServletRequest req,HttpServletResponse res){
		int id=Integer.parseInt(req.getParameter("id"));
		Map result = new HashMap();
		try{
			if(smsTempSerImpl.delSmsTempInfo(id)){
				result.put("code", PubConstant.RUN_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if(result.get("code")==null){
			result.put("code", PubConstant.RUN_FAILURE);
		}
		
		try {
			res.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/getOneSmsTemp")
	public String getOneSmsTemp(HttpServletRequest req,HttpServletResponse res){
		SmsTempObj obj=null;
		if(PubConstant.IF_MODIFY_YES.equals(req.getParameter("ifModify"))){
			try{
				obj=smsTempSerImpl.getSmsTempById(req.getParameter("templateId"));
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		req.setAttribute("obj", obj);
		
		//获取模板类型
		List<Map> smsTypes=null;
		try{
			smsTypes=smsTempSerImpl.getSendChannels();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("smsTypes", JSONArray.fromObject(smsTypes));
				
		req.setAttribute("ifModify", req.getParameter("ifModify"));
		
		
		return "query/oneSmsTempInfo";
	}
	@RequestMapping("/addOneSmsTemp")
	public void addOneSmsTemp(@ModelAttribute SmsTempObj paramObj, HttpServletRequest req,HttpServletResponse res){
		//获取操作人名称
		paramObj.setCreateUser(((UserObj)req.getSession().getAttribute("user")).getOpernum());
		Map result = new HashMap();
		try{
			if(smsTempSerImpl.insertSmsTemp(paramObj)){
				result.put("code", PubConstant.RUN_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if(result.get("code")==null){
			result.put("code", PubConstant.RUN_FAILURE);
		}
		
		try {
			res.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/modifyOneSmsTempById")
	public void modifyOneSmsTempById(@ModelAttribute SmsTempObj paramObj, HttpServletRequest req,HttpServletResponse res){
		//获取操作人
		paramObj.setCreateUser(((UserObj)req.getSession().getAttribute("user")).getOpernum());
		Map result = new HashMap();
		try{
			if(smsTempSerImpl.modifyOneSmsTemp(paramObj)){
				result.put("code", PubConstant.RUN_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if(result.get("code")==null){
			result.put("code", PubConstant.RUN_FAILURE);
		}
		try {
			res.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/showBodyInPage")
	public void showBodyInPage(HttpServletRequest req,HttpServletResponse res){
		try {
			res.getWriter().print(PubConstant.RUN_SUCCESS);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
