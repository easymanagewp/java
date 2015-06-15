package cn.wp.commons.framework.persistence;

import cn.wp.commons.component.filter.Filter;

public interface Updateable extends FilterablePersistenceManager{
	static String UPDATE_CHAIN_NAME = "update.filter.chains"; 
	
	void update(Object... objs);
	
	default void addUpdateFilter(Filter filter){
		getFilterChainManager().getFilterChain(UPDATE_CHAIN_NAME).addFilter(filter);
	}
}
