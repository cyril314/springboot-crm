<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>
<%
    Throwable ex = null;
    if (exception != null)
        ex = exception;
    if (request.getAttribute("javax.servlet.error.exception") != null)
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
    //记录日志
    Logger logger = LoggerFactory.getLogger("error.jsp");
    logger.error(ex.getMessage(), ex);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>System error!</title>
    <link rel="stylesheet" href="${ctx}/static/css/error.css">
</head>
<body>
<div class="error__container">
    <div class="error__code">
        <c:forEach items="${status.toString().split('')}" var="v">
            <p>${v}</p>
        </c:forEach>
    </div>
    <div class="error__title">System error Page</div>
    <div class="error__description">Internal system error</div>
    <button class="action">Go Home</button>
</div>
</body>
</html>
