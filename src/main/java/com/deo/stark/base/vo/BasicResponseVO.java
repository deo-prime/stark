package com.deo.stark.base.vo;

public class BasicResponseVO<T> {

	private boolean success = false;
	private String message = "";
	private String code;
	private T data;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BasicResponseVO(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public BasicResponseVO(boolean success, String message, T data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
