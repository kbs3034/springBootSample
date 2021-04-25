package com.bskim.co.usr.lgi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bskim.co.usr.common.dto.User;
import com.bskim.co.usr.lgi.dao.LoginDao;
import com.bskim.common.annotaion.TranID;
import com.bskim.common.exception.BizException;
import com.bskim.common.session.SessionObject;
import com.bskim.common.session.SessionUtils;
import com.bskim.common.utils.IObjectUtils;

@Service
public class USRLGI001 {
	@Autowired
	private SessionUtils sessionUtils;
	@Autowired
	private LoginDao LoginDao;
	
	
	@TranID("USRLGI00101R")
	public User login (User user) throws BizException {
		User result = LoginDao.selectUserInfo(user);
		
		if(result != null) {
			SessionObject sessionObject = new SessionObject();
			
			sessionObject.setCustNo(result.getCustNo());
			sessionObject.setNickName(result.getNickName());
			sessionObject.setUserId(result.getUserId());
			sessionObject.setUserName(result.getUserName());
			
			sessionUtils.creatSession(sessionObject);
			
			result = (User) IObjectUtils.convertObjectToObject(sessionObject, User.class);
		}else {
			throw new BizException("로그인실패");
		}
		
		return result; 
	}
	
	@TranID("USRLGI00102R")
	public SessionObject sessionCheck() {
		return sessionUtils.getSessionObject();
	}
		
}
