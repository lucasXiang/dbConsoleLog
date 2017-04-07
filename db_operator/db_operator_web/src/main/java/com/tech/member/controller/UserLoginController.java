package com.tech.member.controller;

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

import com.tech.member.entity.ParentFuncInfo;
import com.tech.member.entity.UserObj;
import com.tech.member.entity.UserParam;
import com.tech.member.service.DBLoginSer;
import com.tech.member.util.Md5Encrypt;
/**
 * 用户登陆控制层
 * @author zhongqiang.xiang 
 * @version 创建时间：2017年3月20日  下午2:13:04
 */
@Controller
public class UserLoginController {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DBLoginSer dbLoginSerImpl;
	
	@RequestMapping("/DBConsole/getLoginPage")
	public String getLoginPage() {
		return "login";
	}
	@RequestMapping("/DBConsole/login")
	public String login(@ModelAttribute UserObj obj,HttpServletRequest req,HttpServletResponse res){
		if(obj.getCode()==null||obj.getOpernum()==null||obj.getOperpwd()==null){
			req.setAttribute("msg", "请输入登录信息");
			return "login";
		}
		if(obj.getCode().equals(req.getSession().getAttribute("code"))){
			try{
				UserParam userParam=new UserParam();
				userParam.setUserId(obj.getOpernum().trim());
				userParam.setPassWord(Md5Encrypt.md5(obj.getOperpwd().trim()).trim());
				UserObj users = dbLoginSerImpl.queryUserByUserMap(userParam);
				if(users != null){
					req.getSession().setAttribute("user", users);
					return "redirect:/DBConsole/getHomePage.htm";
				}else{
					req.setAttribute("msg", "用户名或密码错误");
				}
			}catch(Exception e){
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}else{
			req.setAttribute("msg", "验证码错误");
		}
		
		return "login";
	}
	@RequestMapping("/DBConsole/getHomePage")
	public String getHomePage(HttpServletRequest req,HttpServletResponse res){
		List<ParentFuncInfo> funcs=null;
		try {
			funcs=dbLoginSerImpl.getFunctionTreeByFuncIds(((UserObj)req.getSession().getAttribute("user")).getFuncIds());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		req.setAttribute("funcs", funcs);
		return "home";
	}
	
	@RequestMapping("/DBConsole/logout")
	public String loginOut(HttpServletRequest req,HttpServletResponse res){
		//注销session存留的用户信息
		req.getSession().removeAttribute("user");
		
		return "login";
	}
	
}
