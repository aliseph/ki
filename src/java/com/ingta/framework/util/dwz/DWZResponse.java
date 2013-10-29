package com.ingta.framework.util.dwz;

import com.ingta.framework.util.spring.SpringContextUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * springmvc 返回dwz识别的map类型的数据
 *
 */
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

    public static Map<String, Object> validateOK() {
        return validateOK(null);
    }

    public static Map<String, Object> validateError() {
        return validateError(null);
    }

    public static Map<String, Object> validateOK(Object message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ok", message == null ? SpringContextUtil.getMessage("response.validation.ok") : message);
        return map;
    }

    public static Map<String, Object> validateError(Object message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("error", message == null ? SpringContextUtil.getMessage("response.validation.error") : message);
        return map;
    }

    public static Map<String, Object> operateFail() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.operate.fail"));
        map.put(STATUSCODE, ERROR);
        return map;
    }

    /**
     * 自定义，登录超时
     *
     * @return
     */
    public static Map<String, Object> timeout() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.timeout"));
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
     *
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
        map.put(MESSAGE, SpringContextUtil.getMessage("response.operate.ok"));
        map.put(STATUSCODE, OK);
        map.put(NAVTABID, navTabId);
        return map;
    }

    public static Map<String, Object> ajaxOperateOK(String rel) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.operate.ok"));
        map.put(STATUSCODE, OK);
        map.put(CALLBACKTYPE, CLOSECURRENT);
        map.put(REL, rel);
        return map;
    }

    public static Map<String, Object> ajaxDeleteOK(String rel) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.delete.ok"));
        map.put(STATUSCODE, OK);
        map.put(REL, rel);
        return map;
    }

    public static Map<String, Object> operateOK() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.operate.ok"));
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
        map.put(MESSAGE, SpringContextUtil.getMessage("response.operate.fail"));
        map.put(STATUSCODE, ERROR);
        return map;
    }

    public static Map<String, Object> deleteOK(String navTabId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.delete.ok"));
        map.put(STATUSCODE, OK);
        map.put(NAVTABID, navTabId);
        return map;
    }

    public static Map<String, Object> deleteOK() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.delete.ok"));
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

    public static Map<String, Object> closeCurrent(Object navTabId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MESSAGE, SpringContextUtil.getMessage("response.operate.ok"));
        map.put(NAVTABID, navTabId);
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
