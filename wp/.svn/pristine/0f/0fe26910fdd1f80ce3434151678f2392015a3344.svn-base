package cn.wp.pri.project_manager.entity;

import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.core.commons.framework.support.entity.Po;
import cn.yjhl.commons.tools.json.DateFormat;

@Entity(name="pm.matter")
@Table(name="pm_matter")
public class Matter extends Po {
	private static final long serialVersionUID = -8685576385254790977L;
	//transformation
	public static final String STATUS_CREATE = "创建";
	public static final String STATUS_PROGRESS = "处理中";
	public static final String STATUS_COMPLETE = "完成";
	
	private String projectCode;	// 问题所属项目
	private String status;		// 问题状态
	private Integer code;		// 问题编码
	private String title;		// 问题标题
	private Long progressTime;		// 问题开始处理时间 
	private Long completeTime;		// 问题处理时间
	private String remark;		// 问题描述
	
	@Transient
	public String getCompassId() {
		return this.getId();
	}
	
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:25:28 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取问题状态
	 * </div>
	 * @return 问题状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:25:44 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置问题状态
	 * </div>
	 * @param status 问题状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:25:55 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取问题编号
	 * </div>
	 * @return 问题编号
	 */
	@OrderBy(value="desc")
	public Integer getCode() {
		return code;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:26:06 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置问题编号
	 * </div>
	 * @param code 问题编号
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:26:18 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取问题简介
	 * </div>
	 * @return 问题简介
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:26:33 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置问题简介
	 * </div>
	 * @param title 问题简介
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:26:42 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取开始处理时间
	 * </div>
	 * @return 开始处理时间
	 */
	@DateFormat
	public Long getProgressTime() {
		return progressTime;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:27:02 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置开始处理时间
	 * </div>
	 * @param progressTime 开始处理时间
	 */
	public void setProgressTime(Long progressTime) {
		this.progressTime = progressTime;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:27:17 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取处理结束时间
	 * </div>
	 * @return 处理结束时间
	 */
	@DateFormat
	public Long getCompleteTime() {
		return completeTime;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:27:29 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置处理结束时间
	 * </div>
	 * @param completeTime 处理结束时间
	 */
	public void setCompleteTime(Long completeTime) {
		this.completeTime = completeTime;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:27:40 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取问题描述
	 * </div>
	 * @return 问题描述
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月22日 下午8:27:51 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置问题描述
	 * </div>
	 * @param remark 问题描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月25日 下午3:52:31 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 获取问题所属项目
	 * </div>
	 * @return 问题所属项目
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * <strong>author : 王鹏 </strong> <br>
	 * <strong>Create on : 2015年5月25日 下午3:52:42 </strong> <br>
	 * <strong>Description : </strong> <br>
	 * <div>
	 * 设置问题所属项目
	 * </div>
	 * @param projectCode 问题所属项目
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	
	
	
}
