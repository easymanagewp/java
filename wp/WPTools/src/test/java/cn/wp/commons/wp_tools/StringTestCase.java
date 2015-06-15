package cn.wp.commons.wp_tools;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class StringTestCase {
	
	@Test
	public void StringIsBlankCase(){
		Assert.assertTrue(WPTools.execute("string:confirm:blank", Boolean.class, ""));
	}
	
	@Test
	public void StringIsNotBlankCase(){
		Assert.assertFalse(WPTools.execute("string:confirm:notBlank", Boolean.class, ""));
	}
	
	@Test
	public void StringBase64Case(){
		String encodeString = "王鹏";
		String decodeString = "";
		
		decodeString = WPTools.execute("string:codec:base64:encode",String.class, encodeString);
		
		Assert.assertEquals(encodeString, WPTools.execute("string:codec:base64:decode", String.class, decodeString));
	}
	
	
	@Test
	public void test(){
		System.out.println(WPTools.execute("json:load:object", Map.class, "alias.tools.json",Map.class));
	}
}
