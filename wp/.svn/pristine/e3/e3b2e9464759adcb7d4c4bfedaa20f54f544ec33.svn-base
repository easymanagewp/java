<div class="header bgcolor">
	<div class="float_l line_height">
		<ul class="list_inline">
			<li>
				<button href="#project_submenu">项目管理</button>
				<div id="project_submenu" class="submenu">
					<button href="001_002_001.html">添加</button><br/>
					<button href="001_001_001.html">查看</button>
				</div>
			</li>
			<li>
				<button href="003_001_001.html">接口管理</button>
				
			</li>
			<li>
				<button href="#matter_submenu">问题列表</button>
				<div id="matter_submenu" class="submenu">
					<button href="002_002_001.html">添加</button><br/>
					<button href="002_001_001.html">查看</button>
				</div>
			</li>
			<li>
				<button href="#document_submenu">document</button>
				<div id="document_submenu" class="submenu">
					<button href="004_002_001.html">Add</button><br/>
					<button href="004_001_001.html">Page</button>
				</div>
			</li>
			<li>
				<button href="index.html">首页</button>
			</li>
		</ul>
	</div>
	<div class="float_r line_height">
		<select id="project_select_list" onchange="fnChangeProject()">
		</select>
	</div>

	
	<script type="text/javascript">

		var $projectSelectList = $("#project_select_list");
		var fnChangeProject = function(){
			$.post("${basePath}/project/current_project.do",{_method:"put",code:$projectSelectList.val()},function(){
				window.location.reload();
			},"json")
		}
		
		$.getJSON("${basePath}/project.do",{},function(resp){
			if(resp.status==WP.CONFIG.JSON_RESP_STATUS.SUCCESS){
				var projects = resp.res;
				for(var iIndex = 0;iIndex<projects.length;iIndex++){
					var project = projects[iIndex];
					var option = $("<option>").attr('value',project.code).text(project.name)
					if(project.code=='${(current_project)!}'){
						option.attr("selected","selected");
					}
					$projectSelectList.append(option);
				}
				<#if (!current_project??)>
				fnChangeProject();
				</#if>
			}else{
				alert(resp.msg);
			}
		})
	</script>
</div>