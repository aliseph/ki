package com.ingta.framework.ibatis.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.ingta.framework.ibatis.dao.IbatisBaseDao;
import com.ingta.framework.ibatis.dao.IbatisGenericDao;
import com.ingta.framework.ibatis.util.Page;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-8 上午09:56:09 说明：
 */
public class IbatisGenericDaoImpl<T, PK> extends IbatisBaseDao implements IbatisGenericDao<T, PK> {

    private Class<T> entityClass;// DAO所管理的Entity类型
    private Class<PK> idClass;// id的类型
    private String entityName;// DAO管理的对象T的名字
    public static final String POSTFIX_INSERT = ".insert";
    public static final String POSTFIX_UPDATE = ".update";
    public static final String POSTFIX_UPDATE_STATUS = ".updateStatus";
    public static final String POSTFIX_DELETE = ".delete";
    public static final String POSTFIX_DELETE_ID = ".deleteById";
    public static final String POSTFIX_BATCH_DELETE = ".deleteByIds";
    public static final String POSTFIX_SELECT = ".select";
    public static final String POSTFIX_GETLIST = ".getList";
    public static final String POSTFIX_COUNT = ".count";
    public static final String POSTFIX_QUERY = ".query";
    public static final String POSTFIX_VALIDATION = ".validation";
    public static final String POSTFIX_GETLIKE = ".getLike";

    /**
     * DAO管理的entity的名字与相应的后缀，组成相应的ibatis管理的sql语句
     */
    private String getStatementId(String suffix) {
        return entityName + suffix;
    }

    /**
     * 依赖注入时初始化Dao实例， 其中包含classname， id的类型（如：String） entityName
     * Dao层对象所操作的对象的类名，泛型
     */
    @Override
    public void init(Class<T> entityClass, Class<PK> idClass) {
        this.entityClass = entityClass;
        this.idClass = idClass;
        this.entityName = entityClass.getSimpleName().toLowerCase();
        if (logger.isDebugEnabled()) {
            logger.debug("assign to entityClass and idClass : " + entityClass + "," + idClass);
        }
    }

    @Override
    public T findById(String statementId, PK id) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_SELECT)
                : statementId;
        return ObjParse(getSqlMapClientTemplate().queryForObject(statementId, id));
    }

    @Override
    public T findOne(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_GETLIST)
                : statementId;
        return ObjParse(getSqlMapClientTemplate().queryForObject(statementId, params));
    }

    @Override
    public List<T> findAll(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_GETLIST)
                : statementId;
        return ListParse(getSqlMapClientTemplate().queryForList(statementId, bean2Map(params)));
    }

    @Override
    public List<T> findLike(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_GETLIKE)
                : statementId;
        return ListParse(getSqlMapClientTemplate().queryForList(statementId, bean2Map(params)));
    }

    @Override
    public List<T> findByNumber(String statementId, Object params, int number) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_GETLIST)
                : statementId;
        return ListParse(getSqlMapClientTemplate().queryForList(statementId, bean2Map(params), 0,
                number));
    }

    @Override
    public Page<T> findAllByPage(String listStatId, String countStatId, Page<T> page) {
        logger.debug("params :" + page.toMap());
        listStatId = StringUtils.isEmpty(listStatId) ? getStatementId(POSTFIX_GETLIST) : listStatId;
        if (page.getCount() == 0) {
            page = new Page<T>(findCount(countStatId, page.toMap()), page.getParams(),
                    page.getNumPerPage(), page.getPageNum());
        }
        page.setData(ListParse(getSqlMapClientTemplate().queryForList(listStatId, page.toMap(),
                page.getSkipResults(), page.getNumPerPage())));
        return page;
    }

    @Override
    public PK add(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_INSERT)
                : statementId;
        return idParse(getSqlMapClientTemplate().insert(statementId, params));
    }

    @Override
    public void batchAdd(String statementId, final List<Object> paramList) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_INSERT)
                : statementId;
        final String sqlId = statementId;
        getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (Object o : paramList) {
                    executor.insert(sqlId, o);
                    batch++;
                    if (batch != 1000) {
                        continue;
                    }
                    executor.executeBatch();
                    batch = 0;
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    @Override
    public int modify(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_UPDATE)
                : statementId;
        return getSqlMapClientTemplate().update(statementId, params);
    }

    @Override
    public void batchModify(String statementId, final List<Object> paramList) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_UPDATE)
                : statementId;
        final String sqlId = statementId;
        getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (Object o : paramList) {
                    executor.update(sqlId, o);
                    batch++;
                    if (batch == 300) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    @Override
    public int modifyStatus(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_UPDATE_STATUS)
                : statementId;
        return getSqlMapClientTemplate().update(statementId, params);
    }

    @Override
    public void batchModifyStatus(String statementId, final List<Object> paramList) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_UPDATE_STATUS)
                : statementId;
        final String sqlId = statementId;
        getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (Object o : paramList) {
                    executor.update(sqlId, o);
                    batch++;
                    if (batch == 300) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    @Override
    public int remove(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_DELETE_ID)
                : statementId;
        return getSqlMapClientTemplate().delete(statementId, params);
    }

    @Override
    public void batchRemove(String statementId, final List<Object> paramList) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_DELETE_ID)
                : statementId;
        final String sqlId = statementId;
        getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
                executor.startBatch();
                int batch = 0;
                for (Object o : paramList) {
                    executor.delete(sqlId, o);
                    batch++;
                    if (batch == 300) {
                        executor.executeBatch();
                        batch = 0;
                    }
                }
                executor.executeBatch();
                return null;
            }
        });
    }

    @Override
    public Object findObject(String statementId, Object params) {
        return getSqlMapClientTemplate().queryForObject(statementId, params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O> List<O> findList(String statementId, Object params) {
        return (List<O>) getSqlMapClientTemplate().queryForList(statementId, params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O> List<O> findNumbers(String listStatId, Object params, int resultsNumbers,
            int skipResults) {
        return (List<O>) getSqlMapClientTemplate().queryForList(listStatId, params, skipResults,
                resultsNumbers);
    }

    @Override
    public long findCount(String statementId, Object params) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_COUNT)
                : statementId;
        return (Long) getSqlMapClientTemplate().queryForObject(statementId, params);
    }

    @Override
    public boolean validation(String statementId, Object id) {
        statementId = StringUtils.isEmpty(statementId) ? getStatementId(POSTFIX_VALIDATION)
                : statementId;
        Long count = (Long) getSqlMapClientTemplate().queryForObject(statementId, id);
        return count == 0;
    }

    @SuppressWarnings("unchecked")
    public T ObjParse(Object value) {
        if (value != null && value.getClass().equals(entityClass)) {
            return (T) value;
        }
        return null;
    }

    public Object bean2Map(Object value) {
        if (!isMap(value)) {
            try {
                return BeanUtils.describe(value);
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return value;
    }

    public boolean isMap(Object value) {
        if (value != null && value instanceof Map) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public PK idParse(Object value) {
        if (value != null && value.getClass().equals(idClass)) {
            return (PK) value;
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> ListParse(List list) {
        if (list != null && !list.isEmpty() && list.get(0).getClass().equals(entityClass)) {
            return list;
        }
        return null;
    }
}
