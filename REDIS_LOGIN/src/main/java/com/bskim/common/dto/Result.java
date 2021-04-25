package com.bskim.common.dto;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class Result {
	private String resultMessage;
	private HttpStatus statusCode;
	
	private Map<String,Object> data;
	
	public Result() {};
	public Result(HttpStatus statusCode, String resultMessage, Map<String,Object> data) {
		this.statusCode = statusCode;
		this.resultMessage = resultMessage;
		this.data = data;
	}
	
	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
