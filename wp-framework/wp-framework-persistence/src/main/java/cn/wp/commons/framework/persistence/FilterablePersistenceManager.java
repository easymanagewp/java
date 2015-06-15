package cn.wp.commons.framework.persistence;

import cn.wp.commons.component.filter.FilterChainManager;

public interface FilterablePersistenceManager extends FilterChainManager{
	void setFilterChainManager(FilterChainManager filterChainManager);
	
	FilterChainManager getFilterChainManager();
}
