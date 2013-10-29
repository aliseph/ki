package com.ingta.si.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ingta.framework.common.MD5Util;
import com.ingta.framework.ibatis.service.impl.IbatisBaseServiceImpl;
import com.ingta.framework.util.CommonUtil;
import com.ingta.framework.util.TupleUtil;
import com.ingta.framework.util.TwoTuple;
import com.ingta.si.admin.entity.Admin;
import com.ingta.si.admin.service.AdminService;
import com.ingta.si.util.InitParams;

/**
 * 管理员
 * @author ingta_肖杰
 * 
 */
@Service("adminService")
@Transactional(readOnly = true)
public class AdminServiceImpl extends IbatisBaseServiceImpl<Admin, String> implements AdminService {

	@PostConstruct
	public void init() {
		ibatisDao.init(Admin.class, String.class);
	}

	@Transactional(readOnly = false)
        @Override
	public String add(Admin admin) {
		admin.setId(CommonUtil.getUUid());
		admin.setPassword(MD5Util.MD5(admin.getPassword()));// MD5加密密码
		admin.setStatus(constant.normal);
		return super.add(admin);
	}

        @Override
	public TwoTuple<Boolean, Admin> login(Admin admin) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", admin.getAccount());
		params.put(constant.statusName, constant.normal);
		Admin _admin = (Admin) this.findOne(params);
		boolean flag = false;
		if (_admin != null && _admin.getPassword().equals(MD5Util.MD5(admin.getPassword()))) {
			flag = true;
		}
		return TupleUtil.tuple(flag, _admin);
	}

	@Override
	@Transactional(readOnly = false)
	public void resetPassword(String id) {
		Admin admin = new Admin();
		admin.setId(id);
		admin.setPassword(InitParams.INIT_PASSWORD);
		this.update(admin);
	}
	
}