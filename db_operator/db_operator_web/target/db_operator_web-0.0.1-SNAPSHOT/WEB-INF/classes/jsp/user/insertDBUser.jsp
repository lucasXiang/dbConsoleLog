<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/conf/tld/c.tld" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
%>
<html>
<head>
<link href="<%=path%>/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/jscripts/common.js"></script>
<script src="<%=path%>/jscripts/jquery/jquery.js" type="text/javascript"></script>
<script src="<%=path%>/jscripts/xmlformat.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=path%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增用户</title>
<script type="text/javascript">
	function addOneUser(){
		if($("#userName").val().trim()==''){
			alert("请输入用户名！");
			return;
		}
		if($("#password").val().trim()==''){
			alert("请输入密码！");
			return;
		}
		if($("#password").val().trim()!=$("#password2").val().trim()){
			alert("两次输入的密码不一致，请修改！");
			return;
		}
		if(confirm("确定要提交用户信息吗？")){
			var funParams=document.getElementsByName("funParam");
			var funParamsArr=[];
			for(i in funParams){
				if(funParams[i].checked){
					funParamsArr.push(funParams[i].value);
				}
			}
			$.ajax({
				type:'POST',
				url:"<%=path%>/userManager/insertUserInfo.htm",
				data:{
					userName:$("#userName").val().trim(),
					name:$("#name").val().trim(),
					password:$("#password").val().trim(),
					sex:$("#sex").val(),
					funParamsStr:funParamsArr.join(',')
				},
				dataType:'json',
				success:function(res){
					alert(res.msg);
					reset();
				},
				error:function(){
					alert("系统繁忙，请稍后再试！");
				}
			});
			
		}
	}
	function reset(){
		$("#userName").val('');
		$("#name").val('');
		$("#password").val('');
		$("#password2").val('');
		$("#sex").val('');
		var funParams=document.getElementsByName("funParam");
		for(i in funParams){
			if(funParams[i].checked){
				funParams[i].checked=false;
			}
		}
	}
</script>
</head>
<body>
	<form id="showOneSqlInfo" name="showOneSqlInfo">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%">
						<span>当前的位置:新增用户</span>
					</td>
				</tr>
			</table>
		</div>
		<div class="con_search02">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" class="con_search_right20"><span
						class="con_search_right">
						<input name="button232" type="button" class="buttonStyle-2word" onclick="addOneUser();" value="新增" />
						<input name="button232" type="button" class="buttonStyle-2word" onclick="reset();" value="重置" />
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search" id="excDiv">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td colspan="4">
						用户注册信息
						<br/><br/>
					</td>
				</tr>
				<tr>
					<td class="con_search_right">用户名：</td>
					<td><input class="inputStyle" id="userName"/> </td>
					<td class="con_search_right">用户名称：</td>
					<td><input type="text" id="name" class="inputStyle" value="" size="50px;"/></td>
				</tr>
				<tr>
					<td class="con_search_right">密码：</td>
					<td><input type="password" id="password" class="inputStyle" value="" size="50px;"/></td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="con_search_right">密码确认：</td>
					<td><input type="password" id="password2" class="inputStyle" value="" size="50px;"/></td>
					<td></td><td></td>
				</tr>
				<tr>
					<td class="con_search_right">性别：</td>
					<td>
						<select class="inputStyle" id="sex">
							<option value="">==请选择==</option>
							<option value="0">男</option>
							<option value="1">女</option>
						</select>
					</td>
					<td></td><td></td>
				</tr>
				<tr>
					<td colspan="4">
						权限设置
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<c:forEach var="par" items="${parFuns}" >
						<br>
							&nbsp;&nbsp;&nbsp;&nbsp;${par.pfuncValue }
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<c:forEach var="subFun" items="${par.chFuncObjs }" varStatus="s">
								<label><input type="checkbox" name="funParam" value="${subFun.id }">${subFun.funcValue }</label>
								<c:if test="${(s.index+1)%5 == 0 }"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
							</c:forEach>
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>