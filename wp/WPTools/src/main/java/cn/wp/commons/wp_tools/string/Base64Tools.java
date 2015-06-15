package cn.wp.commons.wp_tools.string;

/**
 * 
 * Base64 编码解码库
 *
 */
public class Base64Tools {
	/**
	 * Base64编码
	 * @param encodeStr
	 * @return
	 */
	public static final String encodeBase64(String encodeStr){
		byte[] encodeBytes = java.util.Base64.getEncoder().encode(encodeStr.getBytes());
		return new String(encodeBytes);
	}
	
	/**
	 * Base64解码
	 * @param decodeStr
	 * @return
	 */
	public static final String decodeBase64(String decodeStr){
		byte[] decodeBytes = java.util.Base64.getDecoder().decode(decodeStr);
		return new String(decodeBytes);
	}
}
