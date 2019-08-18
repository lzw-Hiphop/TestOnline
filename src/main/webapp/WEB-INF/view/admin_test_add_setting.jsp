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
        <form id="testPaperForm" action="admin_test_add" method="get">
            <h3 class="form-inline">单选题数量：
                <input class="form-control" type="number" name="singleChoiceNum" required>
            </h3>
            <h3 class="form-inline">单选题默认分值：
                <input class="form-control" type="number" name="singleChoiceGrade" required>
            </h3>
            <h3 class="form-inline">单选题选项数量：
                <input class="form-control" type="number" name="singleChoiceAnswerNum" required>
            </h3>
            <hr>
            <h3 class="form-inline">多选题数量：
                <input class="form-control" type="number" name="multipleChoiceNum" required>
            </h3>
            <h3 class="form-inline">多选题默认分值：
                <input class="form-control" type="number" name="multipleChoiceGrade" required>
            </h3>
            <h3 class="form-inline">多选题选项数量：
                <input class="form-control" type="number" name="multipleChoiceAnswerNum" required>
            </h3>
            <hr>
            <button class="btn btn-lg btn-primary btn-block" type="submit">保存设置</button>
        </form>
    </div>
</body>
</html>