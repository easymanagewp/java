<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>项目管理系统</title>
   <link rel="stylesheet" type="text/css" href="${basePath}/static/css/commons/global.css"/>
  <script type="text/javascript" src="${basePath}/static/js/commons/jquery_v1.11.0.js"></script>
  <script type="text/javascript" src="${basePath}/static/js/commons/global.js"></script>
  
 </head>
 <body>
		<#include "/include/header.ftl">
		<div class="body">
			<p>
				<h1 id="title"></h1>	
			</p>
			<p id="remark">
				
			</p>
			<p style="text-align:right;width:90%;">
				<button href="002_001_001.html">返回</button>
			</p>
		</div>

		<script type="text/javascript">
			var strId = "${(RequestParameters.id)!}";
			var $title = $("#title");
			var $remark = $("#remark");
			
			$.getJSON("${basePath}/matter/"+strId+".do",{},function(resp){
				if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
					$("#title").text(resp.res.title);
					$("#remark").append(resp.res.remark);
				}else{
					alert(resp.msg);
				}
			})
		</script>
 </body>
</html>
