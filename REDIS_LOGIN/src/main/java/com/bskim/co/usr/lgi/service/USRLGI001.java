package com.bskim.co.usr.lgi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bskim.co.usr.common.dto.User;
import com.bskim.common.annotaion.TranID;
import com.bskim.common.session.SessionObject;
import com.bskim.common.session.SessionUtils;
import com.bskim.common.utils.IObjectUtils;

@Service
public class USRLGI001 {
	
	private SessionUtils sessionUtils;
	@Autowired
	public USRLGI001(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}
	
	@TranID("USRLGI00101R")
	public User login (User user) {
		User result = new User(); 
		
		if("test".equals(user.getUserId())) {
			SessionObject sessionObject = new SessionObject();
			sessionObject.setCustNo("123456789");
			sessionObject.setNickName("닉네임");
			sessionObject.setUserId(user.getUserId());
			sessionObject.setUserName("이름");
			
			sessionUtils.creatSession(sessionObject);
			
			result = (User) IObjectUtils.convertObjectToObject(sessionObject, User.class);
		}
		
		return result; 
	}
	
	@TranID("USRLGI00102R")
	public SessionObject sessionCheck() {
		return sessionUtils.getSessionObject();
	}
		
}
