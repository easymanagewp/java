package cn.wp.commons.wp_tools.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 字符串操作工具类
 * @author 王鹏
 *
 */
public class StringTools {

	/**
	 * 判断字符串为否为空或者null
	 * @param str
	 * @return 返回判断结果
	 */
	public static Boolean isBlank(String str){
		return (str == null || "".equals(str.trim()));
	}
	
	/**
	 * 判断字符串非空并且非null
	 * @param str
	 * @return 返回判断结果
	 */
	public static Boolean isNotBlank(String str){
		return !(StringTools.isBlank(str));
	}
	
	/**
	 * 判断字符串数组中是否包含任意一个空字符串
	 * @param strs
	 * @return 返回判断结果
	 */
	public static Boolean isNotAnyNullAndBlank(String[] strs){
		Boolean isOk = Boolean.TRUE;
		for(String str : strs){
			if(StringTools.isBlank(str)){
				isOk = Boolean.FALSE;
			}
		}
		return isOk;
	}
	
	/**
	 * 产出字符串中的最后一个字符
	 * @param cs
	 * @return 返回判断结果
	 */
	public static String deleteLastChar(CharSequence cs){		
		StringBuffer sb = new StringBuffer(cs);
		StringTools.deleteLastChar(sb);
		return sb.toString();
	}
	
	/**
	 * 删除StringBuffer中的最后一个字符
	 * @param sb
	 */
	public static void deleteLastChar(StringBuffer sb){
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	/**
	 * 转换字符串首字母为小写
	 * @param str
	 * @return
	 */
	public static String toLowerCaseOfFirst(String str){
		str = str.substring(0,1).toLowerCase()+str.substring(1);
		return str;
	}
	
	/**
	 * 删除StringBuilder中的最后一个字符
	 * @param sb
	 */
	public static void deleteLastChar(StringBuilder sb){
		if(sb.length()>0){
			sb.deleteCharAt(sb.length()-1);
		}
	}
	
	/**
	 * MD5加密
	 * @param str
	 * @return 返回加密结果
	 */
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
			return str;
		}
	}
}
