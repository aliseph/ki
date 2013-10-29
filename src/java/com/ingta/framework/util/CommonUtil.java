package com.ingta.framework.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.UUID;

/**
 * 此处是公用工具类
 * 
 * @author pc
 * 
 */
public class CommonUtil {

	/**
	 * 得到UUID
	 */
	public static String getUUid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取与评估的接口路径及文件头部信息
	 */
	public static Properties getFilePath(String fileName) throws Exception {
		// String baseClassDir = System.getProperty("user.dir");
		String baseClassDir = CommonUtil.class.getResource("/").toString();
		baseClassDir = baseClassDir.substring(6);
		baseClassDir = URLDecoder.decode(baseClassDir, "UTF-8");
		InputStream in = new BufferedInputStream(new FileInputStream(
				baseClassDir + fileName));
		Properties p = new Properties();
		p.load(in);
		return p;
	}

	/**
	 * 指定字符串，截取指定字符之间的数据的方法
	 */
	public static String stringGetNewString(String longString, String reg) {
		if (longString.indexOf(reg) > 0) {
			String string = longString.substring(longString.indexOf(reg) + 1);
			String endStr = "";
			if (string.indexOf(reg) > 0) {
				endStr = string
						.substring(0, string.indexOf(reg) + reg.length());
				endStr = reg + endStr;
			}

			longString = longString.replace(endStr, "");
			return CommonUtil.stringGetNewString(longString, reg);
		}
		return longString;
	}

	/**
	 * 数据转化为DOUBLE如果传入数据为空，返回0.00
	 */
	public static Double DoubleByString(Object value) {
		if (value == null || "".equals(value)) {
			return 0.00;
		}
		try {
			Double d = Double.parseDouble((String) (value + ""));
			return d;
		} catch (Exception e) {
			return 0.00;
		}
	}

	/**
	 * 数据转化为Integer如果传入数据为空，返回0
	 */
	public static int IntegerByString(Object value) {
		if (value == null || "".equals(value)) {
			return 0;
		}
		try {
			int d = Integer.parseInt((String) (value + ""));
			return d;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 数据 转化 为 String 如果传入的数据为空 ,返回 "  "
	 * 
	 * @param value
	 * @return
	 */
	public static String StringByObject(Object value) {
		if (value == null || "".equals(value)) {
			return " ";
		}
		try {
			String d = value.toString();
			return d;
		} catch (Exception e) {
			return " ";
		}
	}

}
