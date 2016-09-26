package com.sh.util;

import java.io.Serializable;

public class ResultData implements Serializable{
	private int status;//0表示参数异常,1表示正常，2表示其他提示信息
	private String message;//信息
	private Object result;//数据结果
	public int getStatus() {
		return status;
	}
	/**
	 * 
	 * @param status //0表示参数异常,1表示正常，2表示其他提示信息
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
