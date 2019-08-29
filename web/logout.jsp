<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/7/28
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<meta charset="utf-8">
<%
    response.setHeader("refresh","1;URL=index.html");
    session.invalidate();
%>
