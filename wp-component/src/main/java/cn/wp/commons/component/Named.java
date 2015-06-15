package cn.wp.commons.component;

/**
 * 提供一个可命名的接口组件，实现该接口的对象必须为对象设定一个名字，这个名字可作为identity使用
 * @author 王鹏
 */
public interface Named {
	/**
	 * 获取对象名称
	 * @return 对象名称
	 */
	String getName();
	
	/**
	 * 设置对象名称
	 * @param name 对象名称
	 */
	void setName(String name);
}
