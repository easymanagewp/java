package cn.wp.pri.project_manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.core.commons.framework.support.container.RespContainer;
import cn.core.commons.framework.support.controller.BaseController;
import cn.core.commons.framework.support.exception.EntityExistedException;
import cn.core.commons.framework.support.exception.EntityNotFindException;
import cn.core.commons.framework.support.exception.IdNotFindException;
import cn.core.commons.framework.utils.CacheingLogable;
import cn.wp.pri.project_manager.entity.Project;
import cn.wp.pri.project_manager.service.ProjectService;

@Controller("pm.projectController")
@RequestMapping("project")
public class ProjectController extends BaseController implements CacheingLogable{
	
	@Autowired private ProjectService projectService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer projects(){
		RespContainer resp = new RespContainer();
		try {
			List<Project> projects = projectService.list();
			resp.setRes(projects);
			resp.setStatus(RespContainer.SUCCESS);
		} catch (EntityNotFindException e) {
			resp.setStatus(RespContainer.EMPTY);
			resp.setMsg("查询结果为空");
		} catch (Exception e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("系统异常");
			getLog().error("",e);
		}
		return resp;
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer findById(@PathVariable("id")String id){
		RespContainer resp = new RespContainer();
		try {
			Project project = projectService.findById(id);
			resp.setRes(project);
			resp.setStatus(RespContainer.SUCCESS);
		} catch (IdNotFindException e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("id必须存在");
		} catch (EntityNotFindException e) {
			resp.setStatus(RespContainer.EMPTY);
			resp.setMsg("查询结果为空");
		} catch (Exception e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("系统异常");
			getLog().error("",e);
		}
		return resp;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer add(Project project){
		RespContainer resp = new RespContainer();
		try {
			projectService.save(project);
			resp.setStatus(RespContainer.SUCCESS);
		} catch (EntityExistedException e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("该实体已经存在");
		} catch (Exception e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("系统异常");
			getLog().error("",e);
		}
		return resp;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer update(Project project){
		RespContainer resp = new RespContainer();
		try {
			projectService.update(project);
			resp.setStatus(RespContainer.SUCCESS);
		} catch (EntityExistedException e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("该实体已经存在");
		} catch (Exception e) {
			resp.setStatus(RespContainer.FAIL);
			resp.setMsg("系统异常");
			getLog().error("",e);
		}
		return resp;
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="current_project")
	public void currentProject(@RequestParam("code")String code,HttpServletRequest request){
		request.getSession().setAttribute("current_project", code);
	}
	
}
