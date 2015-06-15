package cn.wp.pri.project_manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.core.commons.framework.support.controller.BaseController;
import cn.yjhl.commons.tools.ApplicationTools;

@Controller("pm.htmlController")
@RequestMapping("html")
public class HtmlController extends BaseController{
	private static Map<String,String> map = new HashMap<String,String>();
	
	static{
		// 首页
		map.put("index","index");
		// 项目列表
		map.put("001_001_001", "project_view");
		// 项目添加
		map.put("001_002_001", "project_edit");
		// bug列表
		map.put("002_001_001", "matter");
		// bug编辑
		map.put("002_002_001", "matter_edit");
		map.put("002_002_002", "matter_view");
		// api列表
		map.put("003_001_001", "api");
		// api编辑
		map.put("003_002_001", "api_edit");
		
	}
	
	@RequestMapping("{code}.html")
	public ModelAndView htmlView(@PathVariable("code")String code){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(ApplicationTools.getValue("config.mappings.mapping[code='"+code+"'].ftl").toString());
		return mav;
	}
	
}
