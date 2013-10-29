package com.ingta.si.admin.entity;

import java.io.Serializable;

public class BSysconfig implements Serializable{
	private static final long serialVersionUID = 9011724273021547186L;
	
	private int sysconfigcode;//配置编码
	private String sysconfigname;//配置名称
	private String sysconfigvalue;//配置值
	private String sfqy;//是否启用
	
	public int getSysconfigcode() {
		return sysconfigcode;
	}
	public void setSysconfigcode(int sysconfigcode) {
		this.sysconfigcode = sysconfigcode;
	}
	public String getSysconfigname() {
		return sysconfigname;
	}
	public void setSysconfigname(String sysconfigname) {
		this.sysconfigname = sysconfigname;
	}
	public String getSysconfigvalue() {
		return sysconfigvalue;
	}
	public void setSysconfigvalue(String sysconfigvalue) {
		this.sysconfigvalue = sysconfigvalue;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	
	
}
