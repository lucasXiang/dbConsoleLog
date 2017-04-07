<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/conf/tld/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="author" content="ray01@sogou.com,Ray" />
<meta name="Copyright" content="www.unspay.com,上海银生宝电子支付服务有限公司版权所有" />
<meta name="description" content="数据操作控制平台系统" />
<meta content="数据操作控制平台系统" name="keywords" />
<title>数据操作控制平台系统</title>
<link href="<%=request.getContextPath()%>/style/login.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	background-image: url(<%=request.getContextPath()%>/images/login_bg.gif);
}
-->
</style>
<SCRIPT language=javascript>
	function checkvalue() {

		if (document.getElementById("opernum").value == "") {
			document.getElementById("opernum").focus();
			window.alert("请填写操作员号码！");
			return false;
		}

		var operpwd = document.getElementById("operpwd");
		if (operpwd.value == "") {
			operpwd.focus();
			window.alert("请填写操作员密码！");
			return false;
		}

		if (document.getElementById("code").value == "") {
			document.getElementById("code").focus();
			window.alert("请填写验证码！");
			return false;
		}
		return true;
	}
	function refreshCode(){
		var codeImg = document.getElementById("codeImg");
	    var d = new Date();
	    codeImg.src = "<%=request.getContextPath()%>/AuthCode/code.htm?s="+d;
	}
</SCRIPT>
</head>
<body>
	<div id="content">
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/logo_login.gif" alt="logo" />
		</div>
		<div id="title">

			<div id="title_left">DBMS 系统管理登录</div>
			<br />
			<div style="font: 0px/0px sans-serif; clear: both; display: block">
			</div>

		</div>
		<div id="login_left">
			<form action="<%=request.getContextPath()%>/DBConsole/login.htm" onsubmit="return checkvalue()"
				method="post">
				<div id="login_left_left"></div>
				<div id="login_left_middle">
					<strong>管理员号码</strong>： <input name="opernum" type="text"
						class="form" maxlength="15" id="opernum" /> <span
						class="text_width"><br /> 管理员密码</span>： <input name="operpwd"
						type="password" class="form" maxlength="20" id="operpwd" /> <br />
					<span class="text_width">验证码</span>： <input name="code" type="text"
						class="form" maxlength="4" id="code" /> <br /> <br /> <img id="codeImg"
						src="<%=request.getContextPath()%>/AuthCode/code.htm" onclick="refreshCode()">
				</div>
				<div id="login_left_right">
					<input name="imageField" type="image" src="<%=request.getContextPath()%>/images/login.gif"
						width="65px" height="45px" border="0px" />
				</div>
				<c:if test="${msg != ''}">
					<div>
						<span style="color:red;">${msg }</span>
					</div>
				</c:if>
			</form>
		</div>
		<br />
		<div id="footer">
			<a href="https://www.unspay.com/" target="_blank">上海银生宝电子支付服务有限公司</a>
		</div>

	</div>

</body>
</html>
