<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
	String requestType = request.getHeader("X-Requested-With");
	if (requestType != null && !requestType.equals("")
			&& requestType.equals("XMLHttpRequest")) {
		//response.setHeader("session-status", "timeout");
		out.print("服务器异常！");
	} else {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>服务器异常</title>
	</head>
	<body>
		<div>
			<div>
				<ul>
					<li>
						${message }
					</li>
					<li>
						1、请转到
						<a href="${URL['INDEXURL'] }">主页</a> 。
					</li>
					<li>
						2、或单击
						<a href="javascript:history.back(1)">后退</a> 按钮，然后尝试其他链接。
					</li>
				</ul>
			</div>
		</div>
	</body>
</html>
<%
	}
%>