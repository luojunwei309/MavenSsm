package com.ljw.ssm.exception;

/**
 * 保存Admin失败出现的异常
 * @author 永无BUG(LJW)
 *
 */
public class InsertAdminException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InsertAdminException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertAdminException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InsertAdminException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsertAdminException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsertAdminException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
