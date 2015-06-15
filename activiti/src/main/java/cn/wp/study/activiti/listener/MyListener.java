package cn.wp.study.activiti.listener;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;

public class MyListener implements ActivitiEventListener {

	public void onEvent(ActivitiEvent event) {
		switch (event.getType()) {

		case JOB_EXECUTION_SUCCESS:
			System.out.println("A job well done!");
			break;

		case JOB_EXECUTION_FAILURE:
			System.out.println("A job has failed...");
			break;

		default:
			System.out.println("Event received: " + event.getType());
		}
	}
	
	/**
	 * 此决定事件分发时，onEvent方法抛出异常时的行为，这里返回false，会忽略异常，当返回true时，异常将不会被忽略，继续向上传播，迅速导致当前命令失败。
	 * 当事件是一个API调用的一部分时（或其他事务性的操作，如job执行），事务就会回滚。
	 * 当事件监听中的行为不是业务性时，建议返回false
	 */
	public boolean isFailOnException() {

		return false;
	}

}
