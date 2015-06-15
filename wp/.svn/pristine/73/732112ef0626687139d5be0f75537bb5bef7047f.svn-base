<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>项目管理系统</title>
  <link rel="stylesheet" type="text/css" href="${basePath}/static/css/commons/global.css"/>
  <script type="text/javascript" src="${basePath}/static/js/commons/jquery_v1.11.0.js"></script>
  <script type="text/javascript" src="${basePath}/static/js/commons/global.js"></script>
  <script type="text/javascript" src="${basePath}/static/js/commons/template.js"></script>
 </head>
 <body>
	<#include "/include/header.ftl">
	<div class="body">
		<ul id="project_list">
			
		</ul>
	</div>

	<script type="text/template" id="project_item_template">
		{{each projects as project}}
			<li style="margin-left:3px;"><button href="001_002_001.html?id={{project.id}}">{{project.code}}({{project.name}})</button></li>
		{{/each}}
	</script>
	
	<script type="text/javascript">
		var $projectList = $("#project_list");
		var strProjectItemTemplate = $("#project_item_template").text();

		var fnTemplate = template.render(strProjectItemTemplate);

		$.getJSON("${basePath}/project.do",{},function(resp){
			if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
				var strResult = fnTemplate({projects:resp.res})
				$projectList.append(strResult);
				WP.loadStyle();
			}else{
				alert(resp.msg);
			}
		})
	</script>
 </body>
</html>
