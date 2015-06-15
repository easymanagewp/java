package cn.wp.commons.component.filter;

import java.lang.reflect.Method;

import junit.framework.Assert;

import org.junit.Test;

import cn.wp.commons.component.filter.FilterContext;
import cn.wp.commons.component.filter.imp.DefaultFilterContext;

public class DefaultMethodFilterContextTestCase {
	
	public static class SayHello {
		public void saveHello(String hello){
			System.out.println(hello);
		}
	}
	
	private static Method method = null;
	private static SayHello obj = new SayHello();
	private static Object[] params = null;
	static {
		try {
			method = obj.getClass().getMethod("sayHello", String.class);
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}

		params = new Object[] { "hello" };
	}

	private FilterContext context = new DefaultFilterContext();
	{
		context.setMethod(method);
		context.setObject(obj);
		context.setParams(params);
	}

	@Test
	public void getMethodTest() {
		Method _method = context.getMethod();
		Assert.assertEquals(_method, method);
	}

	@Test
	public void getObjectTest() {
		Object _object = context.getObject();
		Assert.assertEquals(_object, obj);
	}

	@Test
	public void getParamsTest() {
		Object[] _params = context.getParams();
		Assert.assertEquals(params.length, _params.length);
		for (int index = 0; index < params.length; index++) {
			Assert.assertEquals(_params[index], params[index]);
		}
	}
}
