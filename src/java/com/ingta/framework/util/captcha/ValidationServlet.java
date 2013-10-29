package com.ingta.framework.util.captcha;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 8410689218513914573L;

        @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

        @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String validateC = (String) request.getSession().getAttribute(
				CaptchaServlet.CAPTCHAKEY);
		String veryCode = request.getParameter("c");
		PrintWriter out = response.getWriter();
		Map<String, String> json = new HashMap<String, String>();
		if (StringUtils.isBlank(veryCode)) {
			json.put("msg", "blank");
			out.println(JSON.toJSONString(json));
		} else {
			if (validateC.equalsIgnoreCase(veryCode)) {
				json.put("msg", "true");
				out.println(JSON.toJSONString(json));
			} else {
				json.put("msg", "false");
				out.println(JSON.toJSONString(json));
			}
		}
		out.flush();
		out.close();
	}
}
