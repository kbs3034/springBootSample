package com.bskim.common.dto;

import java.util.Map;

public class Result {
	private String userId;
	private String resultMessage;
	private String statusCode;
	
	private Map<String,Object> data;

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
