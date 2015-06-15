package cn.wp.pri.project_manager.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.core.commons.framework.support.dao.container.QueryContainer;
import cn.core.commons.framework.support.dao.container.ResultContainer;
import cn.core.commons.framework.support.exception.EntityExistedException;
import cn.core.commons.framework.support.exception.EntityIsNullException;
import cn.core.commons.framework.support.exception.EntityNotFindException;
import cn.core.commons.framework.support.exception.IdNotFindException;
import cn.core.commons.framework.support.service.AbstractService;
import cn.wp.pri.project_manager.entity.Matter;
import cn.wp.pri.project_manager.service.MatterService;
import cn.yjhl.commons.tools.StringTools;

@Service
public class MatterServiceImpl extends AbstractService<Matter> implements
MatterService {

	private static final Object lock = new Object();
	
	@Override
	public void save(Matter entity) throws EntityExistedException, Exception {
		synchronized (lock) {
			entity.setStatus(Matter.STATUS_CREATE);
			entity.setCode(getMatterCode());
			super.save(entity);
		}
	}
	
	/*
	 * 获取问题编码
	 */
	@SuppressWarnings("unchecked")
	private Integer getMatterCode() {
		QueryContainer qc = new QueryContainer();
		qc.addOrder("code", QueryContainer.OrderType.DESC);
		qc.setPageSize(1);
		try {
			ResultContainer rc = super.paging(null, qc);
			List<Matter> matters = rc.getResultList();
			return (matters.get(0).getCode()+1);
		} catch (EntityNotFindException e) {
			return 1;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	public ResultContainer paging(Matter entity, QueryContainer container)
			throws EntityNotFindException, Exception {
		if(StringTools.isNotBlankOrNull(entity.getStatus())){
			switch (entity.getStatus()) {
			case Matter.STATUS_CREATE:
				container.addCondition("status=:status");
				container.addParam("status", Matter.STATUS_CREATE);
				break;
			case Matter.STATUS_PROGRESS:
				container.addCondition("status=:status");
				container.addParam("status", Matter.STATUS_PROGRESS);
				break;
			case Matter.STATUS_COMPLETE:
				container.addCondition("status=:status");
				container.addParam("status", Matter.STATUS_COMPLETE);
				break;
			default:
				break;
			}
		}
		
		container.addOrder("INSTR('处理中,创建,完成',status)", QueryContainer.OrderType.ASC);
		
		container.addCondition("projectCode=:projectCode");
		container.addParam("projectCode", entity.getProjectCode());
		return super.paging(entity, container);
	}
	
	@Override
	public void update(Matter entity) throws EntityIsNullException,
			IdNotFindException, EntityNotFindException, Exception {
		if(StringTools.isBlankOrNull(entity.getStatus())){
			entity.setStatus(null);
		}
		super.update(entity);
	}
}
