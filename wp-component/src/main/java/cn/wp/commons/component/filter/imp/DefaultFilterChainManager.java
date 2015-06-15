package cn.wp.commons.component.filter.imp;

import java.util.ArrayList;
import java.util.List;

import cn.wp.commons.component.filter.FilterChain;
import cn.wp.commons.component.filter.FilterChainManager;
import cn.wp.commons.component.filter.exception.FilterChainExistException;
import cn.wp.commons.wp_tools.WPTools;

public class DefaultFilterChainManager implements FilterChainManager {
	private List<DefaultFilterChain> filterChains = new ArrayList<DefaultFilterChain>();
	
	/* (non-Javadoc)
	 * @see cn.wp.commons.component.fileter.FilterChainManager#addFilterChain(cn.wp.commons.component.fileter.DefaultFilterChain)
	 */
	public void addFilterChain(DefaultFilterChain filterChain){
		if(WPTools.execute("object:confirm:isNull", Boolean.class, getFilterChain(filterChain.getName()))){
			throw new FilterChainExistException();
		}
		filterChains.add(filterChain);
	}
	
	/* (non-Javadoc)
	 * @see cn.wp.commons.component.fileter.FilterChainManager#getFilterChain(java.lang.String)
	 */
	public FilterChain getFilterChain(String name){
		return WPTools.execute("object:context:jxpath:getValue", DefaultFilterChain.class, ".[name='"+name+"']");
	}
}
