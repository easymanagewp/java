package cn.wp.commons.framework.persistence;

import cn.wp.commons.component.filter.Filter;

public interface Saveable extends FilterablePersistenceManager {
	static String SAVE_CHAIN_NAME = "save.filter.chains"; 
	
	void save(Object... objs);
	
	default void addSaveFilter(Filter filter){
		getFilterChainManager().getFilterChain(SAVE_CHAIN_NAME).addFilter(filter);
	}
}
