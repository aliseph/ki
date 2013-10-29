package com.ingta.framework.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbContextHolder {
	private static Logger logger = LoggerFactory.getLogger(DbContextHolder.class);
	/**
	 * DataSource上下文，每个线程对应相应的数据源key
	 */
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal contextHolder = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static void setDbType(String dbType) {
		logger.info("change to " + dbType);
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return (String) contextHolder.get();
	}

	public static void clearDbType() {
		contextHolder.remove();
	}
}
