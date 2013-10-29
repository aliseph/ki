package com.ingta.si.admin.entity;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户名称
 * @author ingta_肖杰
 * 
 */
public class Admin implements Serializable {
	private static final long serialVersionUID = -5716804578907259560L;
	/** 管理员编码 */
	private String id;
	/** 管理员登录名 */
	@NotBlank(message = "帐号不能为空")
	// @Length(min=2, max=20, message="帐号长度必须在2-20之间") //@Pattern(regexp =
	// "^[a-zA-Z_]\\w{4,19}$", message = "帐号必须以字母下划线开头，可由字母数字下划线组成")
	private String account;

	/** 管理员密码 */
	private String password;
	/** 管理员显示名 */
	@NotBlank
	// @Length(min=2, max=20)
	private String name;
	/** 管理员状态 */
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}