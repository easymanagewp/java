package cn.wp.commons.component.filter;

/**
 * 默认的拦截器接口
 * @author 王鹏
 */
public interface Filter {
	/**
	 * 在目标对象执行之前执行
	 * @param context 上下文环境
	 * @return
	 * @throws Exception
	 */
	boolean before(FilterContext context) throws Exception;
	
	/**
	 * 在目标对象执行之后执行
	 * @param context 上下文环境
	 * @param e 目标对象执行之后抛出的异常信息，可在方法内部消化，或者继续向上抛
	 * @return
	 */
	boolean after(FilterContext context,Exception e) throws Exception;
}