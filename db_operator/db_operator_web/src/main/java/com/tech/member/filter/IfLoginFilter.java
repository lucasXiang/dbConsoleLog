package com.tech.member.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IfLoginFilter extends HttpServlet implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest res = (HttpServletRequest)arg0;
		if(res.getRequestURI().equals("/db_operator_web/DBConsole/getLoginPage.htm")||res.getRequestURI().equals("/db_operator_web/DBConsole/login.htm")
				||res.getRequestURI().equals("/db_operator_web/AuthCode/code.htm")){
			arg2.doFilter(arg0, arg1);
		}else{
			if(res.getSession().getAttribute("user")!=null){
				arg2.doFilter(arg0, arg1);
			}else{
				((HttpServletResponse)arg1).sendRedirect(res.getContextPath() + "/DBConsole/getLoginPage.htm");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
