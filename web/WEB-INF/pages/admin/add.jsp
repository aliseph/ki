<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
    <form method="post" action="admin/add" class="pageForm required-validate" callback="dialogAjaxDone">
        <div class="pageFormContent" layoutH="56">
            <dl>
                <dt>
                帐号：
                </dt>
                <dd>
                    <!--   -->
                    <input name="account" type="text" data-rule="required;length[2~20, true];remote[admin/validation]"
                           minlength="2" maxlength="20" size="35" alt="请输入管理员帐号2-20位" />
                </dd>
            </dl>
            <dl>
                <dt>
                用户名：
                </dt>
                <dd>
                    <!--  -->
                    <input type="text" name="name"  size="35" minlength="2" data-rule="required;length[2~20, true]"
                           maxlength="20" alt="请输入用户名，登录后显示 2-20位">
                </dd>
            </dl>

            <dl>
                <dt>
                密码：
                </dt>
                <dd>
                    <input type="password" name="password" size="35" data-rule="密码:required" minlength="4"
                           maxlength="20" alt="字母、数字、下划线 6-20位" />
                </dd>
            </dl>
            <dl>
                <dt>
                再次输入密码：
                </dt>
                <dd>
                    <input type="password" name="againpwd" size="35" data-rule="确认密码:required;match(password)" />
                </dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">
                                保存
                            </button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">
                                取消
                            </button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>