<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在线考试系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap.min.js"></script>
    <style>
        .container {
            width: 300px;
            margin-top: 50px
        }

        .form-control {
            margin-bottom: 10px;
        }

        body {
            background: #eee;
        }
    </style>
    <script language="JavaScript">
        function getQueryVariable(variable) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] == variable) {
                    return pair[1];
                }
            }
            return (false);
        }
    </script>

</head>

<body>
<script>
    var state = getQueryVariable("state");
    if (state == "error") {
        alert("登陆失败，用户名或密码错误！");
    }
</script>
<div class="container">
    <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">
        <h2 class="form-signin-heading">在线考试系统 - 登录</h2>
        <input type="text" id="username" name="username" class="form-control" placeholder="用户名" required autofocus>
        <input type="password" id="password" name="password" class="form-control" placeholder="密码" required>
        <div class="radio">
            <label class="radio-inline">
                <input type="radio" name="userLevel" id="levelStudent" value="student" checked>
                学生用户 </label>
            <label class="radio-inline">
                <input type="radio" name="userLevel" id="levelAdmin" value="admin">
                管理员 </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        <a href="toregister">未注册？点这里</a>
    </form>
</div>
</body>
</html>