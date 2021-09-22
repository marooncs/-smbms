<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>超市订单管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body class="public_body">
    <header class="publicHeader">
        <h1>超市订单管理系统</h1>
        <div class="publicHeaderR">
            <p><span style="color: red"> ${User_Session.userName}</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath}/jsp/login.out">退出</a>
        </div>
    </header>
    <section class="publicPanel">
        <div class="left">
            <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
            <nav>
                <ul class="list">
                    <li ><a href="${pageContext.request.contextPath }/jsp/bill.do?method=query">订单管理</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/provider.do?method=query">供应商管理</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/user.do?method=query">用户管理</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/pwdmodify.jsp">密码修改</a></li>
                    <li><a href="${pageContext.request.contextPath }/jsp/login.out">退出系统</a></li>
                </ul>
            </nav>
        </div>
        <%-- path:项目地址 --%>
        <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath}"/>
        <%-- 上一条链接的地址 --%>
        <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>

