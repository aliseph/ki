package com.ingta.framework.ibatis.executor;

import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import com.ingta.framework.ibatis.dialect.Dialect;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-9 下午12:49:43 说明：
 */
public class PagingSqlExecutor extends SqlExecutor {

	private static Logger logger = LoggerFactory.getLogger(PagingSqlExecutor.class);

	private Dialect dialect;

	private boolean enableLimit = true;

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public boolean isEnableLimit() {
		return enableLimit;
	}

	public void setEnableLimit(boolean enableLimit) {
		this.enableLimit = enableLimit;
	}

	@Override
	public void executeQuery(StatementScope statementScope, Connection conn, String sql,
			Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback)
			throws SQLException {
		if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)
				&& supportsLimit()) {
			sql = dialect.getLimitString(sql, skipResults, maxResults);
			if (logger.isDebugEnabled()) {
				logger.debug(sql);
			}
			skipResults = NO_SKIPPED_RESULTS;
			maxResults = NO_MAXIMUM_RESULTS;
		}
		super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
	}

	public boolean supportsLimit() {
		if (enableLimit && dialect != null) {
			return dialect.supportsLimit();
		}
		return false;
	}

}
