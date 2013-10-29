package com.ingta.framework.ibatis.service;

import java.util.List;
import java.util.Map;

import com.ingta.framework.ibatis.util.Page;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-4-14 下午02:08:13
 *          说明：所有service接口的父基类，T为一个具体的对象(entity)，PK为这个对象的主键的类型
 *          (String、Integer、Long……)
 */
public interface IbatisBaseService<T, PK> {

	/**
	 * 根据条件分页方式查询数据库中的所有对象
	 * @param Page 分页信息
	 * @param t 查询对象的特定条件
	 * @return 封装后的对象信息
	 */
	Page<T> findAllByPaging(Page<T> page);

	/**
	 * 查询数据库中的所有对象
	 * @param params 查询对象的特定条件
	 * @return 封装后的对象信息
	 */
	List<T> findAll(Object params);

	/**
	 * 查询数据库中的所有可用的对象（状态值是正常的）
	 * @param params 查询对象的特定条件
	 * @return 封装后的对象信息
	 */
	List<T> findAllUsable(Map<String, Object> params);

	/**
	 * 根据条件查询数据库中的一个具体对象
	 * @param params 查询对象的特定条件
	 * @return 具体对象
	 */
	List<T> findLike(Map<String, Object> params);

	/**
	 * 通过编码查询数据库中的一个具体对象
	 * @param id 主键(编码)
	 * @return 具体对象
	 */
	T findById(PK id);

	/**
	 * 根据条件查询数据库中的一个具体对象
	 * @param params 查询对象的特定条件
	 * @return 具体对象
	 */
	T findOne(Object params);

	/**
	 * 通过编码删除数据库中的一个具体对象
	 * @param id 主键(编码)
	 */
	void deleteInDB(PK id);

	/**
	 * 更新数据库中的一个对象的数据
	 * @param t 对象
	 */
	void update(T t);

	/**
	 * 在数据库中的添加一个对象
	 * @param t 对象
	 * @return 对象的主键(编码)
	 */
	PK add(T t);

	/**
	 * 通过编码把数据库中的一个具体对象的状态重新设置为NORMAL
	 * @param id
	 */
	void recover(PK id);

	/**
	 * 通过编码把数据库中的一个具体对象的状态设置为DELETE
	 * @param id 主键(编码)
	 */
	void deleteByStatus(PK id);

	/**
	 * 验证对象是否已经被使用
	 * @param id 对象（一般为主键）
	 * @return 存在返回false,不存在返回true
	 */
	boolean validation(Object id);

}
