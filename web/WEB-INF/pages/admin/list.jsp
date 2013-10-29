<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pageContent">
	<div class="unitBox" style="float: left; display: block; overflow: auto; width: 480px;">
		<div class="pageContent" style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
			<h2 class="contentTitle">
				管理员
			</h2>
			<div class="panelBar">
				<ul class="toolBar">
					<c:if test="${status eq '1'}">
						<li>
							<a class="add" href="admin/toAdd" target="dialog" rel="addAdmin" mask="true" width="460"
								height="270"><span>添加管理员</span> </a>
						</li>
						<li>
							<a class="delete" href="admin/delete?id={sid_admin}" target="ajaxTodo" title="确定要删除“{sid_admin}”吗?"><span>删除管理员</span>
							</a>
						</li>
						<li>
							<a class="edit" href="admin/show?id={sid_admin}" target="dialog" rel="alterAdmin" mask="true"
								width="460" height="230" title="修改“{sid_admin}”"><span>修改管理员</span> </a>
						</li>
						<li class="line">
							line
						</li>
						<li>
							<a class="icon" href="admin/reset?id={sid_admin}" target="ajaxTodo" title="确定要重置“{sid_admin}”的密码为“111111”吗?"><span>密码重置</span>
							</a>
						</li>
						<li>
							<a class="icon" rel="adminList" target="navTab" href="admin/list?status=0"><span>管理员回收站</span>
							</a>
						</li>
					</c:if>
					<c:if test="${status eq '0'}">
						<li>
							<a class="edit" href="admin/recover?id={sid_admin}" target="ajaxTodo" title="确定要恢复吗?"><span>恢复管理员</span>
							</a>
						</li>
						<li class="line">
							line
						</li>
						<li>
							<a class="icon" rel="adminList" target="navTab" href="admin/list"><span>管理员列表</span> </a>
						</li>
					</c:if>
				</ul>
			</div>
			<table class="table" width="100%" layoutH="90">
				<thead>
					<tr>
						<th width="10%" align="center">
							序号
						</th>
						<th width="30%" align="center">
							登录帐号
						</th>
						<th width="25%" align="center">
							真实姓名
						</th>
						<th width="20%" align="center">
							帐号状态
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="admin" items="${adminList}" varStatus="status">
						<tr target="sid_admin" rel="${admin.id}" rel_name=${admin.account }>
							<td>
								${status.count}
							</td>
							<td>
								${admin.account}
							</td>
							<td>
								${admin.name}
							</td>
							<td>
								${admin.status==1?"启用":"禁用"}
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div id="adminMenusTree" class="unitBox" style="margin-left: 486px;">
		<div class="pageContent" style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
			<h2 class="contentTitle">
				可见菜单
			</h2>
			<div layoutH="41">
				<ul style="line-height: 21px; background: #FFF;" id="aumenus" class="ztree"></ul>
			</div>
		</div>
	</div>
</div>

