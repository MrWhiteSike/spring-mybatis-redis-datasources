package com.bsk.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 继承Spring的AbstractRoutingDataSource类来进行拓展多数据源
 * 该类相当于一个DataSource的路由，用于根据key值来进行切换对应的dataSource
 * 动态数据源
 * @author Lenovo
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	
	/**
	 * 返回当前数据源的key值
	 * 之后通过该key值在resolvedDataSources这个map中找到对应的value，该value就是数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceHolder.getDataSources();
	}
	
}
