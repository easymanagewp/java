package cn.wp.commons.component.filter.imp;

import cn.wp.commons.component.DefaultNamedCls;
import cn.wp.commons.component.filter.Filter;
import cn.wp.commons.component.filter.FilterContext;



/**
 * 过滤器，在执行某些任务之前，过滤器将对数据进行必要的处理
 * @author 王鹏
 */
public class DefaultFilter extends DefaultNamedCls implements Filter{

	public DefaultFilter(String name){
		super(name);
	}
	
	public boolean before(FilterContext context) throws Exception{
		return true;
	}

	public boolean after(FilterContext context, Exception exception) {
		return true;
	}
}
