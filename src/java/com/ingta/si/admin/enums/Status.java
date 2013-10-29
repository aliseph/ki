package com.ingta.si.admin.enums;

public enum Status {
	NORMAL("正常", 1), DELETE("删除", 0);

	private String name;
	private int code;
	public static String OTHER = "all";

	private Status(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}

}
