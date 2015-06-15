package cn.wp.commons.component.filter;

public interface FilterChain {

	/**
	 * 添加Filter
	 */
	public abstract void addFilter(Filter filter);

	/**
	 * 根据名称获取filter
	 * @param name
	 * @return
	 */
	public abstract Filter getFilter(String name);
	
	/**
	 * 开始执行拦截器链
	 * @param context 上下文环境
	 * @return true，继续执行下一条信息
	 * @throws Exception
	 */
	boolean before(FilterContext context) throws Exception;
	
	/**
	 * 回调连接器链
	 * @param context
	 * @param e
	 * @return
	 * @throws Exception
	 */
	boolean after(FilterContext context,Exception e) throws Exception;

}