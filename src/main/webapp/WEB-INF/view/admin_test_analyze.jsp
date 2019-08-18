<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="active"><a href="admin_test">考试管理</a></li>
                <li><a href="admin_user">考生管理</a></li>
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
        <h2>试卷分数详情</h2>
        <h3>试卷号：${testpaper.paperid}&nbsp;&nbsp;&nbsp;&nbsp;试卷科目：${subjectName}</h3>
        <h3>试卷名：${testpaper.papername}</h3>
        <hr>
        <h4>做题人数：${count}人&nbsp;&nbsp;&nbsp;&nbsp;最高分：${max}分&nbsp;&nbsp;&nbsp;&nbsp;最低分：${low}分&nbsp;&nbsp;&nbsp;&nbsp;平均分：${average}分</h4>
        <hr>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>考试号</th>
                    <th>考生号</th>
                    <th>考生姓名</th>
                    <th>分数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="adminTestAnalyzeBean" items="${adminTestAnalyzeBeans}">
                    <tr>
                        <td>${adminTestAnalyzeBean.testId}</td>
                        <td>${adminTestAnalyzeBean.userId}</td>
                        <td>${adminTestAnalyzeBean.realName}</td>
                        <td>${adminTestAnalyzeBean.grade}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>