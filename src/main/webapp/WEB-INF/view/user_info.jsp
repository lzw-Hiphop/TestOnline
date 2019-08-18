<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在线考试系统</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        body {
            background: #eee
        }

        .user-form span {
            width: 120px;
            display: inline-block;
            text-align: right;
        }

        .user-form h4 {
            padding-bottom: 10px;
        }

        input[type="submit"] {
            margin-left: 150px;
        }

        button[type="button"] {
            margin-left: 30px;
        }
    </style>
    <script language="javascript" type="text/javascript">
        function showOrHiddenPasswordText() {
            var p1 = document.getElementById("old-password");
            var p2 = document.getElementById("new-password");
            var p3 = document.getElementById("new-password2");
            p1.hidden = !p1.hidden;
            p2.hidden = !p2.hidden;
            p3.hidden = !p3.hidden;
        }

        function submitSave() {
            var h = document.getElementById("old-password");
            var p1 = document.getElementById("oldPassword");
            var p2 = document.getElementById("newPassword");
            var p3 = document.getElementById("newPassword2");
            if (h.hidden == false) {
                if (!p1.value) {
                    alert("错误，请输入原密码！");
                    return false;
                }
                if (!p2.value) {
                    alert("错误，请输入新密码！");
                    return false;
                }
                if (p2.value != p3.value) {
                    alert("错误，新密码与确认新密码不相同！");
                    return false;
                }
            }
            return true;
        }

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
    var password = getQueryVariable("password");
    if (state == "success") {
        alert("保存成功！");
    }
    if (password == "true") {
        alert("保存成功，密码已修改，请重新登录！")
        window.location.href = "login.jsp";
    }
    if (password == "false") {
        alert("保存失败，原密码错误！")
    }
</script>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar"><span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="#">在线考试系统</a></div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="test_info">考试信息</a></li>
                <li><a href="test_record">考试记录</a></li>
                <li class="active"><a href="user_info?username=${sessionScope.get("username")}">用户信息</a></li>
            </ul>
            <a href="logout">
                <p class="navbar-text pull-right">退出</p>
            </a>
            <p class="navbar-text pull-right">学生用户：${sessionScope.get("realName")}</p>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h2>用户信息</h2>
        <hr>
        <div class="user-form">
            <form action="${pageContext.request.contextPath}/userChange?username=${username}" method="post"
                  onSubmit="return submitSave()">
                <h4><span>用户名：</span>&nbsp;${username}</h4>
                <input type="hidden" name="username" value="${username}">
                <h4><span>真实姓名：</span>
                    <input type="text" id="realname" name="realname" value="${realname}" required>
                </h4>
                <h4 id="old-password" hidden="true"><span>原密码：</span>
                    <input type="password" id="oldPassword" name="password">
                </h4>
                <h4 id="new-password" hidden="true"><span>新密码：</span>
                    <input type="password" id="newPassword" name="newpassword">
                </h4>
                <h4 id="new-password2" hidden="true"><span>确认新密码：</span>
                    <input type="password" id="newPassword2">
                </h4>
                <button type="button" class="btn btn-danger" onClick="showOrHiddenPasswordText()">修改密码</button>
                <input type="submit" class="btn btn-warning" value="保存修改">
            </form>
        </div>
    </div>
</div>
</body>
</html>