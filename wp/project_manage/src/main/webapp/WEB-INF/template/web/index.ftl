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
		<div class="select_bar">
			搜索：<input id="queryValue" value="${(RequestParameters.q)!}" style="width:90%;"/>
			<button style="margin-left:10px;" id="search">查询</button>
		</div>
		<div id="content_view" ></div>
		<script id="template" type="text/template">
		<table class="view_table">
			{{each result as doc}}
			<tr class="title" style="background-color:#eee;">
				<td><a href="004_002_002.html?id={{doc.id}}">{{#doc.title[0]}}</a></td>
			</tr>
			<tr>				
				<td style="padding:3px 10px 3px 10px;">{{if doc.content}}
					{{each doc.content as content}}
						{{#content}}<br/>
					{{/each}}
				{{/if}}</td>
			</tr>
			{{/each}}
		</table>
		</script>
		
		<script type="text/javascript">
		var $contentView = $("#content_view");
		var strTemplate = $("#template").text();
		var fnTemplateRender = template.render(strTemplate);
		$("#search").on('click',function(){
			window.location.href = "index.html?q="+$("#queryValue").val();
		})
		<#if RequestParameters.q??>
		$.getJSON("${basePath}/search.do",{pageSize:1000,queryValue:"${(RequestParameters.q)!}"},function(resp){
			if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
				var resultHtml = fnTemplateRender({result:resp.res})
				$contentView.html(resultHtml);
			}else{
				alert(resp.msg);
			}
		})
		</#if>

	</script>
	</div>
 </body>
</html>
