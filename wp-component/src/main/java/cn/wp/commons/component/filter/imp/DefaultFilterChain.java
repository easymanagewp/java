package cn.wp.commons.component.filter.imp;

import java.util.ArrayList;
import java.util.List;

import cn.wp.commons.component.DefaultNamedCls;
import cn.wp.commons.component.filter.Filter;
import cn.wp.commons.component.filter.FilterChain;
import cn.wp.commons.component.filter.FilterContext;
import cn.wp.commons.component.filter.exception.FilterExistException;
import cn.wp.commons.wp_tools.WPTools;

/**
 * Filter管理器，管理filter的执行与配置上下文环境
 * 
 * @author 王鹏
 */
public class DefaultFilterChain extends DefaultNamedCls implements FilterChain{

	private List<Filter> chains = new ArrayList<Filter>();
	
	public DefaultFilterChain(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see cn.wp.commons.component.fileter.FilterChain#addFilter(cn.wp.commons.component.fileter.DefaultFilter)
	 */
	public void addFilter(DefaultFilter filter) {
		if (WPTools.execute("object:confirm:isNotNull", Boolean.class,
				getFilter(filter.getName()))) {
			throw new FilterExistException();
		}

		chains.add(filter);
	}

	/* (non-Javadoc)
	 * @see cn.wp.commons.component.fileter.FilterChain#getFilter(java.lang.String)
	 */
	public Filter getFilter(String name) {
		return WPTools.execute("object:context:jxpath:getValue",
				DefaultFilter.class, ".[name='" + name + "']");
	}

	public boolean before(FilterContext context) throws Exception{
		for(int index =0;index<chains.size();index++){
			if(!chains.get(index).before(context)){
				return false;
			}
		}
		
		return true;
	}

	public boolean after(FilterContext context, Exception e) throws Exception {
		for(int index = chains.size()-1;index>=0;index--){
			if(!chains.get(index).after(context, e)){
				return false;
			}
		}
		return true;
	}

	public void addFilter(Filter filter) {
		chains.add(filter);
	}

}
