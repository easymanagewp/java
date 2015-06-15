package cn.wp.commons.component.filter;

import cn.wp.commons.component.filter.imp.DefaultFilterChain;

public interface FilterChainManager {

	public abstract void addFilterChain(DefaultFilterChain filterChain);

	public abstract FilterChain getFilterChain(String name);

}