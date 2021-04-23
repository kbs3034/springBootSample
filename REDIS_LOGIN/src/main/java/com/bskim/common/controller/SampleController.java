package com.bskim.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bskim.common.session.SessionObject;
import com.bskim.common.session.SessionUtils;

@RestController
@RequestMapping("/sample")
public class SampleController {
	
	public SessionUtils sessionUtils;
	
	@Autowired
	public SampleController(SessionUtils sessionUtils) {
		this.sessionUtils = sessionUtils;
	}
	
	@RequestMapping("/login")
	public @ResponseBody Map<String,Object> login(@RequestBody Map<String,String> param) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		String id = param.get("id");
		String password = param.get("password");
		
		//로그인 정합성 로직(DB설치전 임시 test)
		if("test".equals(id) && "test".equals(password)) {
			SessionObject sessionObject = new SessionObject();
			sessionObject.setCustNo("123456789");
			sessionObject.setNickName("닉네임");
			sessionObject.setUserId(id);
			sessionObject.setUserName("이름");
			
			sessionUtils.creatSession(sessionObject);
			result.put("resultMessage", "success");
		}else {
			result.put("resultMessage", "fail");
		}
		
	return result;
	}
	
	@RequestMapping("/sessionChk")
	public @ResponseBody Map<String,Object> sessionChk() {
		Map<String,Object> result = new HashMap<String,Object>();
		
		if(sessionUtils.isLogin()) {
			result.put("sessionObject", sessionUtils.getSessionObject());
			result.put("resultMessage", "success");
		}else {
			result.put("resultMessage", "fail");
		}
		
	return result;
	}
	
}
