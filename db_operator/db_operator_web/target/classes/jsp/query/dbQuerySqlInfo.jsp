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
<script src="<%=path%>/jscripts/xmlformat.js" type="text/javascript"></script>
<script type="text/javascript"
	src="<%=path%>/jscripts/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>SQL语义信息</title>
<script type="text/javascript">
	var pageNum=1;
	var pageSize=10;
	var totalCount=0;
	function auditSqlInfo(){
		if($("#auditStatus").val()==''){
			alert("请选择 审核结果！");
			return;
		}
		if($("#auditExp").val().trim()==''){
			alert("审核说明不能为空，请填写审核说明！");
			return;
		}
		
		var id='${obj.id}';
		var auditStatus=$("#auditStatus").val();
		$.ajax({
			type: 'POST',
			url: "<%=path%>/auditConsole/auditOneSqlInfo.htm",
			data : {
				id:id,
				auditStatus:auditStatus,
				auditExp:$("#auditExp").val()
			},
			dataType : "json",
			success : function(res) {
				if(res=='0'){
					alert("审核成功！");
					window.opener.queryQuerySql();
					window.close();
				}else{
					alert("审核失败！");
				}
			},
			error:function(){
				alert("本次操作失败！");
			}
		});
	}
	
	function showExcSqlRes(resList,totalNum){
		$("#resulstTable tr").remove();
		
		var trHead='<tr class="tableTitleBg">';
		for(param in resList[0]){
			trHead+='<td>'+param+'</td>';
		}
		trHead+='</tr>';
		$("#resulstTable").append(trHead);
		var trs='';
		for(i=0;i<resList.length;i++ ){
			trs+='<tr align="center">'
			for(param in resList[i]){
				trs+='<td>'+resList[i][param]+'</td>'
			}
			trs+='</tr>';
		}
		$("#resulstTable").append(trs);
		var tr="<tr align='center'> <td colspan='8'>";
		if(pageNum != 1){
			tr+="<a href='#' onclick='changePage(-1,"+pageSize+");'>上一页 </a>";
		}
		tr+="<span >   当前页面："+pageNum+"   </span>";
		tr+='<select id="pageSize" name="pageSize"'+
				'onchange="resetPageSize();" class="inputStyle">'+
				'<option value="5">5</option>'+
				'<option value="10">10</option>'+
				'<option value="50">50</option>'+
				'<option value="100">100</option>'+
			'</select>'
		if(pageNum*pageSize < totalNum){
			tr+="<a href='#' onclick='changePage(1,"+pageSize+");'>下一页</a>";
		}
		tr+="</td></tr>";
		$("#resulstTable").append(tr);
		$("#pageSize").val(pageSize);
	}
	function changePage(n,pageSize){
		pageNum=parseInt(n)+parseInt(pageNum);
		$.ajax({
			type: 'POST',
			url: "<%=path%>/DBConsole/refreshExcSqlRes.htm?pageSize="+pageSize+"&pageNum="+pageNum+"&excSqlId=${obj.id}",
			success:function(res){
				res=JSON.parse(res);
				totalCount=res.countExcSqlRes;
				showExcSqlRes(res.excSqlRes,res.countExcSqlRes);
			},
			error:function(){
				alert("操作失败！");
			}
		});
	}
	function resetPageSize(){
		pageSize=$("#pageSize").val();
		if((parseInt(pageNum)-1)*parseInt(pageSize)>=totalCount){
			pageNum=1;
		}
		changePage(0,pageSize);
	}
	function downLoadExcSqlRes(){
		$("#downExcelForm").submit();//表单提交 
		document.getElementById("downBtn").disabled = "disabled";
	}
	function getExcSqlInfo(){
		var div='<span style="text-align:left; font-weight:700">'+
					'执行sql的结果数据'+
					'<hr/>'+
				'</span>'+
				'<div style="overflow-x: auto; overflow-y: auto; height: 100%; width:100%;" >'+
					'<table width="100%" border="1px;" bordercolor="#436EEE" cellpadding="0" cellspacing="0px" id="resulstTable" >'+
					'</table>'+
				'</div>';
		$("#excDiv").append(div);
		changePage(0,pageSize);
		document.getElementById("excBtn").disabled = "disabled";
	}
</script>
</head>
<body>
	<form id="showOneSqlInfo" name="showOneSqlInfo">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%">
						<c:if test="${ifAuditPage == '1' }"><span>当前的位置:查看sql语义</span></c:if>
						<c:if test="${ifAuditPage == '0' }"><span>当前的位置:审核sql语义</span></c:if>
					</td>
				</tr>
			</table>
		</div>
		<div class="con_search02">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" class="con_search_right20"><span
						class="con_search_right">
						<c:if test="${ifAuditPage == '0' and obj.auditStatus == '1' }">
							<input name="button232" type="button" class="buttonStyle-2word" onclick="auditSqlInfo();" value="确定" />
						</c:if>
						<c:if test="${obj.auditStatus == '0' and ifAuditPage == '1' }">
							<input id="excBtn" type="button" class="buttonStyle-10word" onclick="getExcSqlInfo();" value="执行sql语义" />
							<input id="downBtn" type="button" class="buttonStyle-10word" onclick="downLoadExcSqlRes();" value="下载sql执行的结果数据" />
						</c:if>
						
						<input name="button232" type="button" class="buttonStyle-2word" onclick="window.opener.queryQuerySql();window.close();" value="关闭" />
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search" id="excDiv">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td colspan="4" style="text-align:left; font-weight:700">
						执行说明
						<hr>
					</td>
				</tr>
				<tr>
					<td class="con_search_right">执行目的：</td>
					<td><textarea id="excReasons" name="excReasons" rows="30"
							style="max-width: 1500px; max-height: 2000px; width: 300px; height: 70px;" readonly="readonly" 
							cols="60" class="inputStyle">${obj.excReasons }</textarea></td>
					<td class="con_search_right">级别状态：</td>
					<td><select id="excStaus" name="excStaus" class="inputStyle" disabled="disabled">
							<c:if test="${obj.excStaus == '0' }"><option value="0">正常</option></c:if>
							<c:if test="${obj.excStaus == '1' }"><option value="1">重要</option></c:if>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" style="text-align:left; font-weight:700">
						数据源信息
						<hr>
					</td>
				</tr>
				<tr>
					<td class="con_search_right">数据源：</td>
					<td colspan="3"><input type="text" class="inputStyle" id="sid" name="sid" size="49" readonly="readonly" value="${obj.excDataSourceObj.sid }"></td>
				</tr>
				
				<tr>
					<td colspan="4" style="text-align:left; font-weight:700">
						执行的sql语义
						<hr>
					</td>
				</tr>
				<tr>
					<td class="con_search_right">SQL：</td>
					<td colspan="3"><textarea id="excSql" name="excSql"
							style="max-width: 1500px; max-height: 2000px; width: 300px; height: 150px;" readonly="readonly"
							class="inputStyle">${obj.excSql }</textarea></td>
				</tr>
				
				<tr>
					<td colspan="4" style="text-align:left; font-weight:700">
						审核信息
						<hr>
					</td>
				</tr>
				
				<c:if test="${ifAuditPage == '0' and obj.auditStatus == '1' }" var="flag">
					<tr>
						<td class="con_search_right">审核结果：</td>
						<td>
							<select id="auditStatus" name="auditStatus" class="inputStyle">
								<option value="">请选择</option>
								<option value="0">已通过</option>
								<option value="2">未通过</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="con_search_right">审核说明：</td>
						<td colspan="3"><textarea id="auditExp" name="auditExp"
								style="max-width: 1500px; max-height: 2000px; width: 300px; height: 150px;" 
								class="inputStyle"></textarea></td>
					</tr>
				</c:if>
				<c:if test="${!flag }">
					<tr>
						<td class="con_search_right">操作日期：</td>
						<td>
							<input type="text" class="inputStyle" value="${obj.auditTime }" size="49" readonly="readonly"/>
						</td>
						<td class="con_search_right">单据状态：</td>
						<td>
							<select id="auditStatus" name="auditStatus" class="inputStyle" disabled="disabled">
								<c:if test="${obj.auditStatus=='0' }"><option value="0">已通过</option></c:if>
								<c:if test="${obj.auditStatus=='1' }"><option value="1">已提交</option></c:if>
								<c:if test="${obj.auditStatus=='2' }"><option value="2">未通过</option></c:if>
								<c:if test="${obj.auditStatus=='3' }"><option value="2">已执行</option></c:if>
							</select>
						</td>
					</tr>
					<tr>
						<td class="con_search_right">审核说明：</td>
						<td colspan="3"><textarea
								style="max-width: 1500px; max-height: 2000px; width: 300px; height: 150px;" readonly="readonly"
								class="inputStyle">${obj.auditExp }</textarea></td>
					</tr>
				</c:if>
				
			</table>
		</div>
	</form>
	<form id="downExcelForm" action="<%=path%>/DBConsole/downData2Excel.htm" style="display: none;">
		<input type="hidden" name="fileName" value="下载.xls" />
		<input type="hidden" name="excSqlId" value="${obj.id }" />
	</form>
</body>
</html>