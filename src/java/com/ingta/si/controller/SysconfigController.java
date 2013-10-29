package com.ingta.si.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ingta.framework.util.dwz.DWZResponse;
import com.ingta.si.admin.entity.BSysconfig;
import com.ingta.si.admin.service.SysconfigService;

/**
 * 系统参数管理
 *
 */
@Controller
@RequestMapping("/config")
public class SysconfigController extends BaseController {

    @Resource
    private SysconfigService sysconfigService;

    // private static final String REL = "listConfig";
    /**
     * 列出所有配置信息
     */
    @RequestMapping(value = "/list")
    public String getAllConfig(ModelMap model) throws Exception {
        model.addAttribute("configList", sysconfigService.findAll(null));
        return "system/paramList";
    }

    /**
     * 到添加系统参数页面
     */
    @RequestMapping(value = "/toAdd")
    public String toAddConfig(ModelMap modelMap) {
        return "system/addSysconfig";
    }

    /**
     * 添加系统参数
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addConfig(BSysconfig config) {
        try {
            sysconfigService.add(config);
            return DWZResponse.operateOK("configList");
        } catch (Exception e) {
            e.printStackTrace();
            return DWZResponse.operateFail();
        }
    }

    /**
     * 到修改系统参数页面
     */
    @RequestMapping(value = "/toAlter/{sysconfigcode}")
    public String toAlterConfig(@PathVariable("sysconfigcode") String sysconfigcode, ModelMap model) {
        BSysconfig sysconfig = sysconfigService.findById(sysconfigcode);
        model.addAttribute("sysconfig", sysconfig);
        return "system/alterSysconfig";
    }

    /**
     * 修改系统参数
     */
    @RequestMapping(value = "/alter", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> alterConfig(BSysconfig config) {
        sysconfigService.update(config);
        return DWZResponse.closeCurrent(DWZResponse.OPERATE_OK, "configList");
    }

    /**
     * 删除系统参数
     *
     */
    @RequestMapping(value = "/delete/{sysconfigcode}", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteQuoComp(@PathVariable("sysconfigcode") String sysconfigcode) {
        this.sysconfigService.deleteInDB(sysconfigcode);
        return DWZResponse.deleteOK("configList");
    }

    /**
     * ajax验证参数是否已经存在
     */
    @RequestMapping(value = "/valiConfig", method = RequestMethod.GET)
    @ResponseBody
    public boolean valiConfig(String sysconfigcode) throws Exception {
        return sysconfigService.validation(sysconfigcode);
    }
}
