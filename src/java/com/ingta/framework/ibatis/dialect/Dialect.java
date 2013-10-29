package com.ingta.framework.ibatis.dialect;

/**
 * @author aliseph
 * 
 */
public interface Dialect {
	static final String SQL_END_DELIMITER = "";

	public boolean supportsLimit();

	public String getLimitString(String sql, boolean hasOffset);

	public String getLimitString(String sql, int skipResults, int maxResults);

}
