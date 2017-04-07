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
<script type="text/javascript"
	src="<%=path%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>短信模板列表</title>
<script type="text/javascript">
	function changePage(n){
		var pageNum=parseInt($("#pageNum").val())+parseInt(n);
		var pageSize=$("#pageSize").val().trim();
		var totalNum=$("#totalNum").val().trim();
		if((pageNum-1) * parseInt(pageSize) >= parseInt(totalNum)){
			pageNum=1;
		}
		$.ajax({
            type: 'POST',
            url: "<%=path%>/smsTempConsole/refreshSmsTempShowPage.htm",
					data : {
						pageNum : pageNum,
						pageSize : pageSize,
						head : $("#head").val().trim(),
						body : $("#body").val().trim(),
						sendChannel : $("#sendChannel").val(),
						type:$("#type").val(),
						createUser : $("#createUser").val().trim(),
						startTime : $("#startTime").val().trim(),
						endTime : $("#endTime").val().trim()
					},
					dataType : "json",
					success : function(res) {
						$("#smsTempTable tr:gt(0)").remove();
						var trs = '';
						for ( var i in res.smsTempObjs) {
							var obj = res.smsTempObjs[i];
							var type='';
							if(obj.sendChannel.indexOf('C')!= -1){
								type='短信模板';
							}else if(obj.sendChannel.indexOf('H')!= -1){
								type='HTTP模板';
							}else if(obj.sendChannel.indexOf('M')!= -1){
								type='邮件模板';
							}
							trs += '<tr class="tableConBg">'
									+ '<td width="5%"><input name="templateId" type="hidden" '+
						'value="'+obj.templateId+ '"/> '+obj.templateId+'</td>'
									+ '<td>'
									+ obj.head
									+ '</td>'
									+ '<td>'
									+ '<textarea style="width:100%;height:100%">'+obj.body+'</textarea>'
									+ '</td>'
									+ '<td>'
									+ obj.sendChannel
									+ '</td>'
									+ '<td>'
									+ type
									+ '</td>'
									+ '<td>'
									+ obj.remark
									+ '</td>'
									+ '<td>'
									+ obj.createUser
									+ '</td>'
									+ '<td>'
									+ obj.createTime
									+ '</td>'
									+ '<td><a href="#" onclick="modifySmsTemp(this)">修改</a>&nbsp;<a href="#" onclick="delSmsTemp(this);">删除</a></td>'
							trs += '</tr>';
						}
						trs += '<tr class="tableConBg-2">'
								+ '<td colspan="8"><input id="pageNum" type="hidden"'+
					'value="'+res.pageNum+'" /> <input id="totalNum" type="hidden" value="'+res.totalNum+'"/>';
						if (res.pageNum > 1) {
							trs += '<a href="#" onclick="changePage(-1);" style="color: #080808">上一页</a>&nbsp;';
						}
						trs += ' 当前页面：' + res.pageNum + '&nbsp;';
						if ((parseInt(res.pageNum))
								* parseInt(res.pageSize) < parseInt(res.totalNum)) {
							trs += '<a href="#" onclick="changePage(1);" style="color: #080808">下一页</a>&nbsp;';
						}
						trs += '&nbsp; 页面大小：<select id="pageSize" name="pageSize" onchange="resetPageSize();"'
								+ 'class="inputStyle">'
								+ '<option value="5">5</option>'
								+ '<option value="10">10</option>'
								+ '<option value="50">50</option>'
								+ '<option value="100">100</option>'
								+ '<option value="250">250</option>'
								+ '<option value="500">500</option><option value="1000">1000</option></select></td></tr>';

						$("#smsTempTable").append(trs);
						$("#pageSize").val(res.pageSize);
					},
					error : function(r) {
						alert("提交失败！");
					}
				});
	}
	function resetPageSize() {
		changePage(0);
	}
	function querySmsTemp() {
		changePage(0);
	}
	function resetForm() {
		$("#head").val('');
		$("#body").val('');
		$("#sendChannel").val('');
		$("#createUser").val('');
		$("#startTime").val('');
		$("#endTime").val('');
		$('#type').val($('#sendChannel').val());
	}
	function delSmsTemp(a){
		if(!confirm("确认要删除？")){
			return;
		}
		var id=$(a).parent().parent().find('td').eq(0).find('input').eq(0).val();
		$.ajax({
			type:'post',
			url: "<%=path%>/smsTempConsole/delOneSmsTempById.htm",
			data:{id:id},
			dataType:'json',
			success:function(res){
				if(res.code=='0'){
					alert("删除成功！");
				}else{
					alert("删除失败，请重新操作！");
				}
				querySmsTemp();
			},
			error:function(){
				alert("系统繁忙，请稍后再试...");
			}
		});
	}
	function addOneSmsTemp(){
		window.open ('<%=path%>/smsTempConsole/getOneSmsTemp.htm?ifModify=1','oneSqlInfo','height=750,width=1000,top=100,left=200,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	}
	function modifySmsTemp(a){
		var id=$(a).parent().parent().find('td').eq(0).find('input').eq(0).val();
		window.open('<%=path%>/smsTempConsole/getOneSmsTemp.htm?ifModify=0&templateId='+id,'oneSqlInfo','height=750,width=1000,top=100,left=200,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no');
	}
	
	function getSendChannel(){
		var sendChannels=JSON.parse('${smsTypes }');
		$("#sendChannel option:gt(0)").remove();
		var type=$("#type").val();
		for(i in sendChannels){
			if((sendChannels[i].SENDCHANNEL).indexOf(type)!=-1){
				$("#sendChannel").append('<option value="'+sendChannels[i].SENDCHANNEL+'" >'+sendChannels[i].SENDCHANNEL+'</option>');
			}
		}
	}
</script>
</head>
<body onload="$('#pageSize').val(${numMap.pageSize});">
	<form action=" " method="post" id="querySqlListForm"
		name="querySqlListForm">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%"><span>当前的位置:短信模板管理</span>
					</td>
				</tr>
			</table>
		</div>
		<div class="con_search02">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" class="con_search_right20"><span
						class="con_search_right"><input name="button232"
							type="button" class="buttonStyle-2word" value="新增"
							onclick="addOneSmsTemp();" /> <input name="button232"
							type="button" class="buttonStyle-2word" value="查询"
							onclick="querySmsTemp();" /> <input name="button232"
							type="button" class="buttonStyle-2word" value="重置"
							onclick="resetForm();" /> 
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_search_right">标题：</td>
					<td><input type="text" class="inputStyle" id="head" size="30"></td>
					<td class="con_search_right">内容：</td>
					<td><input type="text" class="inputStyle" id="body" size="30"></td>
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
					<td class="con_search_right">创建人：</td>
					<td>
						<input type="text" class="inputStyle" id="createUser" size="30">
					</td>
					<td class="con_search_right">创建日期：</td>
					<td><input id="startTime" size="16" name="startTime"
						readonly="readonly" class="Wdate" type="text"
						onFocus="var endTime=$dp.$('endTime');WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})" />
						至 <input id="endTime" size="16" name="endTime" readonly="readonly"
						class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" /></td>
				</tr>
			</table>
		</div>
		<div class="con_table">
			<h2 style="margin-left: 5px;">查询列表</h2>
		</div>
		<div class="con_table">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				id="smsTempTable" class="tableBg">
				<tr class="tableTitleBg">
					<td width="5%">id</td>
					<td>标题</td>
					<td>内容</td>
					<td>通道类型</td>
					<td>类型</td>
					<td>备注</td>
					<td>创建人工号</td>
					<td>创建时间</td>
					<td>操作</td>
				</tr>

				<c:forEach items="${objs}" var="obj" varStatus="s">
					<tr class="tableConBg">
						<td width="5%"><input name="templateId" type="hidden"
							value="${obj.templateId}" /> ${obj.templateId}</td>
						<td>${obj.head }</td>
						<td width="30%"><textarea style="width:100%;height:100%">${obj.body }</textarea></td>
						<td>${obj.sendChannel }</td>
						<td>
							<c:if test="${fn:contains(obj.sendChannel,'C') }">短信模板</c:if>
							<c:if test="${fn:contains(obj.sendChannel,'H') }">HTTP模板</c:if>
							<c:if test="${fn:contains(obj.sendChannel,'M') }">邮件模板</c:if>
						</td>
						<td width="20%">remark</td>
						<td>${obj.createUser }</td>
						<td>${obj.createTime }</td>
						<td><a href="#" onclick="modifySmsTemp(this)">修改</a>&nbsp;<a href="#" onclick="delSmsTemp(this);">删除</a></td>
					</tr>
				</c:forEach>

				<tr class="tableConBg-2">
					<td colspan="8"><input id="pageNum" type="hidden"
						value="${numMap.pageNum }" /> <input id="totalNum" type="hidden"
						value="${numMap.totalNum }" /> <c:if test="${numMap.pageNum > 1 }">
							<a href="#" onclick="changePage(-1);" style="color: #080808">上一页</a>
						</c:if> &nbsp; 当前页面：${numMap.pageNum } &nbsp; <c:if
							test="${(numMap.pageNum)*numMap.pageSize < numMap.totalNum }">
							<a href="#" onclick="changePage(1);" style="color: #080808">下一页</a>
						</c:if> &nbsp;&nbsp; 页面大小：<select id="pageSize" name="pageSize"
						onchange="resetPageSize();" class="inputStyle">
							<option value="5">5</option>
							<option value="10">10</option>
							<option value="50">50</option>
							<option value="100">100</option>
							<option value="250">250</option>
							<option value="500">500</option>
							<option value="1000">1000</option>
					</select></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>