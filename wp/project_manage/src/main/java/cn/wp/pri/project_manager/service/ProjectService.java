package cn.wp.pri.project_manager.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.commons.framework.support.service.Service;
import cn.wp.pri.project_manager.entity.Project;

@Transactional
public interface ProjectService extends Service<Project> {

	
}
