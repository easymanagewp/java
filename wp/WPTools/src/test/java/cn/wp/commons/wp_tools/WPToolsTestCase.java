package cn.wp.commons.wp_tools;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;

public class WPToolsTestCase {
	
	@Test
	public void WPToolsTestCase(){
		System.out.println("====创建随机指定长度的字符串=====");
		System.out.println(WPTools.execute("string:get:random", String.class, 11,"abcdefghigklmnopqrstuvwxyz"));
		System.out.println("=====MD5加密=====");
		System.out.println(WPTools.execute("string:codec:md5", String.class, "222222"));
		System.out.println("====自动生成toString的内容====");
		System.out.println(ReflectionToStringBuilder.toString(this));
		
		
	}
	
}
