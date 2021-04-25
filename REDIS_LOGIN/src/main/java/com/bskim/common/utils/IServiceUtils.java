package com.bskim.common.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.stereotype.Component;

import com.bskim.common.annotaion.TranID;

@Component
public class IServiceUtils {
	
	private static Object seviceObj;
	
	@Value("${project.basePackage}")
	String basePackage;
	
	public Object ServiceCall(Map<String,Object> param,String tranId) throws Throwable {
		Object result = null;
		
		String bussinessDvCode = tranId.substring(0, 3).toLowerCase();
		String bussinessDetailDvCode = tranId.substring(3, 6).toLowerCase();
		String serviceId =  tranId.substring(0, 9);
		
		StringBuffer sb = new StringBuffer(basePackage);
		
		sb.append(".");sb.append(bussinessDvCode);
		sb.append(".");sb.append(bussinessDetailDvCode);
		sb.append(".service.");
		sb.append(serviceId);
		
		Method[] methods;
		try {
			seviceObj = BeanUtils.getBean(Class.forName(sb.toString())); 
			
			methods = seviceObj.getClass().getDeclaredMethods();
			Method foundMethod = null;
			
			for(Method method : methods) {
				String value = method.getAnnotation(TranID.class).value();
				if(value.equals(tranId) && foundMethod == null) {
					foundMethod = method;
				}else if(value.equals(tranId) && foundMethod != null) {
					// TODO throw new BizException("서비스내에 중복된 거래아이디가 등록되었습니다. 확인 후 다시 시도 하십시오.");
					throw new IllegalAccessException("서비스내에 중복된 거래아이디가 등록되었습니다. 확인 후 다시 시도 하십시오.");
				}
			}
			
			if(foundMethod != null) {
				try {
					if(foundMethod.getParameterTypes().length > 0) {
						Object paramObject = IObjectUtils.convertMapToObject(param, foundMethod.getParameterTypes()[0].newInstance());
						result = foundMethod.invoke(seviceObj, paramObject);
					}else {
						result = foundMethod.invoke(seviceObj);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new IllegalArgumentException("해당 거래의 파라미터가 잘못 설정 되었습니다 확인후 다시 시도하십시오.");
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new IllegalAccessException("해당 거래 아이디를 찾을 수 없습니다. 거래 아이디 명명규칙 등을 확인하여 서비스 아이디 등록후 다시 시도하십시오.");
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					throw e.getCause();
				}
			}else {
				throw new IllegalAccessException("해당 거래 아이디를 찾을 수 없습니다. 거래 아이디 명명규칙 등을 확인하여 서비스 아이디 등록후 다시 시도하십시오.");
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
