package com.ingta.si.admin.service.impl;


import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ingta.framework.ibatis.service.impl.IbatisBaseServiceImpl;
import com.ingta.si.admin.entity.BSysconfig;
import com.ingta.si.admin.service.SysconfigService;

@Service("sysconfigService")
@Transactional(readOnly = true)
public class SysconfigServiceImpl extends IbatisBaseServiceImpl<BSysconfig, String>
		implements SysconfigService {

	@PostConstruct
	public void init() {
		ibatisDao.init(BSysconfig.class, String.class);
	}

}
