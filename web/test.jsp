<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/7/24
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%
    // 编码，解决中文乱码
//    String str = URLEncoder.encode(request.getParameter("name"),"utf-8");
    // 设置 name 和 url cookie
    Cookie name = new Cookie("name",
            "张三");
    Cookie url = new Cookie("url",
            "url");

    // 设置cookie过期时间为24小时。
//    name.setMaxAge(0);
//    url.setMaxAge(255555555);

    // 在响应头部添加cookie
    response.addCookie( name );
    response.addCookie( url );
    for(int i=0;i<request.getCookies().length;i++) {
        out.println(request.getCookies()[i].getName());
        out.println(request.getCookies()[i].getMaxAge());
    }
%>
<html>
<head>
    <title>设置 Cookie</title>
</head>
<body>

<h1>设置 Cookie</h1>

<ul>
    <li><p><b>网站名:</b>
        <%= request.getParameter("name")%>
    </p></li>
    <li><p><b>网址:</b>
        <%= request.getParameter("url")%>
    </p></li>
</ul>
</body>
</html>