package com.ingta.si.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingta.framework.ibatis.util.Page;
import com.ingta.framework.ibatis.util.SQLParams;
import com.ingta.framework.util.dwz.DWZResponse;
import com.ingta.si.admin.entity.Admin;
import com.ingta.si.admin.service.AdminService;

/**
 * 用户权限管理controller
 * @author ingta_肖杰
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {
	@Resource
	private AdminService adminService;

	private static final String REL = "adminList";// 管理员列表navTab

	@RequestMapping(value = "/list")
	public String List(Integer pageNum, String status, ModelMap model, Integer numPerPage) {
		SQLParams params = new SQLParams();
		Map<String, Object> otherParams = new HashMap<String, Object>();
		otherParams.put("status", status == null ? 1 : status);
		params.setOtherParams(otherParams);
		Page<Admin> pagingInfo = new Page<Admin>();
		pagingInfo.setParams(params);
		if (numPerPage != null && numPerPage != 0) pagingInfo.setNumPerPage(numPerPage);
		if (pageNum != null) pagingInfo.setPageNum(pageNum);
		pagingInfo = adminService.findAllByPaging(pagingInfo);
		model.addAllAttributes(pagingInfo.toMap(REL));
		return "admin/list";
	}

	@RequestMapping(value = "/show")
	public String get(String id, ModelMap model) {
		Admin admin=adminService.findById(id);
		System.out.println(admin);
		model.addAttribute("admin",admin);
		return "admin/alter";
	}

	@RequestMapping(value = "/validation")
	@ResponseBody
	public Map<String, Object> vali(String account) {
		if (adminService.validation(account)) return DWZResponse.validateOK();
		else return DWZResponse.validateError();
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public Map<String, Object> delete(String id) {
		adminService.deleteByStatus(id);
		return DWZResponse.operateOK(DWZResponse.DELETE_OK, REL);
	}

	@RequestMapping(value = "/recover")
	@ResponseBody
	public Map<String, Object> recover(String id) {
		adminService.recover(id);
		return DWZResponse.operateOK(DWZResponse.RESET_OK, REL);
	}

	@RequestMapping(value = "/reset")
	@ResponseBody
	public Map<String, Object> resetPass(String id) {
		adminService.resetPassword(id);
		return DWZResponse.operateOK(DWZResponse.RESET_PASSWORD_OK, REL);
	}

	@RequestMapping(value = "/toAdd")
	public String toAdd() {
		return "admin/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(@Valid Admin admin, Errors errors) {
		if (errors.hasErrors()) {
			return DWZResponse.operateFail(errorsToString(errors));
		}
		adminService.add(admin);
		return DWZResponse.closeCurrent(DWZResponse.OPERATE_OK, REL);
	}

	@RequestMapping(value = "/alter", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> alter(Admin admin) {
		adminService.update(admin);
		return DWZResponse.closeCurrent(DWZResponse.OPERATE_OK, REL);
	}

}
