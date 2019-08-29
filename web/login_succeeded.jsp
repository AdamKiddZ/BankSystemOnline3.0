<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/7/28
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<style type="text/css">--%>
<%--    .setting {--%>
<%--        font-size: 30px;--%>
<%--        color: #F66;--%>
<%--    }--%>
<%--    a:link,a:visited{--%>
<%--        text-decoration:none;  /*超链接无下划线*/--%>
<%--    }--%>
<%--</style>--%>
<html>
<head>
<%--    <title>Login successfully!</title>--%>
<%--    <h1 align="center">Hello ,welcome to here.${param.userid}</h1>--%>
</head>
<body>
<%--<h2 align="center">退出登录请点击<a href="logout.jsp" class="setting">此处<img src="images/ToLogout.png" width="36" height="36" /></a>！</h2>--%>
<%
    response.setHeader("refresh","1;URL=home.jsp");
//    session.invalidate();
%>
</body>
</html>
