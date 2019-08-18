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


        function submitSave() {
            var p1 = document.getElementById("newPassword");
            var p2 = document.getElementById("newPassword2");
            if (p1.value != p2.value) {
                alert("错误，新密码与确认新密码不相同！");
                return false;
            }
        }

        return true;
        }
    </script>
</head>
<body>

<script type="text/javascript">
    var state = "${state}";
    if (state == "error") {
        alert("添加失败，用户名已存在！");
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
                <li><a href="admin_test">考试管理</a></li>
                <li class="active"><a href="admin_user">考生管理</a></li>
                <li><a href="admin_subject">科目管理</a></li>
            </ul>
            <a href="logout">
                <p class="navbar-text pull-right">退出</p>
            </a>
            <p class="navbar-text pull-right">管理员用户：${sessionScope.get("realName")}</p>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h2>新增考生</h2>
        <hr>
        <div class="user-form">
            <form action="${pageContext.request.contextPath}/adminAdd" method="post" onSubmit="return submitSave()">
                <h4><span>考生用户名：</span>
                    <input type="text" id="username" name="username" required>
                </h4>
                <h4><span>真实姓名：</span>
                    <input type="text" id="realname" name="realname" required>
                </h4>
                <h4 id="new-password"><span>密码：</span>
                    <input type="password" id="newPassword" name="newPassword" required>
                </h4>
                <h4 id="new-password2"><span>确认密码：</span>
                    <input type="password" id="newPassword2" name="newPassword2" required>
                </h4>
                <input type="submit" class="btn btn-warning" value="保存修改">
            </form>
        </div>
    </div>
</div>
</body>
</html>