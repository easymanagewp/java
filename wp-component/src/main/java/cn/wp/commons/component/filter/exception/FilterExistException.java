package cn.wp.commons.component.filter.exception;

public class FilterExistException extends RuntimeException {
	private static final long serialVersionUID = -5480189873189437368L;

	public FilterExistException() {
		super();
	}

	public FilterExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FilterExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public FilterExistException(String message) {
		super(message);
	}

	public FilterExistException(Throwable cause) {
		super(cause);
	}

	
}
