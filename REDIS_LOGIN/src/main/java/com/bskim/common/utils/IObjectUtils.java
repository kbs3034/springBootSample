package com.bskim.common.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;




public class IObjectUtils {
	
	public static Object convertObjectToObject(Object obj, Class targetClass) {
		Object result = null;
		try {
			result = targetClass.newInstance();
			
			Field[] fields = obj.getClass().getDeclaredFields();
			Field[] targetFields = result.getClass().getDeclaredFields();
			
			
			int fieldsLen = fields.length;
			int targetFieldsLen = targetFields.length;
			
			for(int i = 0; i < fieldsLen; i++) {
				for(int j = 0; j < targetFieldsLen; j++) {
					String fieldName = fields[i].getName();
					String targetFieldName = targetFields[j].getName();
					
					if(targetFieldName.equals(fieldName) && fields[i].getType() == targetFields[j].getType()) {
						Method getterMethod = new PropertyDescriptor(fields[i].getName(),obj.getClass()).getReadMethod();
						Method setterMethod = new PropertyDescriptor(targetFields[j].getName(),result.getClass()).getWriteMethod();
							
						setterMethod.invoke(result, getterMethod.invoke(obj));
					}
				}
			}
		} catch (InstantiationException | IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			// throw new BizException("Object Convert fail");
			e.printStackTrace();
		}
		
		return result;
	}

	public static Object convertMapToObject(Map<String,Object> map, Object obj) {
		Object result = obj;
		try {
			if(map == null || map.size() == 0) return result;
			BeanUtils.populate(result, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> convertObjectToMap(Object obj) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		result = objectMapper.convertValue(obj, Map.class);
		
		return result;
	}
	
}
