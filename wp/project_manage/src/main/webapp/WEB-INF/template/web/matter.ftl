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
		<ul class="tab">
			<li><button <#if !RequestParameters.status??> class="current"</#if> href="002_001_001.html">全部</button></li>
			<li><button <#if (RequestParameters.status?? && RequestParameters.status=='创建')>class="current"</#if> href="002_001_001.html?status=创建">待处理</button></li>
			<li><button <#if (RequestParameters.status?? && RequestParameters.status=='处理中')>class="current"</#if> href="002_001_001.html?status=处理中">处理中</button></li>
			<li><button <#if (RequestParameters.status?? && RequestParameters.status=='完成')>class="current"</#if> href="002_001_001.html?status=完成">已完成</button></li>
		</ul>
		<input type="hidden" name="status" value="${(RequestParameters.status)!}"/>
		<input type="hidden" name="currentPage" value=""/>
		<div id="tab_content_view" class="tab_content">
			
		</div>
	</div>
	<script id="template" type="text/template">
		<div class="view_panel">
			<table class="view_table">
				<tr class="title">
					<td>#</td>
					<td>标题</td>
					<td>简介</td>
					<td>状态</td>
					<td>创建时间</td>
					<td>处理时间</td>
					<td>操作</td>
				</tr>
				{{each result.resultList as matter}}
				<tr>
					<td>{{matter.code}}</td>
					<td>
						{{if matter.title.length>20}}
							{{matter.title.substring(0,20)+"..."}}
						{{else}}
							{{matter.title}}
						{{/if}}
					</td>
					<td>
						{{if matter.remark.length>20}}
							{{matter.remark.substring(0,20)+"..."}}
						{{/if}}
						{{if matter.remark.length<=20}}
							{{matter.remark}}
						{{/if}}
					</td>
					<td>{{matter.status}}</td>
					<td>{{matter.createTime}}</td>
					<td>
						{{if matter.status=='创建'}}
						未处理
						{{else}}
							{{matter.progressTime}}
						{{/if}}
					</td>
					<td>
						<button href="002_002_002.html?id={{matter.id}}">查看</button>
						{{if matter.status=='创建'}}
							<button href="002_002_001.html?id={{matter.id}}">处理</button>
						{{/if}}
						{{if matter.status=='处理中'}}
							<button href="002_002_001.html?id={{matter.id}}">完成</button>
						{{/if}}
						<button onclick="fnDeleteItem('{{matter.id}}')">删除</button>
					</td>
				</tr>
				{{/each}}
			</table>
		</div>
		<div class="paging">
			总记录数<span>{{result.totalRow}}</span>,当前第{{result.currentPage}}/{{result.totalPage}}页
			<button onclick="fnLoadData({requestPage:1})">首&nbsp;&nbsp;页</button>
			<button onclick="fnLoadData({requestPage:{{(result.currentPage)-1}}})">上一页</button>
			<button onclick="fnLoadData({requestPage:{{(result.currentPage)+1}}})">下一页</button>
			<button onclick="fnLoadData({requestPage:{{result.totalPage}}})">尾&nbsp;&nbsp;页</button>
		</div>
	</script>

	<script type="text/javascript">
		var $status = $(":input[name='status']");
		var $currentPage = $(":input[name='currentPage']");
		var strTemplate = $("#template").text();
		var fnTemplateRender = template.render(strTemplate);
		var $tabContentView = $("#tab_content_view");

		var fnDeleteItem = function(strId){
			if(window.confirm("确定删除该元素？")){
				$.post("${basePath}/matter/"+strId+".do",{_method:"delete"},function(resp){
					if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
						alert("删除成功");
						window.location.reload();
					}else{
						alert(resp.msg);
					}
				})
			}
		}

		var fnLoadData = function(params){
			var _params = $.extend({},params,{
				status : $status.val(),
				pageSize : 7
			})
			$.getJSON("${basePath}/matter.do",_params,function(resp){
				if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
					var oResult = resp.res;
					$currentPage = oResult.currentPage;
					var resultHtml = fnTemplateRender({result:oResult});
					$tabContentView.html(resultHtml)
				}else{
					alert(resp.msg);
				}
				WP.loadStyle();
			})
		};

		fnLoadData();

	</script>
 </body>
</html>
