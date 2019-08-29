<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/7/28
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<style>
    a:hover{
        color: #8e24aa;
    }
</style>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login failed!</title>
    <!--
    Template 2105 Input
    http://www.tooplate.com/view/2105-input
    -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/materialize.min.css">
    <link rel="stylesheet" href="css/tooplate.css">
</head>
<%--<body>--%>
<%--<h1>用户名或密码错误！</h1>--%>
<%--<%=request.getRequestURI()%>--%>
<%--<h2>3秒后将自动跳转至登录页面...如果没有跳转请点击<a href="login.jsp" style="color:#1693F3">这里</a>！</h2>--%>
<%--<%--%>
<%--    response.setHeader("refresh","3;login.jsp");--%>
<%--%>--%>

<%--<img src="images/waiting.gif" width="400" height="272" />--%>
<%--</body>--%>
<%--</html>--%>
<body id="survey" class="font-weight-light">
<div class="container tm-container-max-800">
    <div class="row">
        <div class="col-12">
            <header class="mt-5 mb-5 text-center">
                <h3 class="font-weight-light tm-form-title">Login failed!</h3>
                <p class="tm-form-description">
                    Failed to login.
                    After 3s you will be guided into Login page...If the automatic redirection does't occur, please click <a style="font-size: large" href="login.jsp">Here</a> to retry.
                <%
                    response.setHeader("refresh","3;login.jsp");
                %>

                <img src="images/waiting.gif" width="400" height="272" />
                </p>
            </header>

        </div>
    </div>
    <footer class="row tm-mt-big mb-3">
        <div class="col-xl-12">
            <p class="text-center grey-text text-lighten-2 tm-footer-text-small">
                2019 BankSystem.
            </p>
        </div>
    </footer>
</div>

<script src="js/jquery-3.2.1.slim.min.js"></script>
<script src="js/materialize.min.js"></script>
<script>
    $(document).ready(function () {
        $('select').formSelect();
    });
</script>
</body>

</html>

