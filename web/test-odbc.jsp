<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/8/1
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*"%>

<%
    //定义数据库驱动程序
    String JDBCDRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";

    //定义数据库的链接地址
    //testDB为配置的数据源名称
    String DBURL = "jdbc:odbc:testDB";

    //定义数据库连接对象
    Connection conn = null;

    //定义数据库Statement对象 用于操作数据库
    PreparedStatement stmt = null;

    //定义sql语句
    String sql = null;%>
<%
    //加载驱动程序
    try {
        Class.forName(JDBCDRIVER);
    } catch (Exception e) {
        out.println("驱动程序加载失败！");
    }
    try{
        //连接数据库
        conn = DriverManager.getConnection(DBURL);
    }catch(Exception e){
        out.println("数据库连接失败！");
    }
    // 操作数据库
    // 通过Connection对象实例化PreparedStatement对象
    try{
        //待执行的sql语句
        sql = "insert into test (username) values('依古比古')";
        stmt = conn.prepareStatement(sql);
        stmt.execute(sql);
    }catch(Exception e){
        out.println("数据库操作失败！");
    }finally {
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("关闭出错！");
        }
    }
%>

<!DOCTYPE html>
<html>
<head>

    <title>用JDBC-ODBC连接Access数据库实例</title>


</head>

<body>

</body>
</html>
