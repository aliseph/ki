package com.ingta.si.util;

import java.io.Serializable;

import com.ingta.framework.common.MD5Util;

/**
 * 用于系统初始化时保存系统参数，附带一些工具方法，使用enum类，保证为单例
 * */
public enum InitParams implements Serializable {
	INSTANCE, ;

	public InitParams getInstance() {
		return INSTANCE;
	}

	public static final String ADMIN = "ADMIN";
	public static final String INIT_PASSWORD = MD5Util.MD5("111111");
}