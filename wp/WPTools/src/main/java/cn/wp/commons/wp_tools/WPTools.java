package cn.wp.commons.wp_tools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.log4j.Logger;

import cn.wp.commons.wp_tools.exception.WPToolsExecuteException;
import cn.wp.commons.wp_tools.json.JsonTools;

/**
 * 提供统一的工具调用入口
 * @author wangpeng
 *
 */
@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
public class WPTools {
	private static Logger log = Logger.getLogger(ClassUtils.class.getName());
	
	private static final ClassLoader classLoader = WPTools.class.getClassLoader();
	/**
	 * 工具类类型：static
	 */
	private static final String METHOD_TYPE_STATIC = "static";
	/**
	 * 工具类类型：实例化
	 */
	private static final String METHOD_TYPE_INSTALL = "install";
	
	/**
	 * 方法库文件名称
	 */
	private static final String ALIAS_TOOLS_JSON_FILE = "alias.tools.json";
	/**
	 * 参数库文件名称
	 */
	private static final String PARAMS_TYPES_JSON_FILE = "params.types.json";
	
	/**
	 * 实例化目标类缓存
	 */
	private static final MapCache<String,Object> installTargetCache = new MapCache<String, Object>("install.target.cache",new HashMap<String,Object>());
	/**
	 * 加载类文件目标缓存
	 */
	private static final MapCache<String,Class> classTargetCache = new MapCache<String, Class>("class.target.cache",new HashMap<String,Class>());
	/**
	 * 参数类型缓存
	 */
	private static final MapCache<String,Class[]> paramsTypes = new MapCache<String, Class[]>("params.types.cache",new HashMap<String,Class[]>());
	/**
	 * 方法库缓存
	 */
	private static Map<String,String> aliasNames = null;
	
	static{
		aliasNames = JsonTools.getJSONByFile(ALIAS_TOOLS_JSON_FILE, Map.class);
		
		Map<String,List<String>> paramsMap = JsonTools.getJSONByFile(PARAMS_TYPES_JSON_FILE, Map.class);
		for(Map.Entry<String, List<String>> params : paramsMap.entrySet()){
			String key = params.getKey();
			List<String> tempValues = params.getValue();
			Class[] value = new Class[tempValues.size()];
			for(int i=0;i<tempValues.size();i++){
				String tempValue = tempValues.get(i);
				Class valueCls = null;
				try {
					valueCls = classLoader.loadClass(tempValue);
				} catch (ClassNotFoundException e) {
					throw new WPToolsExecuteException("配置文件加载失败",e);
				}
				value[i] = valueCls;
			}
			paramsTypes.put(key, value);
		}
	}
	
	/**
	 * 执行指定的工具方法，参数默认采用基本数据类型
	 * @param toolUri 工具类uri
	 * @param resultType 执行结果的预期类型
	 * @param params 参数列表
	 * @return
	 */
	public static final <ResultType>ResultType execute(String toolUri,Class<ResultType> resultType,Object... params){
		return execute(toolUri, resultType, true, params);
	}
	
	/**
	 * 执行指定工具方法
	 * @param toolUri 工具类uri
	 * @param resultType 执行结果的预期类型
	 * @param isPrimitiveParams 参数是否为基本数据类型
	 * @param params 参数列表
	 * @return 工具方法的实际返回结果
	 */
	public static final <ResultType>ResultType execute(String toolUri,Class<ResultType> resultType,Boolean isPrimitiveParams ,Object... params){
		// 获取完整uri
		if(null != aliasNames.get(toolUri)){
			toolUri = aliasNames.get(toolUri);
		}
		
		// 获取method签名信息
		String methodType = getMethodType(toolUri);
		String clazzName = getClass(toolUri);
		String methodName = getMethodName(toolUri);
		Class[] parameterTypes = getMethodTypes(params,isPrimitiveParams);
		String paramsUri = "";
		for(Class paramType : parameterTypes){
			paramsUri += "."+paramType.getSimpleName();
		}
		
		if(paramsTypes.get(toolUri+paramsUri)!=null){
			parameterTypes = paramsTypes.get(toolUri+paramsUri); 
		}
		
		// 获取工具类Class
		Class executeClass = null;
		try {
			executeClass = classTargetCache.get(clazzName);
			if(null == executeClass){
				executeClass = classLoader.loadClass(clazzName);
				classTargetCache.put(clazzName, executeClass);
			}
		} catch (ClassNotFoundException e) {
			throw new WPToolsExecuteException("错误的uri，无法追踪到指定工具类",e);
		}
		
		// 获取目标对象实例
		Object targetObj = null;
		getInstall : if(METHOD_TYPE_INSTALL.equals(methodType)){
			targetObj = installTargetCache.get(clazzName);
			if(targetObj != null)
				break getInstall;
			
			try {
				targetObj = executeClass.newInstance();
				installTargetCache.put(clazzName, targetObj);
			} catch (InstantiationException e) {
				throw new WPToolsExecuteException("执行工具类实例化失败",e);
			} catch (IllegalAccessException e) {
				throw new WPToolsExecuteException(e);
			}
		}
		
		Method executeMethod = getMethod(methodName, parameterTypes,
				executeClass);
		
		// 执行method
		Object result = null;
		try {
			result = executeMethod.invoke(targetObj, params);
		} catch (IllegalAccessException e) {
			throw new WPToolsExecuteException("",e);
		} catch (InvocationTargetException e) {
			throw new WPToolsExecuteException("执行目标不存在，无法执行工具方法",e);
		}
		
		// 获取返回值
		if(null == result)
			return null;
		
		return (ResultType) result;
	}

	/**
	 * 根据方法名称和参数类型，获取执行方法对象
	 * @param methodName 方法名称
	 * @param parameterTypes 参数类型
	 * @param executeClass 执行的类
	 * @return 需要执行的方法
	 */
	private static Method getMethod(String methodName, Class[] parameterTypes,
			Class executeClass) {
		// 获取method
		Method executeMethod = null;
		try {
			executeMethod = ClassUtils.getPublicMethod(executeClass, methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new WPToolsExecuteException("错误的uri，无法追踪到指定的工具方法",e);
		} catch (SecurityException e) {
			throw new WPToolsExecuteException("错误的uri,无权限访问指定的工具方法",e);
		}
		return executeMethod;
	}
	
	/*
	 * 从uri获取方法类型，默认为static
	 */
	private static String getMethodType(String toolUri) {
		if(toolUri.startsWith(METHOD_TYPE_INSTALL)){
			return METHOD_TYPE_INSTALL;
		}else{
			return METHOD_TYPE_STATIC;
		}
	}
	
	/**
	 * 获取参数类型列表
	 * @param params 参数列表
	 * @param isPrimitiveParams 是否为基本数据类型
	 * @return 请求参数类型列表
	 */
	private static Class[] getMethodTypes(Object[] params,Boolean isPrimitiveParams) {
		if( null == params || 0 == params.length )
			return null;
		
		Class[] clazzs = new Class[params.length];
		for(int index = 0;index < params.length;index++){
			Object param = params[index];
			if( null == param){
				clazzs[index] = null;
				continue;
			}
			
			clazzs[index] = param.getClass();
		}
		if(isPrimitiveParams){
			for(int i=0;i<clazzs.length;i++){
				Class clazz = ClassUtils.wrapperToPrimitive(clazzs[i]);
				if(clazz!=null){
					clazzs[i] = clazz;
				}
			}
		}
		else{
			for(int i=0;i<clazzs.length;i++){
				Class clazz = ClassUtils.primitiveToWrapper(clazzs[i]);
				if(clazz!=null){
					clazzs[i] = clazz;
				}
			}
		}
		return clazzs;
	}

	/**
	 * 从工具uri获取工具类类名
	 * @param toolUri
	 * @return
	 */
	private static final String getClass(String toolUri){
		String[] toolUriPart = toolUri.split(":");
		
		String _tooluri = toolUriPart[0];
		if(toolUriPart.length > 1){
			_tooluri = toolUriPart[1];
		}
		String classUri = _tooluri.substring(0, _tooluri.lastIndexOf("."));
		
		return classUri;
	}
	
	/**
	 * 从工具uri获取方法调用方法名称
	 * @param toolUri
	 * @return
	 */
	private static final String getMethodName(String toolUri){
		String[] toolUriPart = toolUri.split(":");
		
		String _tooluri = toolUriPart[0];
		if(toolUriPart.length > 1){
			_tooluri = toolUriPart[1];
		}
		
		String methodName = _tooluri.substring( _tooluri.lastIndexOf(".")+1);
		return methodName;
	}
	
}
