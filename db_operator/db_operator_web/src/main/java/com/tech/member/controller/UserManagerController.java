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
import com.tech.member.entity.ExcDataSourceObj;
import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.RegistInfo;
import com.tech.member.entity.UserInfo;
import com.tech.member.service.UserMagSer;

import net.sf.json.JSONObject;

@RequestMapping("/userManager")
@Controller
public class UserManagerController {
	private Logger logger=Logger.getLogger(this.getClass());
	@Autowired
	private UserMagSer userMagSerImpl;
	
	@RequestMapping("/getUserInsertedPage")
	public String getUserInsertedPage(HttpServletRequest req,HttpServletResponse res){
		//获取功能权限列表
		List<ParentFuncInfo> parFuns=null;
		try{
			parFuns=userMagSerImpl.getFunTreeParams();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		//获取数据源
		List<ExcDataSourceObj> dbSours=null;
		try{
			dbSours=userMagSerImpl.getDataSourceInfos();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("parFuns", parFuns);
		req.setAttribute("dbSours", dbSours);
		return "user/insertDBUser";
	}
	
	@RequestMapping("/insertUserInfo")
	public void insertUserInfo(@ModelAttribute RegistInfo registInfo, HttpServletRequest req,HttpServletResponse res){
		Map result=new HashMap();
		//校验用户名是否重名
		try{
			if(userMagSerImpl.ifHasUserName(registInfo.getUserName())){
				result.put("code", PubConstant.RUN_BUSINESS_ERROR);
				result.put("msg", "该用户名已被注册，请重新输入！");
				res.getWriter().print(JSONObject.fromObject(result).toString());
				
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		//插入用户信息
		try{
			if(userMagSerImpl.insertUserInfo(registInfo)){
				result.put("code", PubConstant.RUN_SUCCESS);
				result.put("msg", "成功插入用户信息！");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if(result.isEmpty()){
			result.put("code", PubConstant.RUN_FAILURE);
			result.put("msg", "插入用户信息失败！");
		}
		try {
			res.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	@RequestMapping("/getUsersList")
	public String getUsersList(@ModelAttribute UserInfo userInfo,HttpServletRequest req,HttpServletResponse res){
		List<UserInfo> users=null;
		try{
			users=userMagSerImpl.getUsersList(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		req.setAttribute("users", users);
		// 获取用户列表的统计数目
		Map numMap = new HashMap();
		int cn=0;
		try{
			cn=userMagSerImpl.countUsers(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		numMap.put("totalNum", cn);
		numMap.put("pageNum", userInfo.getPageNum());
		numMap.put("pageSize", userInfo.getPageSize());
		req.setAttribute("numMap", numMap);
		return "user/dbUserList";
	}
	@RequestMapping("/refreshUsersList")
	public void refreshUsersList(@ModelAttribute UserInfo userInfo,HttpServletRequest req,HttpServletResponse res){
		Map result = new HashMap();
		List<UserInfo> users=null;
		try{
			users=userMagSerImpl.getUsersList(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		result.put("users", users);
		// 获取用户列表的统计数目
		int cn=0;
		try{
			cn=userMagSerImpl.countUsers(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		result.put("totalNum", cn);
		result.put("pageNum", userInfo.getPageNum());
		result.put("pageSize", userInfo.getPageSize());
		
		try {
			res.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUserDelFlag")
	public void updateUserDelFlag(HttpServletRequest req,HttpServletResponse res){
		Map result=new HashMap();
		String userId=req.getParameter("userId");
		try{
			if(userMagSerImpl.updateUserDelflagById(userId)){
				result.put("code", PubConstant.RUN_SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		if(result.isEmpty()){
			result.put("code", PubConstant.RUN_FAILURE);
		}
		try {
			res.getWriter().print(JSONObject.fromObject(result).toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
