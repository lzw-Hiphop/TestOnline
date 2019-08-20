<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
</head>
<body>
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
        <div class="user-form">
            <form action="saveImg?username=${username}" method="post" enctype="multipart/form-data">
                <h4><span>请选择头像：</span>
                <input type="file" name="pic" id="pic"/>
                </h4>
                <input type="submit" class="btn btn-primary" value="上传"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>