package cn.edu.hnie.datasources;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * 
 * @author theodo
 * @email 36780272@qq.com
 * @date 2017/8/19 1:03
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	public DynamicDataSource(DataSource defaultTargetDataSource, Map<String, DataSource> targetDataSources) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
		super.setTargetDataSources(new HashMap<Object, Object>(targetDataSources));
		super.afterPropertiesSet();
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getDataSource();
	}

	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}

}
