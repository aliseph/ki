package com.ingta.framework.ibatis.util;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-8 下午12:40:09 说明：
 */
public class SQLConstant {
	public static int monthRange = 6;// 用户能查询到的与当前时间最大的间隔月数
	public String normal = "NORMAL";
	public String delete = "DELETE";
	public String statusName = "STATUS";
	public static int numPerPage = 10;

	public void setNumPerPage(int numPerPage) {
		SQLConstant.numPerPage = numPerPage;
	}

	public void setMonthRange(int monthRange) {
		SQLConstant.monthRange = monthRange;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
