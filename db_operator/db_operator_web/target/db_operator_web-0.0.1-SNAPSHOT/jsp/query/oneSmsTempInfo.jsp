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
<title>短信模板</title>
<script type="text/javascript">
	function addOneSmsTemp(){
		if($("#head").val().trim()==''){
			alert("模板标题不能为空 ！");
			return;
		}
		if($("#sendChannel").val().trim()==''){
			alert("通道类型不能为空！");
			return;
		}
		if($("#body").val().trim()==''){
			alert("模板内容不能为空 ！");
			return;
		}
		$.ajax({
			type:'post',
			url: "<%=path%>/smsTempConsole/addOneSmsTemp.htm",
			data:{
				head:$("#head").val().trim(),
				sendChannel:$("#sendChannel").val().trim(),
				body:$("#body").val().trim(),
				remark:$("#remark").val()
			},
			dataType:'json',
			success:function(res){
				if(res.code=='0'){
					alert("成功插入模板信息！");
					window.opener.querySmsTemp();
					window.close();
				}else{
					alert("插入失败，请重新操作！");
				}
			},
			error:function(){
				alert("系统繁忙，请稍后再试...");
			}
		});
	}
	function modifyOneSmsTemp(){
		if($("#head").val().trim()==''){
			alert("模板标题不能为空 ！");
			return;
		}
		if($("#sendChannel").val().trim()==''){
			alert("通道类型不能为空！");
			return;
		}
		if($("#body").val().trim()==''){
			alert("模板内容不能为空 ！");
			return;
		}
		var templateId='${obj.templateId }';
		$.ajax({
			type:'post',
			url: "<%=path%>/smsTempConsole/modifyOneSmsTempById.htm",
			data:{
				templateId:templateId,
				head:$("#head").val().trim(),
				sendChannel:$("#sendChannel").val().trim(),
				body:$("#body").val().trim(),
				remark:$("#remark").val()
			},
			dataType:'json',
			success:function(res){
				if(res.code=='0'){
					alert("成功修改模板信息！");
					window.opener.querySmsTemp();
					window.close();
				}else{
					alert("修改失败，请重新操作！");
				}
			},
			error:function(){
				alert("系统繁忙，请稍后再试..");
			}
		});
	}
	function showInPage(){
		$.ajax({
			type:'post',
			url:"<%=path%>/smsTempConsole/showBodyInPage.htm",
			data:{},
			dataType:'json',
			success:function(res){
				if(res=='0'){
					var obj = window.open("about:blank");   
	                obj.document.write($("#body").val()); 
				}else{
					alert("操作失败！");
				}
			},
			error:function(){
				alert("系统繁忙，请稍后再试..");
			}
		});
	}
	function getSendChannel(){
		var sendChannels=JSON.parse('${smsTypes }');
		var type=$("#type").val();
		$("#sendChannel option:gt(0)").remove();
		if(type==''){
			return;
		}
		for(i in sendChannels){
			if((sendChannels[i].SENDCHANNEL).indexOf(type)!=-1){
				$("#sendChannel").append('<option value="'+sendChannels[i].SENDCHANNEL+'" >'+sendChannels[i].SENDCHANNEL+'</option>');
			}
		}
	}
	function init(){
		if('${obj.sendChannel}'.startsWith('C')){
			$("#type").val('C');
			getSendChannel();
		}else if('${obj.sendChannel}'.startsWith('H')){
			$("#type").val('H');
			getSendChannel();
		}else if('${obj.sendChannel}'.startsWith('M')){
			$("#type").val('M');
			getSendChannel();
		}
		$("#sendChannel").val('${obj.sendChannel}');
	}
</script>
</head>
<body onload="init();">
	<form id="showOneSqlInfo" name="showOneSqlInfo">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%">
						<c:if test="${ifModify == '1' }"><span>当前的位置:新增短息模板</span></c:if>
						<c:if test="${ifModify == '0' }"><span>当前的位置:短信模板修改</span></c:if>
					</td>
				</tr>
			</table>
		</div>
		<div class="con_search02">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" class="con_search_right20"><span
						class="con_search_right">
						<c:if test="${ifModify == '1' }">
							<input name="button232" style="text-align:center; float:left;" type="button" class="buttonStyle-10word" onclick="showInPage();" value="模板内容页面效果" />
							<input name="button232" type="button" class="buttonStyle-2word" onclick="addOneSmsTemp();" value="新增" />
						</c:if>
						<c:if test="${ifModify == '0' }">
							<input name="button232" style="text-align:center; float:left;" type="button" class="buttonStyle-10word" onclick="showInPage();" value="模板内容页面效果" />
							<input name="button232" type="button" class="buttonStyle-2word" onclick="modifyOneSmsTemp();" value="修改" />
						</c:if>
						<input name="button232" type="button" class="buttonStyle-2word" onclick="window.opener.querySmsTemp();window.close();" value="关闭" />
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search" id="excDiv">
			<c:if test="${ifModify == '1' }">
				<table width="100%" border="0" cellpadding="0" cellspacing="10px">
					<tr>
						<td class="con_search_right">模板标题：</td>
						<td><input type="text" id="head" class="inputStyle" value="" size="50px;"/></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="con_search_right">类型：</td>
						<td><select class="inputStyle" id="type" onchange="getSendChannel();">
							<option value="">==请选择==</option>
							<option value="C">短信模板</option>
							<option value="H">HTTP模板</option>
							<option value="M">邮件模板</option>
						</select></td>
						<td class="con_search_right">通道类型：</td>
						<td><select class="inputStyle" id="sendChannel" >
							<option value="">==请选择==</option>
						</select></td>
					</tr>
					<tr>
						<td class="con_search_right">模板内容：</td>
						<td colspan="3" class="con_search_right" height="200px;">
							<textarea style="width:100%;height:100%;" id="body" onchange=""></textarea>
						</td>
					</tr>
					<tr>
						<td class="con_search_right">备注：</td>
						<td colspan="3" class="con_search_right" height="150px;">
							<textarea style="width:100%;height:100%;" id="remark" ></textarea>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${ifModify == '0' }">
				<table width="100%" border="0" cellpadding="0" cellspacing="10px">
					<tr>
						<td class="con_search_right">模板ID：</td>
						<td><input class="inputStyle" value="${obj.templateId }" readonly="readonly"/> </td>
						<td class="con_search_right">模板标题：</td>
						<td><input type="text" id="head" class="inputStyle" value="${obj.head }" size="50px;"/></td>
<!-- 						<td class="con_search_right">类型：</td> -->
<!-- 						<td> -->
<!-- 							<select class="inputStyle" id="type" disabled="disabled"> -->
<!-- 								<option value="">==请选择==</option> -->
<%-- 								<c:forEach items="${smsTypes }" var="smsType"> --%>
<%-- 									<option value="${smsType.SENDCHANNEL }"> --%>
<%-- 										<c:if test="${fn:contains(smsType.SENDCHANNEL,'C') }">短信模板</c:if> --%>
<%-- 										<c:if test="${fn:contains(smsType.SENDCHANNEL,'H') }">HTTP模板</c:if> --%>
<%-- 										<c:if test="${fn:contains(smsType.SENDCHANNEL,'M') }">邮件模板</c:if> --%>
<!-- 									</option> -->
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</td> -->
					</tr>
					<tr>
						<td class="con_search_right">类型：</td>
						<td><select class="inputStyle" id="type" onchange="getSendChannel();">
							<option value="">==请选择==</option>
							<option value="C">短信模板</option>
							<option value="H">HTTP模板</option>
							<option value="M">邮件模板</option>
						</select></td>
						<td class="con_search_right">通道类型：</td>
						<td><select class="inputStyle" id="sendChannel" >
							<option value="">==请选择==</option>
						</select></td>
					</tr>
					<tr>
<!-- 						<td class="con_search_right">通道类型：</td> -->
<!-- 						<td><select class="inputStyle" id="sendChannel" onchange="$('#type').val($('#sendChannel').val());"> -->
<!-- 							<option value="">==请选择==</option> -->
<%-- 							<c:forEach items="${smsTypes }" var="smsType"> --%>
<%-- 								<option value="${smsType.SENDCHANNEL }">${smsType.SENDCHANNEL }</option> --%>
<%-- 							</c:forEach> --%>
<!-- 						</select></td> -->
					</tr>
					<tr>
						<td class="con_search_right">模板内容：</td>
						<td colspan="3" class="con_search_right" height="200px;">
							<textarea style="width:100%;height:100%;" id="body" >${obj.body }</textarea>
						</td>
					</tr>
					<tr>
						<td class="con_search_right">备注：</td>
						<td colspan="3" class="con_search_right" height="150px;">
							<textarea style="width:100%;height:100%;" id="remark" >${obj.remark }</textarea>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</form>
</body>
</html>