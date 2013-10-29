<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <base href="<%=basePath%>">
            <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
            <title>后台管理</title>
            <link rel="stylesheet" type="text/css" href="static/login/style.css" />
            <script src="static/dwz-ria/js/jquery-1.7.2.min.js" type="text/javascript"></script>
            <script type="text/javascript">
                function changeImg() {
                    var imgSrc = $("#imgObj");
                    var src = imgSrc.attr("src");
                    imgSrc.attr("src", chgUrl(src));
                }
                //时间戳    
                //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳    
                function chgUrl(url) {
                    var timestamp = (new Date()).valueOf();
                    url = url.substring(0, 17);
                    if ((url.indexOf("&") >= 0)) {
                        url = url + "×tamp=" + timestamp;
                    } else {
                        url = url + "?timestamp=" + timestamp;
                    }
                    return url;
                }
                var rightCode = false;
                function isRightCode() {
                    var code = $("#veryCode").attr("value");
                    if (code.length == 4) {
                        code = "c=" + code;
                        $.ajax({
                            type: "POST",
                            url: "code/ResultServlet",
                            data: code,
                            success: function(data) {
                                var json = eval('(' + data + ')');
                                if ((json.msg) != "true") {
                                    $('.clr').html('*验证码错误').removeClass("hidd").css("color", "red");
                                    rightCode = false;
                                } else {
                                    $('.clr').html('*验证码正确').removeClass("hidd").css("color", "green");
                                    rightCode = true;
                                }
                            }
                        });
                    }
                }
                function checkInput() {
                    var name = $("#name").val();
                    var password = $("#password").val();
                    if (!rightCode || name.length < 1 || password.length < 1) {
                        $('.clr').html('信息填写不完整，请输入完整的信息').removeClass("hidd").css("color", "red");
                    } else {
                        $('form').submit();
                    }
                }
                $("body").keydown(function() {
                    if (event.keyCode == 13) {
                        alert(11111111);
                        checkInput();
                    }
                });
                $(function() {
                    var msg = '${message || notice }';
                    if (msg != '') {
                        $(".clr").removeClass("hidd");
                    }
                });
            </script>
    </head>
    <body>
        <div id="main">
            <div class="content">
                <div class="logo">
                </div>
                <div class="clr hidd">
                    <label style="color: red; width: 100px">${message }</label>
                </div>
                <form action="login" method="post" name="form">
                    <div class="user">
                        <p>
                            <span>用户名：</span>
                            <input id="name" type="text" id="account" name="username" />
                        </p>
                        <p>
                            <span>密&nbsp;&nbsp;码：</span>
                            <input id="password" type="password" id="password"
                                   name="password" />
                        </p>
                        <p>
                            <span>验证码：</span>
                            <input onkeyup="isRightCode();" id="veryCode" type="text" name="captcha" style="width: 78px;" maxlength="4"/>
                            <span>
                                <a onclick="changeImg()" style="cursor: pointer" title="换验证码">
                                    <img id="imgObj"  style="vertical-align: middle;" src="code/VCodeServlet" width="65" height="20" /> 
                                </a> 
                            </span>
                        </p>
                    </div>
                    <div class="login">
                        <input type="button" onclick="checkInput();" class="btn"
                               id="button" value="登&nbsp;录">
                    </div>
                </form>
            </div>
            <div class="footer">
                合肥英塔信息技术有限公司 版权所有 Copyright◎2010-2012
            </div>
        </div>
    </body>
</html>