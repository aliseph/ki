<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>WEB应用后台管理系统</title>
        <link href="static/dwz-ria/themes/default/style.css" rel="stylesheet" type="text/css"
              media="screen" />
        <link href="static/dwz-ria/js/validator/jquery.validator.css" rel="stylesheet" type="text/css"
              media="screen" />
        <link href="static/dwz-ria/themes/css/core.css" rel="stylesheet" type="text/css" media="screen" />
        <link href="static/dwz-ria/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
        <link href="static/tree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"
              media="screen" />
        <style type="text/css">
            <!--
            .title {
                color: #3399ff;
                font-size: 20px;
                text-align: left;
                line-height: 50px;
            }
            -->
        </style>
        <!--[if IE]>
            <link href="static/dwz-ria/themes/css/ieHack.css" rel="stylesheet" type="text/css"/>
        <![endif]-->
        <!--【可选】js加速 -->
        <script src="static/dwz-ria/js/speedup.js" type="text/javascript"></script>
        <!--【必须】jQuery库 -->
        <script src="static/dwz-ria/js/jquery-1.7.2.min.js" type="text/javascript"></script>
        <!--【必须】表单验证 -->
        <script src="static/dwz-ria/js/validator/jquery.validator.js" type="text/javascript"></script>
        <!--【可选】用于IE6弹出层不能盖住select问题 -->
        <script src="static/dwz-ria/js/jquery.bgiframe.js" type="text/javascript"></script>
        <!--【可选】editor在线编辑器 -->
        <script type="text/javascript" src="static/dwz-ria/js/jquery.bgiframe.js"></script>

        <script src="static/dwz-ria/js/dwz.core.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.util.date.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.barDrag.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.drag.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.tree.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.accordion.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.ui.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.switchEnv.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.alertMsg.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.contextmenu.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.navTab.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.tab.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.resize.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.dialog.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.dialogDrag.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.cssTable.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.stable.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.taskBar.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.ajax.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.pagination.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.database.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.datepicker.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.effects.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.panel.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.checkbox.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.history.js" type="text/javascript"></script>
        <script src="static/dwz-ria/js/dwz.combox.js" type="text/javascript"></script>

        <!--【必须】 dwz.*.js 打包到dwz.min.js中 
        <script src="static/dwz-ria/bin/dwz.min.js" type="text/javascript"></script>-->
        <!-- 【可选】 用于国际化 -->
        <script src="static/dwz-ria/js/dwz.regional.zh.js" type="text/javascript"></script>
        <script type="text/javascript" src="static/tree/js/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript">
            $(function() {
                DWZ.init("static/dwz-ria/dwz.frag.xml", {
                    //loginUrl : "login.jsp",
                    //loginTitle : "登录", // 弹出登录对话框
                    loginUrl: "login", // 跳到登录页面
                    statusCode: {
                        ok: 200,
                        error: 300,
                        timeout: 301
                    }, //【可选】
                    pageInfo: {
                        pageNum: "pageNum",
                        numPerPage: "numPerPage"
                    }, //【可选】
                    debug: false, // 调试模式 【true|false】
                    callback: function() {
                        initEnv();
                    }
                });
            });
        </script>
    </head>
    <body scroll="no">
        <div id="layout">
            <div id="header">
                <div class="headerNav">
                    <a class="logo" href="#">标志</a>
                    <h1 class="title">
                        WEB应用后台管理
                    </h1>
                    <ul class="nav">
                        <li>
                            <a href="updatePassword" target="dialog" width="600"> ${ADMIN.name } </a>
                        </li>
                        <li>
                            <a href="logout">退出</a>
                        </li>
                    </ul>
                </div>
                <!-- navMenu -->
            </div>
            <div id="leftside">
                <div id="sidebar_s">
                    <div class="collapse">
                        <div class="toggleCollapse">
                            <div></div>
                        </div>
                    </div>
                </div>
                <div id="sidebar">
                    <div class="toggleCollapse">
                        <h2>
                            后台管理菜单
                        </h2>
                        <div>
                            收缩
                        </div>
                    </div>
                    <div class="accordion" fillSpace="sidebar">
                        <!-- rel 里面的值决定是否在同一个面板打开 各自的url写死在里面后期整合再循环-->
                        <div class="accordionHeader">
                            <h2>
                                <span>Folder</span>后台配置
                            </h2>
                        </div>
                        <div class="accordionContent">
                            <ul class="tree treeFolder">
                                <li>
                                    <a href="admin/list" target="navTab" rel="adminList">管理员配置</a>
                                </li>
                            </ul>
                        </div>
                        <div class="accordionHeader">
                            <h2>
                                <span>Folder</span>参数配置
                            </h2>
                        </div>
                        <div class="accordionContent">
                            <ul class="tree treeFolder">
                                <li>
                                    <a href="config/list" target="navTab" rel="configList">系统参数配置管理</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div id="container">
                <div id="navTab" class="tabsPage">
                    <div class="tabsPageHeader">
                        <div class="tabsPageHeaderContent">
                            <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                            <ul class="navTab-tab">
                                <li tabid="main" class="main">
                                    <a href="javascript:;"> <span><span class="home_icon">后台管理</span> </span> </a>
                                </li>
                            </ul>
                        </div>
                        <div class="tabsLeft">
                            left
                        </div>
                        <!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                        <div class="tabsRight">
                            right
                        </div>
                        <!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                        <div class="tabsMore">
                            more
                        </div>
                    </div>
                    <ul class="tabsMoreList">
                        <li>
                            <a href="javascript:;">后台管理</a>
                        </li>
                    </ul>
                    <div class="navTab-panel tabsPageContent layoutBox">
                        <div class="page unitBox">
                            <div class="accountInfo">
                                <p>
                                    <span>标准评估网品牌评估系统后台管理</span>
                                </p>

                                <p>
                                    网站:
                                    <a href="#" target="_blank">http://brand.yingtaw.com/</a>
                                </p>
                            </div>
                            <div class="pageFormContent" layoutH="80" style="margin-right: 230px">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="footer">
            Copyright &copy; 2012
            <a href="demo_page2.html" target="dialog">合肥英塔信息技术有限公司</a>
        </div>
    </body>
</html>