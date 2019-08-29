<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/8/2
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Context context=new InitialContext();
    try{
        //得到JNDI容器
        Context envContext=(Context)context.lookup("java:comp/env");
        DataSource ds=(DataSource)envContext.lookup("test-jdni-datasource");
        System.out.println(ds.getConnection().getClass());
        ds.getLogWriter().print("##################");
        PreparedStatement ps=ds.getConnection().prepareStatement("select * from t_user");
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString("user_name"));
        }
    }catch(Exception e){
        e.printStackTrace();
    }
%>
</body>
</html>
