package com.ljw.ssm.util;

/**
 * ������Ŀ��ajax���󷵻صĽ��
 * @author ����BUG(LJW)
 *
 */
public class ResultEntity<T> {
	public static final String SUCCESS="SUCCESS";
	public static final String FAILED="FAILED";
	
	// ��װ���صĽ��
	private String result;
	
	// ʧ�ܵķ���ֵ
	private String message;
	
	// ���ص�����
	private T data;
	
	
	// ����ɹ�����Ҫ���ݵĹ��߷���
	public static <Type> ResultEntity<Type> successWithoutData(){
		
		return new ResultEntity<Type>(SUCCESS, null, null);
	}
	// ����ɹ���Ҫ�������ݵĹ��߷�����
	public static <Type> ResultEntity<Type> successWithData(Type data){
		return new ResultEntity<Type>(SUCCESS, null, data);
	}
	// ����ʧ�ܷ�����Ϣ
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
