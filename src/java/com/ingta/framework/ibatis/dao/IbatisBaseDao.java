package com.ingta.framework.ibatis.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ingta.framework.ibatis.executor.PagingSqlExecutor;
import com.ingta.framework.ibatis.util.ReflectUtils;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-9 下午01:49:51 说明：
 */
public abstract class IbatisBaseDao extends SqlMapClientDaoSupport {

    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public void setEnableLimit(boolean enableLimit) {
        if (sqlExecutor instanceof PagingSqlExecutor) {
            ((PagingSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
        }
    }

    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof SqlMapClientImpl) {
                ReflectUtils.setFieldValue(((SqlMapClientImpl) sqlMapClient).getDelegate(),
                        "sqlExecutor", SqlExecutor.class, sqlExecutor);
                if (logger.isDebugEnabled()) {
                    logger.debug("renew sqlExecutor");
                }
            }
        }
    }
}
