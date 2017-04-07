<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/conf/tld/c.tld" prefix="c"%>
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
<title>查询sql语义记录列表</title>
<script type="text/javascript">
	function changePage(n){
		var pageNum=parseInt($("#pageNum").val())+parseInt(n);
		var pageSize=$("#pageSize").val().trim();
		var totalNum=$("#totalNum").val().trim();
		console.log(pageNum+';'+pageSize+";"+totalNum);
		
		if((pageNum-1) * parseInt(pageSize) >= parseInt(totalNum)){
			pageNum=1;
		}
		
		$.ajax({
            type: 'POST',
            url: "<%=path%>/DBConsole/refreshQuerySqls.htm",
					data : {
						pageNum : pageNum,
						pageSize : pageSize,
						excReasons : $("#excReasons").val().trim(),
						excStaus : $("#excStaus").val().trim(),
						excDbId : $("#sid").val(),
						auditStatus : $("#auditStatus").val().trim(),
						startTime : $("#startTime").val().trim(),
						endTime : $("#endTime").val().trim()
					},
					dataType : "json",
					success : function(res) {
						$("#querySqlTable tr:gt(0)").remove();
						var trs = '';
						for ( var i in res.objsRes) {
							var obj = res.objsRes[i];
							if (obj.excStaus == '0') {
								obj.excStaus = '正常';
							} else if (obj.excStaus == '1') {
								obj.excStaus = '重要';
							}
							if (obj.auditStatus == '1') {
								obj.auditStatus = '已提交';
							} else if (obj.auditStatus == '0') {
								obj.auditStatus = '已通过';
							} else if (obj.auditStatus == '2') {
								obj.auditStatus = '未通过';
							}else if (obj.auditStatus == '3') {
								obj.auditStatus = '已执行';
							}
							if(obj.auditorObj != '' && obj.auditorObj != null ){
								trs += '<tr class="tableConBg" ondblclick="openChildInfo(this);">'
										+ '<td width="5%"><input name="objId" type="hidden" '+
							'value="'+obj.id+ '"/> '+(parseInt(i)+1)+'</td>'
										+ '<td>'
										+ obj.excDataSourceObj.sid
										+ '</td>'
										+ '<td>'
										+ obj.excSql
										+ '</td>'
										+ '<td>'
										+ obj.excReasons
										+ '</td>'
										+ '<td>'
										+ obj.excStaus
										+ '</td>'
										+ '<td>'
										+ obj.ts
										+ '</td>'
										+ '<td>'
										+ obj.auditStatus
										+ '</td>'
										+ '<td>'
										+ obj.auditorObj.opernum 
										+ '</td>';
							}else{
								trs += '<tr class="tableConBg" ondblclick="openChildInfo(this);">'
									+ '<td width="5%"><input name="objId" type="hidden" '+
						'value="'+obj.id+ '"/> '+(parseInt(i)+1)+'</td>'
									+ '<td>'
									+ obj.excDataSourceObj.sid
									+ '</td>'
									+ '<td>'
									+ obj.excSql
									+ '</td>'
									+ '<td>'
									+ obj.excReasons
									+ '</td>'
									+ '<td>'
									+ obj.excStaus
									+ '</td>'
									+ '<td>'
									+ obj.ts
									+ '</td>'
									+ '<td>'
									+ obj.auditStatus
									+ '</td>'
									+ '<td>'
									+ ''
									+ '</td>';
							}
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

						$("#querySqlTable").append(trs);
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
	function queryQuerySql() {
		changePage(0);
	}
	function resetForm() {
		$("#excReasons").val('');
		$("#excStaus").val('');
		$("#sid").val('');
		$("#startTime").val('');
		$("#endTime").val('');
		$("#auditStatus").val('')
	}
	function openChildInfo(tr) {
		var querySqlId = $(tr).find('td').eq(0).find('input').eq(0).val();
		window.open ('<%=path%>/DBConsole/getOneSqlInfo.htm?querySqlId='+querySqlId+'&ifAuditPage='+'1'+'&pageNum=1&pageSize=10','oneSqlInfo','height=750,width=1000,top=100,left=200,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no') 
	}
</script>

</head>
<body onload="$('#pageSize').val(${numMap.pageSize});">
	<form action=" " method="post" id="querySqlListForm"
		name="querySqlListForm">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%"><span>当前的位置:查询sql语义记录列表</span>
					</td>
				</tr>
			</table>
		</div>
		<div class="con_search02">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" class="con_search_right20"><span
						class="con_search_right"> <input name="button232"
							type="button" class="buttonStyle-2word" value="查询"
							onclick="queryQuerySql();" /> <input name="button232"
							type="button" class="buttonStyle-2word" value="重置"
							onclick="resetForm();" /> 
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_search_right">执行目的：</td>
					<td><input type="text" class="inputStyle" id="excReasons"
						name="excReasons" size="30"></td>
					<td class="con_search_right">级别状态：</td>
					<td><select id="excStaus" name="excStaus" class="inputStyle">
							<option value="">=====请选择=====</option>
							<option value="0">正常</option>
							<option value="1">重要</option>
					</select></td>
				</tr>
				<tr>
					<td class="con_search_right">数据源：</td>
					<td><select id="sid" name="sid" class="inputStyle">
							<option value="">=====请选择=====</option>
							<c:forEach items="${dataSourceObjs }" var="dataSourceObj" >
								<option value="${dataSourceObj.id }">${dataSourceObj.sid }</option>
							</c:forEach>
					</select></td>
					<td class="con_search_right">提交日期：</td>
					<td><input id="startTime" size="16" name="startTime"
						readonly="readonly" class="Wdate" type="text"
						onFocus="var endTime=$dp.$('endTime');WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'endTime\')}'})" />
						至 <input id="endTime" size="16" name="endTime" readonly="readonly"
						class="Wdate" type="text"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" /></td>
				</tr>
				<tr>
					<td class="con_search_right">单据状态：</td>
					<td><select id="auditStatus" name="auditStatus"
						class="inputStyle">
							<option value="">=====请选择=====</option>
							<option value="1">已提交</option>
							<option value="0" selected="selected">已通过</option>
							<option value="2">未通过</option>
							<option value="3">已执行</option>
					</select></td>
				</tr>
			</table>
		</div>
		<div class="con_table">
			<h2 style="margin-left: 5px;">查询列表</h2>
		</div>
		<div class="con_table">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				id="querySqlTable" class="tableBg">
				<tr class="tableTitleBg">
					<td width="5%"></td>
					<td>数据源</td>
					<td>sql语义</td>
					<td>执行目的</td>
					<td>级别状态</td>
					<td>提交时间</td>
					<td>单据状态</td>
					<td>审核人工号</td>
				</tr>

				<c:forEach items="${objs}" var="obj" varStatus="s">
					<tr class="tableConBg" ondblclick="openChildInfo(this);">
						<td width="5%"><input name="objId" type="hidden"
							value="${obj.id}" /> ${s.index+1 }</td>
						<td>${obj.excDataSourceObj.sid }</td>
						<td>${obj.excSql }</td>
						<td>${obj.excReasons }</td>
						<td><c:if test="${obj.excStaus == '0' }">正常</c:if> <c:if
								test="${obj.excStaus == '1' }">重要</c:if></td>
						<td>${obj.ts }</td>
						<td><c:if test="${obj.auditStatus == '0' }">已通过</c:if> <c:if
								test="${obj.auditStatus == '1' }">已提交</c:if> <c:if
								test="${obj.auditStatus == '2' }">未通过</c:if><c:if
								test="${obj.auditStatus == '3' }">已执行</c:if></td>
						<td>${obj.auditorObj.opernum }</td>
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