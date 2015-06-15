package cn.wp.commons.component.filter;


public interface FilterContext {

	void put(String key,Object value);
	
	void remove(String key);
	
	Object get(String key);
}