<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="pageContent">
    <form method="post" action="update" class="pageForm required-validate"
          onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <dl>
                <dt>
                旧密码：
                </dt>
                <dd>
                    <input type="password" name="pass" size="35"
                           class="required" alt="请输入原始密码" remote="valiPass" />
                    <span class="info"></span>
                </dd>
            </dl>
            <dl>
                <dt>
                新密码：
                </dt>
                <dd>
                    <input id="w_validation_pwd" type="password" name="adminPassword"
                           size="35" class="required alphanumeric" minlength="4"
                           maxlength="20" alt="请输入新密码" />
                    <span class="info"></span>
                </dd>
            </dl>
            <dl>
                <dt>
                再次输入密码：
                </dt>
                <dd>
                    <input type="password" name="password" size="35" class="required"
                           equalto="#w_validation_pwd" alt="请再次输入新密码" />
                    <span class="info"></span>
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