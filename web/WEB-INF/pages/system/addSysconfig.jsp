<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="config/add"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>
					配置编码：
				</dt>
				<dd>
					<input name="sysconfigcode" class="required number" type="text" minlength="1"
						maxlength="8" size="35" alt="请输入配置编码1-10位"
						remote="config/valiConfig" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					配置名称：
				</dt>
				<dd>
					<input type="text" name="sysconfigname" class="required" size="35"
						minlength="2" maxlength="50" alt="请输入配置名称 1-25位">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					配置值：
				</dt>
				<dd>
					<input type="text" name="sysconfigvalue" class="required" size="35"
						minlength="2" maxlength="50" alt="请输入配置值 1-50位">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					是否启用：
				</dt>
				<dd>
					<select name="sfqy">
						<option value="1" selected="selected">
							是
						</option>
						<option value="0">
							否
						</option>
					</select>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
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