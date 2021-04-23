//package com.bskim.common.configuration;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.type.ClassMetadata;
//import org.springframework.core.type.MethodMetadata;
//import org.springframework.core.type.classreading.MetadataReader;
//import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
//
//import com.bskim.common.annotaion.TranID;
//
//public class ClassMethodMetaScanExample {
//
//	public static void main(String[] args) {
//		
//		
////		try {
////			
////			Class targetClass = Class.forName("com.bskim.co.usr.lgi.service.USRLGI001");
////			Method[] methods = targetClass.getDeclaredMethods();
////		
////			for(Method method : methods) {
////				TranID tranId = method.getAnnotation(TranID.class);
////				System.out.println(tranId.value());
////			}
////			
////			
////		} catch (ClassNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
////		List<ClassMetadata> classMetaScans = classMetaScan("classpath*:com/bskim/**/*.class", Controller.class);
////		for (ClassMetadata classMetadata : classMetaScans) {
////			System.out.println("classMetaScans");
////			System.out.println(classMetadata.getClassName());
////		}
////
////		List<Set<MethodMetadata>> methodMetaScans = methodMetaScan("classpath:com/bskim/**/*.class", RequestMapping.class);
////		for (Set<MethodMetadata> methodMetaDataSet : methodMetaScans) {
////			for (MethodMetadata methodMetadata : methodMetaDataSet) {
////				System.out.println("methodMetadata");
////				System.out.println(methodMetadata.getDeclaringClassName() + "." + methodMetadata.getMethodName());
////			}
////		}
//	}
//
//	
//	
//	
//	
//	
//	public static List<ClassMetadata> classMetaScan(String classAntPattern, Class annotationType) {
//// 	Spring 컨테이너 안에서는 ApplicationContext.getResources( .. ) 형태로도 사용 가능
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		Resource[] rs;
//		try {
//			rs = resolver.getResources(classAntPattern);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//
//		List classeMetas = new ArrayList();
//		for (Resource r : rs) {
//			MetadataReader mr;
//			try {
//				mr = new SimpleMetadataReaderFactory().getMetadataReader(r);
//			} catch (IOException e) {
//				e.printStackTrace();
//				continue;
//			}
//			boolean hasAnnotation = mr.getAnnotationMetadata().hasAnnotation(annotationType.getName());
//			if (hasAnnotation) {
//				classeMetas.add(mr.getClassMetadata());
//			}
//		}
//		return classeMetas;
//	}
//
//	public static List<Set<MethodMetadata>> methodMetaScan(String classAntPattern, Class annotationType) {
//// Spring 컨테이너 안에서는 ApplicationContext.getResources( .. ) 형태로도 사용 가능
//		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		Resource[] rs;
//		try {
//			rs = resolver.getResources(classAntPattern);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//
//		List allMethodMetadatas = new ArrayList();
//		for (Resource r : rs) {
//			MetadataReader mr;
//			try {
//				mr = new SimpleMetadataReaderFactory().getMetadataReader(r);
//			} catch (IOException e) {
//				e.printStackTrace();
//				continue;
//			}
//			Set methodMetadatas = mr.getAnnotationMetadata().getAnnotatedMethods(annotationType.getName());
//			allMethodMetadatas.add(methodMetadatas);
//		}
//		return allMethodMetadatas;
//	}
//}