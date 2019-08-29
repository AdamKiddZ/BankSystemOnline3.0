<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/7/22
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%--<%@ page language="java" session="false" %>--%>
<html>
  <head>
    <title>$Title$</title>
<%--  <%request.getRequestDispatcher("index1.jsp").forward(request,response);%>--%>
  </head>

<body>
  <%--  <%response.sendRedirect("index1.jsp");%>--%>
<%--  <%out.flush();%>--%>
<%--    <%response.setHeader("refresh","3,index1.jsp");%>--%>
<%--  <jsp:forward page="index1.jsp">--%>
<%--    <jsp:param name="user1" value="zs"/>--%>
<%--    <jsp:param name="user1" value="张三"/>--%>
<%--    <jsp:param name="user2" value="张三"/>--%>
<%--  </jsp:forward>--%>
  <form action="index1.jsp" id="content">
    <input type="checkbox" name="users" value="zs">张三</input>
    <input type="checkbox" name="users" value="ls">李四</input>
    <input type="checkbox" name="users" value="ww">王五</input>
    <button type="submit" form="content">提交</button>
  </form>
  <%
    session.setAttribute("name","天线宝宝");
    session.invalidate();
  %>
  ${sessionScope.name}
<%--<%request.getSession(false);%>--%>
<%--<%=session.getId()%>--%>
  $END$
  </body>
</html>
