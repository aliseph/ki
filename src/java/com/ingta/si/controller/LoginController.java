package com.ingta.si.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ingta.framework.util.TwoTuple;
import com.ingta.framework.util.captcha.CaptchaServlet;
import com.ingta.framework.util.dwz.DWZResponse;
import com.ingta.si.admin.entity.Admin;
import com.ingta.si.admin.service.AdminService;
import com.ingta.si.util.InitParams;

@Controller
public class LoginController extends BaseController {
	@Resource
	private AdminService adminService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(ModelMap model, Integer c) {
		if (c != null) {
			switch (c) {
			case 1:
				model.addAttribute(DWZResponse.MESSAGE, DWZResponse.CAPTCHA_MISTAKE);
				break;
			case 2:
				model.addAttribute(DWZResponse.MESSAGE, DWZResponse.LOGIN_FAIL);
				break;
			case 3:
				model.addAttribute(DWZResponse.MESSAGE, DWZResponse.NOT_LOGIN);
				break;
			}
		}
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, String captcha, ModelMap model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		String validateC = (String) session.getAttribute(CaptchaServlet.CAPTCHAKEY);
		if (!captcha.equalsIgnoreCase(validateC)) {
			redirectAttributes.addAttribute("c", 1);
			return "redirect:login";
		}
		Admin admin = new Admin();
		admin.setAccount(username);
		admin.setPassword(password);
		TwoTuple<Boolean, Admin> tuple = adminService.login(admin);
		if (tuple.first) {
			session.setAttribute(InitParams.ADMIN, tuple.second);
			return "redirect:index";
		} else {
			redirectAttributes.addAttribute("c", 2);
			return "redirect:login";
		}
	}

	@RequestMapping(value = "error")
	public String error(String message, ModelMap model) {
		model.addAttribute("message", message);
		return "error/500";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(InitParams.ADMIN);
		return "login";
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.HEAD }, headers = "x-requested-with=XMLHttpRequest")
	@ResponseBody
	public Map<String, Object> loginDialog() {
		return DWZResponse.timeout();
	}
}
