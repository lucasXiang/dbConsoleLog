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
<title>查询SQL语义控制平台</title>
<script type="text/javascript">
	function submitSql(){
		if($("#excReasons").val().trim()==''){
			alert("请输入 执行目的");
			return;
		}
		if($("#excStaus").val().trim()==''){
			alert("请选择 标记状态");
			return;
		}
		if($("#excSql").val().trim()==''){
			alert("请输入要执行的sql语句");
			return;
		}
		if($("#dbsid").val()==''){
			alert("请输入数据源sid");
			return;
		}
		if($("#toAuditors").val()==''){
			alert("请选审核人！");
			return;
		}
		
		$('#excSql').format({method: 'sql'});
		var excSql=$('#excSql').val().toUpperCase();
		if(excSql.indexOf("SELECT")==-1 || /delete[\s]+from/ig.test(excSql) || /update[\s]+set/ig.test(excSql)){
			alert("请输入正确的查询sql语义");
			return;
		}
		
		$.ajax({
            type: 'POST',
            url: "<%=path%>/DBConsole/submitDBQuerySQL.htm",
            data: {
            	excReasons: $("#excReasons").val().trim(),
            	excStaus: $("#excStaus").val().trim(),
            	excSql: $("#excSql").val().trim(),
            	excDbId: $("#dbsid").val().trim(),
            	toAuditor:$("#toAuditors").val()
            },
            dataType: "json",
            success:function(res){
            	alert(res.msg);
            	if(res.code=='0'){
            		$("#submitDBSQLForm")[0].reset();
            	}
            },
            error:function(r){
            	alert("提交失败！");
            }
        });
	}
	function getToAuditors(select){
		if($(select).val()==''){
			$("#toAuditors").find("option:gt(0)").remove();
			return;
		}
		$.ajax({
            type: 'POST',
            url: "<%=path%>/DBConsole/getToAuditors.htm",
            data: {
            	dbSourId: $(select).val()
            },
            dataType: "json",
            success:function(res){
            	if(res!=null){
            		$("#toAuditors").find("option:gt(0)").remove();
            		var opts='';
            		for(i=0;i<res.length;i++){
            			var toAudit=res[i];
            			opts+='<option value="'+toAudit.OPERATORID+'">'+toAudit.OPERATORNUM+'</option> ';
            		}
            		$("#toAuditors").append(opts);
            	}
            },
            error:function(r){
            	alert("提交失败！");
            }
        });
	}
	
</script>
</head>
<body>

	<form id="submitDBSQLForm" name="submitDBSQLForm">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%"><span>当前的位置:查询SQL语义提交</span>
					</td>
				</tr>
			</table>
		</div>
		<div class="con_search02">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" class="con_search_right20"><span
						class="con_search_right"> <input type="button"
							class="buttonStyle-10word" value="格式化我的sql语义"
							onclick="$('#excSql').format({method: 'sql'});" /> <input
							name="button232" type="button" class="buttonStyle-2word"
							onclick="submitSql();" value="提交" />
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search">
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
							style="max-width: 1500px; max-height: 2000px; width: 300px; height: 70px;"
							cols="60" class="inputStyle"></textarea></td>
					<td class="con_search_right">级别状态：</td>
					<td><select id="excStaus" name="excStaus" class="inputStyle">
							<option value="">=====请选择=====</option>
							<option value="0">正常</option>
							<option value="1">重要</option>
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
					<td>
						<select id="dbsid" name="dbsid" class="inputStyle" onchange="getToAuditors(this);">
							<option value="">=====请选择=====</option>
							<c:forEach items="${dataSourceObjs }" var="obj">
								<option value="${obj.id }">${obj.sid} </option>
							</c:forEach>
						</select>
					</td>
					<td class="con_search_right">审核人：</td>
					<td>
						<select id="toAuditors" name="toAuditors" class="inputStyle" >
							<option value="">=====请选择=====</option>
						</select>
					</td>
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
							style="max-width: 1500px; max-height: 2000px; width: 300px; height: 150px;"
							class="inputStyle"></textarea></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>