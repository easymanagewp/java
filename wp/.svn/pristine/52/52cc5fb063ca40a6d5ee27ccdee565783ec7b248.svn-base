<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>项目管理系统</title>
   <link rel="stylesheet" type="text/css" href="${basePath}/static/css/commons/global.css"/>
  <script type="text/javascript" src="${basePath}/static/js/commons/jquery_v1.11.0.js"></script>
  <script type="text/javascript" src="${basePath}/static/js/commons/global.js"></script>
  <link rel="stylesheet" href="${basePath}/static/plugins/editor/themes/default/default.css" />
<link rel="stylesheet" href="${basePath}/static/plugins/editor/plugins/code/prettify.css" />
<script charset="utf-8" src="${basePath}/static/plugins/editor/kindeditor.js"></script>
<script charset="utf-8" src="${basePath}/static/plugins/editor/lang/zh_CN.js"></script>
<script charset="utf-8" src="${basePath}/static/plugins/editor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="${basePath}/static/plugins/editor/plugins/multiimage/multiimage.js"></script>
<script type="text/javascript">
var fnLoadEditor = function(){
	var options = { 
		cssPath : '${basePath}/static/plugins/editor/plugins/code/prettify.css',
		height: "400px",
		items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
				'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				'anchor', 'link', 'unlink'],
		uploadJson : '${basePath}/editor/upload.do',
		//fileManagerJson : '${basePath}/admin/editor/browser.action',
		allowFileManager : false,
		pasteType :1,
		//imageTabIndex:1, 
		//newlineTag:"br",
		//resizeType : 0, //文本框不可拖动 
		afterCreate : function() { //kindeditor创建后，将编辑器的内容设置到原来的textarea控件里 
		this.sync(); }, 
		afterChange: function(){ //编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里 
		this.sync(); }, 
		afterBlur : function() { //编辑器聚焦后，将编辑器的内容设置到原来的textarea控件里 
		this.sync(); } 
	}; 
	var editor = KindEditor.create('#remark', options); 
	editor.sync();
	prettyPrint();
}
</script>
 </head>
 <body>
		<#assign isAdd=(!(RequestParameters.id??))>

		<#include "/include/header.ftl">
		<div class="body">
			<p>
				<label>标题</label>
			</p>
			<p>
				<input id="title" style="width:90%"/>
			</p>
			<p>
				<label>简介</label><br/>
			</p>
			<p>
				<textarea id="remark" style="width:90%;height:300px;"></textarea>
			</p>
			<p style="text-align:right;width:90%;">
				<button id="c002002002save">保存</button>
				<#if !isAdd>
				<button id="c002002002progress" class="hidden" >处理</button>
				<button id="c002002002complete" class="hidden" >完成</button>
				</#if>
				<button href="002_001_001.html">返回</button>
			</p>
		</div>

		<script type="text/javascript">
			var strId = "${(RequestParameters.id)!}";
			var $title = $("#title");
			var $remark = $("#remark");
			var _method = <#if isAdd>"post"<#else>"put"</#if>;
			
			var fnSave = function(status){
				return function(){
					var params = {
						"id" : strId,
						"title" : $('#title').val(),
						"remark" : $('#remark').val(),
						"_method" : _method,
						"status" : status
					}
					
					var url = "${basePath}/matter.do";
					if(status){
						url = "${basePath}/matter/transformation.do";
					}

					$.post(url,params,function(resp){
						if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
							if(status){
								window.location.href = "002_001_001.html"
							}else{
								alert("保存成功");
							}
						}else{
							alert(resp.msg);
						}
					},"json")
				}
			}

			var fnProgressBind = function(){
				$("#c002002002progress").on('click',fnSave('处理中'))
			}

			var fnCompleteBind = function(){
				$("#c002002002complete").on('click',fnSave('完成'))
			}
			<#if !isAdd>
			$.getJSON("${basePath}/matter/"+strId+".do",{},function(resp){
				if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
					$("#title").val(resp.res.title);
					$("#remark").val(resp.res.remark);
					if(resp.res.status=='创建'){
						$("#c002002002progress").removeClass("hidden");
						fnProgressBind();
					}else if(resp.res.status='处理中'){
						$("#c002002002complete").removeClass("hidden");
						fnCompleteBind();
					}
					fnLoadEditor();
				}else{
					alert(resp.msg);
				}
			})
			<#else>
				fnLoadEditor();
			</#if>

			var $save = $("#c002002002save");
			$save.on("click",fnSave(null))
		</script>
 </body>
</html>
