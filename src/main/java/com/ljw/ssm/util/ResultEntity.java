package com.ljw.ssm.util;

/**
 * 整个项目中ajax请求返回的结果
 * @author 永无BUG(LJW)
 *
 */
public class ResultEntity<T> {
	public static final String SUCCESS="SUCCESS";
	public static final String FAILED="FAILED";
	
	// 封装返回的结果
	private String result;
	
	// 失败的返回值
	private String message;
	
	// 返回的数据
	private T data;
	
	
	// 请求成功不需要数据的工具方法
	public static <Type> ResultEntity<Type> successWithoutData(){
		
		return new ResultEntity<Type>(SUCCESS, null, null);
	}
	// 请求成功需要返回数据的工具方法、
	public static <Type> ResultEntity<Type> successWithData(Type data){
		return new ResultEntity<Type>(SUCCESS, null, data);
	}
	// 请求失败返回信息
	public static<Type> ResultEntity<Type> failed(String message){
		return new ResultEntity<Type>(FAILED, message, null);
	}

	public ResultEntity(String result, String message, T data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public ResultEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	@Override
	public String toString() {
		return "ResultEntity [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
	

}
