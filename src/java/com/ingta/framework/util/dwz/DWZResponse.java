package com.ingta.framework.util.dwz;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * springmvc 返回dwz识别的map类型的数据
 * */
public class DWZResponse implements Serializable {
	private static final long serialVersionUID = 2849811516961977150L;

	public static final String MESSAGE = "message";
	public static final String STATUSCODE = "statusCode";
	public static final String CALLBACKTYPE = "callbackType";
	public static final String FORWARDURL = "forwardUrl";
	public static final String CLOSECURRENT = "closeCurrent";
	public static final String NAVTABID = "navTabId";
	public static final String FORWARD = "forward";
	public static final String REL = "rel";
	public static final String OK = "200";
	public static final String ERROR = "300";
	public static final String TIMEOUT = "301";
	public static final String TIMEOUTMSG = "会话超时，请重新登录";
	public static final String LOGIN_OK = "登录成功";
	public static final String DELETE_OK = "删除成功";
	public static final String DELETE_FAIL = "删除失败";
	public static final String OPERATE_OK = "操作成功";
	public static final String OPERATE_FAIL = "操作失败";
	public static final String SYNC_FAIL = "同步失败，通讯异常";
	public static final String SYNC_OK = "同步成功";
	public static final String RESET_OK = "重置成功";
	public static final String RESET_FAIL = "重置失败";
	public static final String UNAUTHORIZED = "权限不足，不能访问";
	public static final String RESET_PASSWORD_OK = "操作成功，下次登录时生效";
	public static final String INFO_FAIL = "修改未完成，填写信息不完整";
	public static final String BEFORE_PASSWORD_FAIL = "修改未完成，用户原密码不正确";
	public static final String UPDATE_PASSWORD_OK = "用户密码修改成功，下次登录时生效！";
	public static final String NOT_LOGIN = "用户未登录，请登录";
	public static final String ACCOUNT_MISTAKE = "登录失败，账号错误";
	public static final String PASSWORD_MISTAKE = "登录失败，密码错误";
	public static final String CAPTCHA_MISTAKE = "登录失败，验证码错误";
	public static final String LOGIN_FAIL = "登录失败，账号或密码错误";
	public static final String ACCOUNT_LOCK = "账号已被锁定，请与系统管理员联系";
	public static final String UNKNOWN_ERROR = "未知错误";
	public static final String VALI_OK = "可以使用";
	public static final String VALI_ERROR = "已经存在";

	public static Map<String, Object> validateOK() {
		return validateOK(null);
	}

	public static Map<String, Object> validateError() {
		return validateError(null);
	}

	public static Map<String, Object> validateOK(Object message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ok", message == null ? VALI_OK : message);
		return map;
	}

	public static Map<String, Object> validateError(Object message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", message == null ? VALI_ERROR : message);
		return map;
	}

	public static Map<String, Object> operateFail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, OPERATE_FAIL);
		map.put(STATUSCODE, ERROR);
		return map;
	}

	/**
	 * 自定义，登录超时
	 * @return
	 */
	public static Map<String, Object> timeout() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, TIMEOUTMSG);
		map.put(STATUSCODE, TIMEOUT);
		return map;
	}

	public static Map<String, Object> operateFail(Object message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, ERROR);
		return map;
	}

	/**
	 * 自定义错误信息 ajax放回，如：权限不足。您所传的文件格式不正确。
	 * @param message 自定义消息
	 * @return
	 */
	public static Map<String, Object> operateFailforAuth(Object message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, ERROR);
		return map;
	}

	public static Map<String, Object> operateOK(String navTabId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, OPERATE_OK);
		map.put(STATUSCODE, OK);
		map.put(NAVTABID, navTabId);
		return map;
	}

	public static Map<String, Object> ajaxOperateOK(String rel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, OPERATE_OK);
		map.put(STATUSCODE, OK);
		map.put(CALLBACKTYPE, CLOSECURRENT);
		map.put(REL, rel);
		return map;
	}

	public static Map<String, Object> ajaxDeleteOK(String rel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, DELETE_OK);
		map.put(STATUSCODE, OK);
		map.put(REL, rel);
		return map;
	}

	public static Map<String, Object> operateOK() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, OPERATE_OK);
		map.put(STATUSCODE, OK);
		return map;
	}

	public static Map<String, Object> operateOK(Object message, String navTabId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, OK);
		map.put(NAVTABID, navTabId);
		return map;
	}

	public static Map<String, Object> deleteFail() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, DELETE_FAIL);
		map.put(STATUSCODE, ERROR);
		return map;
	}

	public static Map<String, Object> deleteOK(String navTabId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, DELETE_OK);
		map.put(STATUSCODE, OK);
		map.put(NAVTABID, navTabId);
		return map;
	}

	public static Map<String, Object> deleteOK() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, DELETE_OK);
		map.put(STATUSCODE, OK);
		return map;
	}

	public static Map<String, Object> deleteOK(Object message, String navTabId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, OK);
		map.put(NAVTABID, navTabId);
		return map;
	}

	public static Map<String, Object> forward(Object message, String forwardUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, OK);
		map.put(FORWARDURL, forwardUrl);
		map.put(CALLBACKTYPE, FORWARD);
		return map;
	}

	public static Map<String, Object> closeCurrent(Object message, String navTabId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, OK);
		map.put(NAVTABID, navTabId);
		map.put(CALLBACKTYPE, CLOSECURRENT);
		return map;
	}

	public static Map<String, Object> closeCurrent(Object message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, OK);
		map.put(CALLBACKTYPE, CLOSECURRENT);
		return map;
	}

	/**
     *
     * @param message
     * @param navTabId
     * @param forwardUrl
     * @return
     */
    public static Map<String, Object> closeCurrentAndForward(Object message, String navTabId,
			String forwardUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MESSAGE, message);
		map.put(STATUSCODE, OK);
		map.put(NAVTABID, navTabId);
		map.put(CALLBACKTYPE, CLOSECURRENT);
		map.put(FORWARDURL, forwardUrl);
		return map;
	}
}
