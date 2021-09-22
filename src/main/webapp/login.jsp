<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login_bg">

<div class="login_box">
    <header class="login_header">
        <h1>超市订单管理系统</h1>
    </header>
    <form class="login_form" action="${pageContext.request.contextPath}/login.do" method="post">
        <div class="inputbox">
            <label for="username">用户名：</label>
            <input type="text" name="username" id="username" placeholder="请输入用户名" required>
        </div>
        <div class="inputbox">
            <label for="password">密码：</label>
            <input type="password" name="password" id="password" placeholder="请输入密码" required><br>
        </div>
        <div class="login_info">${requestScope.error}</div>
        <div class="subBtn">
            <input type="submit" name="登录"/>
            <input type="reset" name="重置"/>
        </div>

    </form>
</div>


</body>
</html>
