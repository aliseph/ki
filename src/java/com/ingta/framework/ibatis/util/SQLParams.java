package com.ingta.framework.ibatis.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者 杨永兴:
 * @version 创建时间：2013-5-8 上午10:47:08 说明：数据库查询参数，一般用于查询列表
 */
public class SQLParams implements Serializable {

    private static final long serialVersionUID = -4502505206809185321L;
    private String role;// 用户所属角色
    private String permission;// 用户权限
    private String startTime;// 起始时间，一般从当前时间开始
    private String endTime;// 结束时间，向前推一段时间后的时间，早于起始时间
    private Map<String, Object> otherParams;// 查询所需要的其它参数

    public SQLParams(String role, String permission, String startTime, String endTime) {
        this.role = role;
        this.permission = permission;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SQLParams(String role, String permission) {
        this.role = role;
        this.permission = permission;
    }

    public SQLParams() {
    }

    /**
     * 把QueryParams对象组装为一个map对象
     *
     * @return map对象
     */
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("role", this.role);
        map.put("permission", this.permission);
        map.putAll(this.otherParams);
        map.put("startTime", this.startTime);
        map.put("endTime", this.endTime);
        return map;
    }

    public Map<String, Object> getOtherParams() {
        return otherParams;
    }

    public void setOtherParams(Map<String, Object> otherParams) {
        this.otherParams = otherParams;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
