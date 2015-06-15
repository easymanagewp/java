package cn.wp.commons.wp_tools.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.wp.commons.wp_tools.exception.WPToolsExecuteException;

public class MD5Tools {
	
	public static String md5(String str){
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte b[] = md.digest(); 
	
			int i; 
	
			StringBuffer buf = new StringBuffer(""); 
			for (int offset = 0; offset < b.length; offset++) { 
			i = b[offset]; 
			if(i<0) i+= 256; 
			if(i<16) 
			buf.append("0"); 
			buf.append(Integer.toHexString(i)); 
			} 
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {  
			throw new WPToolsExecuteException("md5加密失败",e);
		}
	}
	
}
