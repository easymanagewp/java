package cn.wp.pri.project_manager.service;

import org.springframework.transaction.annotation.Transactional;

import cn.core.commons.framework.support.service.Service;
import cn.wp.pri.project_manager.entity.Matter;

@Transactional
public interface MatterService extends Service<Matter> {

	
}
