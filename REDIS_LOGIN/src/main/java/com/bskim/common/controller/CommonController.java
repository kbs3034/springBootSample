package com.bskim.common.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bskim.common.dto.Result;
import com.bskim.common.exception.BizException;
import com.bskim.common.utils.IObjectUtils;
import com.bskim.common.utils.IServiceUtils;

@RestController
public class CommonController {
	@Autowired
	private IServiceUtils iServiceUtils;
	
	@RequestMapping("/{tranId}.t")
	public @ResponseBody Result ControllTrans(@RequestBody Map<String,Object> param, @PathVariable String tranId) throws Throwable {
		Map<String,Object> data = new HashMap<String,Object>();
		
		Object resultObj = iServiceUtils.ServiceCall(param,tranId);
		data = IObjectUtils.convertObjectToMap(resultObj);
		
		Result result = new Result(HttpStatus.OK,"success",data);
		return result;
	}
}
