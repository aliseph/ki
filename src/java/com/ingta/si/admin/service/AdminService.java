package com.ingta.si.admin.service;

import com.ingta.framework.ibatis.service.IbatisBaseService;
import com.ingta.framework.util.TwoTuple;
import com.ingta.si.admin.entity.Admin;

public interface AdminService extends IbatisBaseService<Admin, String> {

	/**
	 * 登陆方法
	 * @param admin 需要登录的对象
	 * @return 是否可以登录、登录后的对象
	 */
	public TwoTuple<Boolean, Admin> login(Admin admin);

	/**
	 * 重置管理员密码
	 * @param id
	 */
	public void resetPassword(String id);
}
