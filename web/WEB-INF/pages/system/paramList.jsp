<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<a class="add" href="config/toAdd" target="dialog" rel="addConfig"
					mask="true" width="420" height="380"><span>添加参数</span> </a>
			</li>
			<li>
				<a class="edit" href="config/toAlter/{sid_user}" target="dialog"
					rel="alterConfig" mask="true" width="420" height="380"><span>修改参数</span>
				</a>
			</li>
			<li>
				<a class="delete" href="config/delete/{sid_user}" target="ajaxTodo" 
					title="确定要删除”{sid_user}“吗?"><span>删除参数</span>
				</a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="10%" align="left">
					配置编码
				</th>
				<th width="20%" align="left">
					配置名称
				</th>
				<th width="20%" align="left">
					配置值
				</th>
				<th width="10%" align="center">
					配置状态
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="config" items="${configList}" varStatus="status">
				<tr target="sid_user" rel="${config.sysconfigcode}" rel_name="${config.sysconfigname }">
					<td>
						${config.sysconfigcode}
					</td>
					<td>
						${config.sysconfigname}
					</td>
					<td>
						${config.sysconfigvalue}
					</td>
					<td>
						<c:if test="${config.sfqy == 1}">
							启用
						</c:if>
						<c:if test="${config.sfqy != 1}">
							禁用
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
