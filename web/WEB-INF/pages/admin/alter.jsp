<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="admin/alter" class="pageForm required-validate"
		callback="dialogAjaxDone">
		<input type="hidden" name="id" value="${admin.id}" />
		<div class="pageFormContent" layoutH="56">
			<dl>
				<dt>
					帐号：
				</dt>
				<dd>
					<input name="account" type="text" size="35" value="${admin.account}" readonly="readonly" />
				</dd>
			</dl>
			<dl>
				<dt>
					用户名：
				</dt>
				<dd>
					<input type="text" name="name" data-rule="required;length[2~20, true]" size="35" minlength="2"
						maxlength="20" alt="请输入用户名，登录后显示 2-20位" value="${admin.name}">
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
<script type="text/javascript">
	$(function() {
		$("#isWork").attr("value", "${admin.status}");
	});
</script>