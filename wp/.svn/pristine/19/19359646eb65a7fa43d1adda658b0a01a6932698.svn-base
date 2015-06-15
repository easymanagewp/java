package cn.wp.pri.project_manager.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.core.commons.framework.support.container.RespContainer;
import cn.core.commons.framework.support.controller.BaseController;
import cn.core.commons.framework.support.dao.container.QueryContainer;
import cn.core.commons.framework.support.dao.container.ResultContainer;
import cn.core.commons.framework.support.exception.EntityExistedException;
import cn.core.commons.framework.support.exception.EntityNotFindException;
import cn.core.commons.framework.support.exception.IdNotFindException;
import cn.core.commons.framework.utils.CacheingLogable;
import cn.wp.pri.project_manager.entity.Matter;
import cn.wp.pri.project_manager.service.MatterService;
import cn.yjhl.commons.tools.StringTools;

@Controller("pm.matterController")
@RequestMapping("matter")
public class MatterController extends BaseController implements CacheingLogable{
	
	@Autowired private MatterService matterService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer matters(Matter matter,QueryContainer qc,HttpServletRequest request){
		RespContainer resp = new RespContainer();
		try {
			String current_project = request.getSession().getAttribute("current_project").toString();
			matter.setProjectCode(current_project);
			ResultContainer matters = matterService.paging(matter,qc);
			resp.setRes(matters);
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
			Matter matter = matterService.findById(id);
			resp.setRes(matter);
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
	public RespContainer add(Matter matter,HttpServletRequest request){
		RespContainer resp = new RespContainer();
		try {
			String currentProject = request.getSession().getAttribute("current_project").toString();
			matter.setProjectCode(currentProject);
			if(StringTools.isBlankOrNull(currentProject)){
				resp.setStatus(RespContainer.FAIL);
				resp.setMsg("请选择项目");
				return resp;
			}
			matterService.save(matter);
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
	public RespContainer update(Matter matter){
		RespContainer resp = new RespContainer();
		try {
			matterService.update(matter);
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
	
	@RequestMapping(value="transformation",method=RequestMethod.PUT,params={"status=处理中"})
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer transformationProgress(Matter matter){
		matter.setStatus(Matter.STATUS_PROGRESS);
		matter.setProgressTime(new Date().getTime());
		return this.update(matter);
	}
	
	@RequestMapping(value="transformation",method=RequestMethod.PUT,params={"status=完成"})
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer transformationComplete(Matter matter){
		matter.setStatus(Matter.STATUS_COMPLETE);
		matter.setCompleteTime(new Date().getTime());
		return this.update(matter);
	}
	
	@RequestMapping(value="{id}.do",method=RequestMethod.DELETE)
	@ModelAttribute(DEFAULT_ATTR_NAME)
	public RespContainer delete(@PathVariable("id")String id){
		RespContainer resp = new RespContainer();
		try {
			matterService.deleteById(id);
			resp.setStatus(RespContainer.SUCCESS);
		} catch (Exception e) {
			resp.setMsg("删除失败，系统异常");
		}
		return resp;
	}
	
}
