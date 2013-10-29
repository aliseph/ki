package com.ingta.framework.ibatis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ingta.framework.ibatis.dao.IbatisGenericDao;
import com.ingta.framework.ibatis.service.IbatisBaseService;
import com.ingta.framework.ibatis.util.SQLConstant;
import com.ingta.framework.ibatis.util.Page;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-9 下午02:59:30 说明：
 */
public class IbatisBaseServiceImpl<T, PK> implements IbatisBaseService<T, PK> {

    protected IbatisGenericDao<T, PK> ibatisDao;
    protected SQLConstant constant;

    /**
     * 获取一个新的查询参数对象
     *
     * @return 新的查询参数对象
     */
    public Map<String, Object> getNewParamMap() {
        return new HashMap<String, Object>();
    }

    @Override
    public Page<T> findAllByPaging(Page<T> page) {
        return ibatisDao.findAllByPage(null, null, page);
    }

    @Override
    public List<T> findAll(Object params) {
        return ibatisDao.findAll(null, params);
    }

    @Override
    public List<T> findAllUsable(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put(constant.statusName, constant.normal);
        return ibatisDao.findAll(null, params);
    }

    @Override
    public List<T> findLike(Map<String, Object> params) {
        return ibatisDao.findLike(null, params);
    }

    @Override
    public T findById(PK id) {
        return ibatisDao.findById(null, id);
    }

    @Override
    public T findOne(Object params) {
        return ibatisDao.findOne(null, params);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteInDB(PK id) {
        ibatisDao.remove(null, id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T t) {
        ibatisDao.modify(null, t);
    }

    @Override
    @Transactional(readOnly = false)
    public PK add(T t) {
        return ibatisDao.add(null, t);
    }

    @Override
    @Transactional(readOnly = false)
    public void recover(PK id) {
        Map<String, Object> params = getNewParamMap();
        params.put("id", id);
        params.put("status", constant.normal);
        ibatisDao.modifyStatus(null, params);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteByStatus(PK id) {
        Map<String, Object> params = getNewParamMap();
        params.put("id", id);
        params.put("status", constant.delete);
        ibatisDao.modifyStatus(null, params);
    }

    @Override
    public boolean validation(Object id) {
        return ibatisDao.validation(null, id);
    }

    public SQLConstant getConstant() {
        return constant;
    }

    public void setConstant(SQLConstant constant) {
        this.constant = constant;
    }

    public IbatisGenericDao<T, PK> getIbatisDao() {
        return ibatisDao;
    }

    public void setIbatisDao(IbatisGenericDao<T, PK> ibatisDao) {
        this.ibatisDao = ibatisDao;
    }
}
