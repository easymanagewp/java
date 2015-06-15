package cn.wp.commons.wp_tools.exception;

public class WPToolsExecuteException extends RuntimeException {
	private static final long serialVersionUID = -3665320848310488982L;

	public WPToolsExecuteException() {
		super();
	}

	public WPToolsExecuteException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public WPToolsExecuteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public WPToolsExecuteException(String arg0) {
		super(arg0);
	}

	public WPToolsExecuteException(Throwable arg0) {
		super(arg0);
	}

	
}
