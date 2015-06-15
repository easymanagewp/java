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
			<h2>编辑项目</h2>
			<p><label>项目编号:</label><br/><input name="code" style="width:90%"/></p>
			<p><label>项目名称:</label><br/><input name="name" style="width:90%"/></p>
			<p><label>项目简介:</label><br/><textarea name="remark" style="width:90%;height:200px;"></textarea></p>
			<p style="text-align:right;width:90%;">
				<button id="save">保存</button>
				<button href="002_002_001.html">返回</button>
			</p>
		</div>

		<script type="text/javascript">
			var $save = $("#save");
			var $code = $(":input[name='code']");
			var $name = $(":input[name='name']");
			var $remark = $(":input[name='remark']");

			<#if (RequestParameters.id??)>
			$.getJSON("${basePath}/project/${RequestParameters.id}.do",{},function(resp){
				if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
					var project = resp.res;
					$code.val(project.code);
					$name.val(project.name);
					$remark.val(project.remark);
				}else{
					alert(resp.msg);
				}
			})
			</#if>
			$save.on("click",function(){
				var params = {
					"code" : $code.val(),
					"name" : $name.val(),
					<#if RequestParameters.id??>
					"id" : "${(RequestParameters.id)!}",
					"_method" : "put",
					</#if>
					"remark" : $remark.val()
					
				}
				$.post("${basePath}/project.do",params,function(resp){
					if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
						window.location.href = "001_001_001.html";
					}else{
						alert(resp.msg);
					}
				},"json")
			})
			
		</script>
 </body>
</html>
