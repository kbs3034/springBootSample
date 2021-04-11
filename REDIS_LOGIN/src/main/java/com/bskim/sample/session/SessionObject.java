package com.bskim.sample.session;

import java.io.Serializable;

public class SessionObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String userName;
	private String nickName;
	private String custNo;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	
	
}
