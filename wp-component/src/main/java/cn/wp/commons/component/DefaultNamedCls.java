package cn.wp.commons.component;

/**
 * 默认的可命名对象，该对象实现了Named方法，其他对象可直接继承该对象为对象赋予可命名属性
 * @author 王鹏
 */
public class DefaultNamedCls implements Named {
	
	private String name;
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DefaultNamedCls(String name){
		this.name = name;
	}
}
