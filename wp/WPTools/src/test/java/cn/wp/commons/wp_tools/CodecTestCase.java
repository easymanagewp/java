package cn.wp.commons.wp_tools;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class CodecTestCase {
	
	@Test
	public void md5Test(){
		String md5EncodeStr = WPTools.execute("string:codec:md5", String.class, "测试加密");
		System.out.println(md5EncodeStr);
	}
	
	@Test
	public void md5Hex(){
//		DigestUtils.sha256Hex();
	}
}
