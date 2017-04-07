<%@page import="com.tech.member.entity.UserObj"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/conf/tld/c.tld" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="author" content="ray01@sogou.com,Ray" />
<meta name="Copyright" content="www.unspay.com,上海银生宝电子支付服务有限公司版权所有" />
<meta name="description" content="数据操作控制平台系统" />
<meta content="数据操作控制平台系统" name="keywords" />
<!-- easyui-->
<link href="<%=request.getContextPath()%>/style/style.css" rel="stylesheet" type="text/css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jscripts/easyui/themes/easyui.css" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jscripts/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/communal.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/common.css" />
<script src="<%=request.getContextPath()%>/jscripts/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscripts/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscripts/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">
	function tabs(title,url){
		if (parent.$('#tab').tabs('exists', title)) {
			parent.$('#tab').tabs('close', title);
		} 
		parent.$('#tab').tabs("add", {
	    title: title,
	    content : '<iframe /*scrolling="no"*/ frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>',
	    closable: true
		});
	}
</script>
</head>
<body id="body" class="easyui-layout">

	<!-- 上北  start-->
	<div data-options="region:'north'" style="height: 93px;overflow: visible;">
		<div class="headers">
        	<div class="top_left left">
                <span class="left top_text ml10">技术支撑服务</span>
        	</div>
        	 <div class="top_center center">
            	<span class="left top_text ml10">欢迎你, <%=((UserObj)session.getAttribute("user")).getName() %></span>
            </div>
            <div class="top_right right">
                <div class="mt5">
                    <a class="top_btn mr5" href="<%=request.getContextPath()%>/DBConsole/logout.htm"><i></i><span>注销</span></a>
                </div>
            </div>
        </div>
	</div>
	<!-- 左西 start -->
	<!-- 左西 start -->
	 <div data-options="region:'west'" style="width: 250px;" title="功能导航">
		<div class="easyui-accordion" data-options="fit : true,border : false">
			<c:forEach var="par" items="${funcs}">
				<div title="${par.pfuncValue }" selected="true">
				<c:forEach var="sub" items="${par.chFuncObjs }">
					<ul>
						<li><a href="#" url="<%=request.getContextPath()%>${sub.funcUrl}"
							onclick="tabs('${sub.funcValue }','<%=request.getContextPath()%>${sub.funcUrl}')">${sub.funcValue}</a></li>
					</ul>
				</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div> 
	
	<!-- 中心 start-->
	<div data-options="region:'center'"  style="overflow:hidden;">
		<div id="tab" class="easyui-tabs" data-options="fit:true,border:false,iconCls:'icon-reload',closable:true">
			<div title="欢迎页">
				<h1 align="center"><p>欢迎你, <%=((UserObj)session.getAttribute("user")).getOpernum() %></p></h1>
			</div>
		</div>
	</div>
	<!-- 下南 start-->
	<div data-options="region:'south'" style="overflow:hidden;">
		<div class="panel-header panel-header-noborder" style="text-align: center;">
			 <a href="https://www.unscard.com/index/customerservice.do?method=index">上海商联信电子支付服务有限公司  </a>   版权所有
			<br/>
                                      沪ICP备06052865
		</div>
	</div>
</body>
</html>