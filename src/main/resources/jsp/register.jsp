<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户注册</title>
    <!-- 导入Ext -->
    <link href="/static/ext/resources/css/ext-all.css" rel="stylesheet">
    <script type="text/javascript" src="/static/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="/static/ext/ext-all.js"></script>
    <script type="text/javascript" src="/static/ext/build/locale/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/register.js"></script>
    <script>
        function callback(data) {
            if (data == true) {
                {
                    success: false;
                    errors: {
                        userName: '用户名不能为空'
                    }
                }
            }
        }

        function valid(userName) {
            RegisterAction.validUser(userName, callback);
        }
    </script>
</head>
<body></body>
</html>