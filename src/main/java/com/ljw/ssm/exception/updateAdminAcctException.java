package com.ljw.ssm.exception;

/**
 * 更新Admin时检测账号重复抛出异常
 * @author 永无BUG(LJW)
 *
 */
public class updateAdminAcctException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public updateAdminAcctException() {
		super();
	}

	public updateAdminAcctException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public updateAdminAcctException(String message, Throwable cause) {
		super(message, cause);
	}

	public updateAdminAcctException(String message) {
		super(message);
	}

	public updateAdminAcctException(Throwable cause) {
		super(cause);
	}
	

}
