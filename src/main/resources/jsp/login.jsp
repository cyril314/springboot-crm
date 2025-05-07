<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    String basePath = path + request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>系统登录页面</title>
    <!-- 资源包 -->
    <link rel='stylesheet' type='text/css' href="/static/ext/resources/css/ext-all.css">
    <link rel='stylesheet' type='text/css' href="/static/ext/resources/css/xtheme-gray.css">
    <script type="text/javascript" src="/static/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="/static/ext/ext-all.js"></script>
    <script type="text/javascript" src="/static/ext/build/locale/ext-lang-zh_CN.js"></script>
    <style type="text/css">
        .user {
            background: url(/static/images/man.gif) no-repeat 1px 2px;
        }

        .key {
            background: url(/static/images/key.gif) no-repeat 1px 2px;
        }

        .key, .user {
            background-color: #FFFFFF;
            padding-left: 20px;
            font-weight: bold;
            color: #000033;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="/static/js/login.js"></script>
<div id="loginWin" style="height:500px; width:500px; margin:0 auto;"></div>
<div id="loading-mask" style="">
    <div id="loading">
        <div style="text-align: center; padding-top: 26%">
            <img src="/static/images/Loader.gif" width="32" height="32" style="margin-right: 8px;" align="absmiddle"/>
            Loading...
        </div>
    </div>
</div>
</body>
</html>