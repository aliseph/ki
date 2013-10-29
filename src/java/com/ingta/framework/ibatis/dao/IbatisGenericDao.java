package com.ingta.framework.ibatis.dao;

import java.util.List;

import com.ingta.framework.ibatis.util.Page;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-8 上午09:45:41 说明：
 */
public interface IbatisGenericDao<T, PK> {
	/**
	 * 初始化dao
	 * @param clazz1 T对象
	 * @param clazz2 PK对象
	 */
	void init(Class<T> clazz1, Class<PK> clazz2);

	/**
	 * 验证对象是否已经被使用
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param id 对象（一般为主键）
	 * @return 存在返回false,不存在返回true
	 */
	boolean validation(String statementId, Object id);

	/**
	 * 根据主键查找对象
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param id 主键值
	 * @return 对象实体
	 */
	T findById(String statementId, PK id);

	/**
	 * 根据参数查找对象
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return 对象实体
	 */
	T findOne(String statementId, Object params);

	/**
	 * 查找所有对象
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return list
	 */
	List<T> findAll(String statementId, Object params);

	/**
	 * 模糊查询，参数中可带关键字属性(即：与"gjz"绑定到参数params中,可为空)
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return List<T> 对象的集合，可为长度为0的list
	 */
	List<T> findLike(String statementId, Object params);

	/**
	 * 查找一定数量的对象
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @param number 数量
	 * @return list
	 */
	List<T> findByNumber(String statementId, Object params, int number);

	/**
	 * 分页查找所有对象
	 * @param listStatId 查询list的sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param countStatId 查询数量的sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param page 分页查询用到的参数
	 * @return page
	 */
	Page<T> findAllByPage(String listStatId, String countStatId, Page<T> page);

	/**
	 * 查询数量
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return
	 */
	long findCount(String statementId, Object params);

	/**
	 * 新增对象到数据库
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return 自动生成的主键
	 */
	PK add(String statementId, Object params);

	/**
	 * 批量新增对象到数据库
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param paramList sql参数列表
	 */
	void batchAdd(String statementId, final List<Object> paramList);

	/**
	 * 更新对象实体到数据库
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return 更新总条数
	 */
	int modify(String statementId, Object params);

	/**
	 * 批量更新对象信息
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param paramList sql参数列表
	 */
	void batchModify(String statementId, final List<Object> paramList);

	/**
	 * 根据参数删除对象
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return 删除总条数
	 */
	int remove(String statementId, Object params);

	/**
	 * 修改数据库中对象的状态
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param params sql参数
	 * @return 删除总条数
	 */
	int modifyStatus(String statementId, Object params);

	/**
	 * 批量修改数据库中对象的状态
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param paramList sql参数列表
	 */
	void batchModifyStatus(String statementId, final List<Object> paramList);

	/**
	 * 批量删除对象
	 * @param statementId sql语句名称后缀，可以为空，为空时，自动生成一个statementId
	 * @param paramList sql参数列表
	 */
	void batchRemove(String statementId, final List<Object> paramList);

	/**
	 * sql查询单个对象
	 * @param statementId sql语句名称后缀，不能为空
	 * @param params sql参数
	 * @return 查询结果
	 */
	Object findObject(String statementId, Object params);

	/**
	 * sql查询列表
	 * @param statementId sql语句名称后缀，不能为空
	 * @param params sql参数
	 * @return 查询结果
	 */
	<O> List<O> findList(String statementId, Object params);

	/**
	 * 查找一定数量的对象
	 * @param listStatId 查询list的sql语句名称后缀，不能为空
	 * @param params sql参数
	 * @param resultsNumbers 要查询的对象的数量
	 * @param skipResults 起始位置
	 * @return list
	 */
	<O> List<O> findNumbers(String listStatId, Object params, int resultsNumbers, int skipResults);
}
