package com.ingta.si.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingta.framework.util.dwz.DWZResponse;
import com.ingta.si.admin.entity.Admin;
import com.ingta.si.util.InitParams;

public class BaseController {

    private static final Log logger = LogFactory.getLog(BaseController.class);

    protected String errorsToString(Errors errors) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors.getAllErrors()) {
            sb.append(error.getDefaultMessage()).append("；");
        }
        return sb.toString();
    }

    private String getParameterString(Map<String, String[]> parameterMap) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Entry<String, String[]>> iter = parameterMap.entrySet().iterator(); iter
                .hasNext();) {
            Entry<String, String[]> element = (Entry<String, String[]>) iter.next();
            Object strKey = element.getKey();
            String[] value = (String[]) element.getValue();
            sb.append(strKey.toString()).append("=");
            for (int i = 0; i < value.length; i++) {
                sb.append(value[i]).append(",");
            }
        }
        return sb.toString();
    }

    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> handlerException(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, Exception e) throws IOException {
        logger.error("访问路径：" + request.getRequestURI());
        logger.error("提交数据：" + getParameterString(request.getParameterMap()));
        Admin admin = getUser(session);
        if (admin != null) {
            logger.error("操作员：" + getUser(session).getAccount());
        }
        logger.error("-----------------------------------------------", e);
        String message = "操作错误，请联系管理员，错误类型：" + e.getClass().getSimpleName();
        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.isEmpty(requestType) || !requestType.equals("XMLHttpRequest")) {
            response.sendRedirect(request.getContextPath() + "/error?message=" + message);
        }
        return DWZResponse.operateFail(message);
    }

    protected Admin getUser(HttpSession session) {
        return (Admin) session.getAttribute(InitParams.ADMIN);
    }

    /**
     * 取得客户端真实ip
     *
     * @param request
     * @return 客户端真实ip
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
