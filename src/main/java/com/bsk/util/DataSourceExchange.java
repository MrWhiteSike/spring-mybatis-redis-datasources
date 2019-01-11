package com.bsk.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.bsk.entity.Constants;

/**
 * 利用Spring的AOP来实现动态切换数据源
 * 动态切换数据源的切面类
 * @author Lenovo
 *
 */
//@Aspect
//@Component
public class DataSourceExchange {
	private final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 定义切点方法
	 */
//	@Pointcut("execution(* com.bsk.controller.*.*.*(..))")
//	public void aspect() {
//		
//	}
	/**
	 * 切点执行前动态切换数据源
	 * @param point
	 */
//	@Before("aspect()")
	public void doBefore(JoinPoint point) {
		
		// 获取目标对象的类类型
		Class<?> aClass = point.getTarget().getClass();
		// 获取包名用于区分不同数据源
//		String whichDataSource = aClass.getName().substring(19, aClass.getName().lastIndexOf("."));
		String whichDataSource = aClass.getName().substring(16, aClass.getName().lastIndexOf("."));
		logger.info("当前切点的包名" + whichDataSource);
		switch(whichDataSource) {
			case "ssmone":
				DataSourceHolder.setDataSources(Constants.DATASOURCE_ONE);
				break;
			case "ssmtwo":
				DataSourceHolder.setDataSources(Constants.DATASOURCE_TWO);
				break;
		}
	}
	
	/**
	 * 执行后将数据源置空
	 */
//	@After("aspect()")
	public void doAfter() {
		DataSourceHolder.setDataSources(null);
	}
}
