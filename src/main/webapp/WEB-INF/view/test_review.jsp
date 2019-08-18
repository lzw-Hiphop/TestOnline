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

        .panel-title {
            line-height: 40px;
            font-size: 20px;
        }

        .answer, .userAnswer, .paperAnswer {
            line-height: 40px;
            font-size: 20px;
            margin-top: 10px;
            margin-left: 30px;
        }

        .paperAnswer {
            font-weight: bold;
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
                <li class="active"><a href="test_record">考试记录</a></li>
                <li><a href="user_info?username=${sessionScope.get("username")}"">用户信息</a></li>
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
        <h3>试卷号：${paperId}&nbsp;&nbsp;&nbsp;&nbsp;试卷科目：${subjectName}&nbsp;&nbsp;&nbsp;&nbsp;考试时间：${testTime}</h3>
        <h3>试卷名称：${paperName}</h3>
        <h3>得分：${grade}分</h3>
        <hr>
        <%
            char c = 64;
            char ca = 64;
            request.setAttribute("c", c);
            request.setAttribute("c", ca);
        %>
        <c:forEach var="testReviewBean" items="${testReviewBeans}" varStatus="loop">
            <div class="panel
                <c:if test="${testReviewBean.userGrade>0}">
                panel-success
                </c:if>
                <c:if test="${testReviewBean.userGrade==0}">
                panel-danger
                </c:if>
            ">
                <div class="panel-heading">
                    <h3 class="panel-title">${loop.count}、${testReviewBean.question}（${testReviewBean.grade}分）</h3>
                </div>
                <div class="panel-body">
                    <%
                        c = 64;
                        request.setAttribute("c", c);
                    %>
                    <c:forEach var="choice" items="${testReviewBean.choices}" varStatus="loop2">
                        <%
                            c++;
                            request.setAttribute("c", c);
                        %>
                        <h3 class="answer">[${loop2.count}]&nbsp;${choice}</h3>
                    </c:forEach>
                    <h3 class="userAnswer">你的答案：${testReviewBean.userAnswer}</h3>
                    <h3 class="paperAnswer">正确答案：${testReviewBean.paperAnswer}</h3>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>