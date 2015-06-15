package cn.wp.commons.wp_tools.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import cn.wp.commons.wp_tools.exception.WPToolsExecuteException;

import com.alibaba.fastjson.JSON;

public class JsonTools {
	
	@SuppressWarnings("unchecked")
	public static final String getJSONTextByFile(String filePath){
		StringBuffer sbf = new StringBuffer();
		InputStream  is = JsonTools.class.getClassLoader().getResourceAsStream(filePath);
		try {
			List<String> lines = IOUtils.readLines(is);
			for(String line : lines){
				sbf.append(line.trim());
			}
		} catch (IOException e) {
			throw new WPToolsExecuteException("配置文件读取失败",e);
		}
		return sbf.toString();
	}
	
	public static final <ResultType>ResultType getJSONByFile(String filePath,Class<ResultType> resultTypeCls){
		String jsonText = getJSONTextByFile(filePath);
		return JSON.parseObject(jsonText,resultTypeCls);
	}
	
}
