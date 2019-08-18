<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html>
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

        .btn {
            margin: 5px;
        }

        .addTestppaper {
            float: right;
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
                <li><a href="admin_test">考试管理</a></li>
                <li><a href="admin_user">考生管理</a></li>
                <li class="active"><a href="admin_subject">科目管理</a></li>
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
        <h2>科目列表<a href="admin_subject_add" class="btn btn-primary addTestppaper">新增科目</a></h2>
        <hr>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>科目号</th>
                    <th>科目名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="v" items="${subjects}">
                    <tr>
                        <td>${v.subjectid}</td>
                        <td>${v.subjectname}</td>
                        <td><a href="admin_subject_detail?subjectid=${v.subjectid}&subjectname=${v.subjectname}"
                               class="btn btn-xs btn-info">修改</a>
                            <a href="admin_subject/delete?subjectid=${v.subjectid}" class="btn btn-xs btn-danger">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>