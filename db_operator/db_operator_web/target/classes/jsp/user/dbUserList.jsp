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
<title>用户信息列表</title>
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
            url: "<%=path%>/userManager/refreshUsersList.htm",
					data : {
						pageNum : pageNum,
						pageSize : pageSize,
						userName : $("#userName").val().trim(),
						name : $("#name").val().trim()
					},
					dataType : "json",
					success : function(res) {
						$("#userListTable tr:gt(0)").remove();
						var trs = '';
						for ( var i in res.users) {
							var obj = res.users[i];
							if (obj.sex == '0') {
								obj.sex = '男';
							} else if (obj.sex == '1') {
								obj.sex = '女';
							}
							trs += '<tr class="tableConBg">'
								+ '<td width="5%"><input name="objId" type="hidden" '+
					'value="'+obj.id+ '"/> '+(parseInt(i)+1)+'</td>'
								+ '<td>'
								+ obj.userName
								+ '</td>'
								+ '<td>'
								+ obj.name
								+ '</td>'
								+ '<td>'
								+ obj.sex
								+ '</td>'
								+ '<td>'
								+ obj.roleNames
								+ '</td>'
								+ '<td>'
								+ obj.dbSids
								+ '</td>'
								+ '<td>'
								+ obj.smsMenus
								+ '</td>'
								+ '<td>'
								+ '<a href="#" onclick="delUser(this);">删除</a>'
								+ '</td>';
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

						$("#userListTable").append(trs);
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
	function queryUserList() {
		changePage(0);
	}
	function resetForm() {
		$("#userName").val('');
		$("#name").val('');
	}
	function delUser(a){
		if(!confirm("确定要删除该用户吗？")){
			return;
		}
		var userId=$(a).parent().parent().find('td').eq(0).find('input').eq(0).val();
		$.ajax({
            type: 'POST',
            url: "<%=path%>/userManager/updateUserDelFlag.htm",
            data:{
            	userId:userId
            },
            dataType:'json',
            success:function(res){
            	if(res.code=='0'){
            		alert("成功删除该用户！");
            		queryUserList();
            	}else{
            		alert("删除失败，请重新操作！");
            	}
            },
            error:function(){
            	alert("系统繁忙，请稍后再试！");
            }
		});
	}
</script>

</head>
<body onload="$('#pageSize').val(${numMap.pageSize});">
	<form action=" " method="post" id="querySqlListForm"
		name="querySqlListForm">
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_tableBg_left" width="20%"><span>当前的位置:用户信息列表</span>
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
							onclick="queryUserList();" /> <input name="button232"
							type="button" class="buttonStyle-2word" value="重置"
							onclick="resetForm();" /> 
					</span></td>
				</tr>
			</table>
		</div>
		<div class="con_search">
			<table width="100%" border="0" cellpadding="0" cellspacing="10px">
				<tr>
					<td class="con_search_right">用户名：</td>
					<td><input type="text" class="inputStyle" id="userName"
						 size="30"></td>
					<td class="con_search_right">用户名称：</td>
					<td><input type="text" class="inputStyle" id="name"
						size="30"></td>
				</tr>
			</table>
		</div>
		<div class="con_table">
			<h2 style="margin-left: 5px;">查询列表</h2>
		</div>
		<div class="con_table">
			<table width="100%" border="0" cellspacing="1" cellpadding="0"
				id="userListTable" class="tableBg">
				<tr class="tableTitleBg">
					<td width="5%"></td>
					<td>用户名</td>
					<td>用户名称</td>
					<td>性别</td>
					<td>页面权限</td>
					<td>审核的数据源</td>
					<td>短信模板操作权限</td>
					<td>操作</td>
				</tr>

				<c:forEach items="${users}" var="obj" varStatus="s">
					<tr class="tableConBg">
						<td width="5%"><input name="objId" type="hidden"
							value="${obj.id}" /> ${s.index+1 }</td>
						<td>${obj.userName }</td>
						<td>${obj.name }</td>
						<td><c:if test="${obj.sex == '0' }">男</c:if> <c:if
								test="${obj.sex == '1' }">女</c:if></td>
						<td>${obj.roleNames }</td>
						<td>${obj.dbSids }</td>
						<td>${obj.smsMenus }</td>
						<td><a href="#" onclick="delUser(this);">删除</a></td>
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