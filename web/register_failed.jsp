<%--
  Created by IntelliJ IDEA.
  User: Adam
  Date: 2019/8/12
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Register failed!</title>
    <!--
    Template 2105 Input
    http://www.tooplate.com/view/2105-input
    -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:300,400">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/materialize.min.css">
    <link rel="stylesheet" href="css/tooplate.css">
</head>

<body id="survey" class="font-weight-light">
<div class="container tm-container-max-800">
    <div class="row">
        <div class="col-12">
            <header class="mt-5 mb-5 text-center">
                <h3 class="font-weight-light tm-form-title">Register failed!</h3>
                <p class="tm-form-description">Your registry encountered some problems.Click <a style="font-size: large" href="login.jsp">Here</a> to retry.</p>
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
