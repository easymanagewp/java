package cn.wp.commons.component.filter.exception;

public class FilterChainExistException extends RuntimeException {
	private static final long serialVersionUID = -5480189873189437368L;

	public FilterChainExistException() {
		super();
	}

	public FilterChainExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FilterChainExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public FilterChainExistException(String message) {
		super(message);
	}

	public FilterChainExistException(Throwable cause) {
		super(cause);
	}

	
}
