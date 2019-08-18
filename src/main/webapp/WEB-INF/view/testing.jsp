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

        .answer {
            line-height: 40px;
            font-size: 20px;
            margin: 10px;
        }

        .answerRadio {
            width: 30px;
        }

        .answerCheckbox {
            width: 30px;
        }
    </style>
    <script type="text/javascript">
        function submitCheck() {
            return confirm("确认要交卷吗？")
        }

    </script>
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
                <li class="active"><a href="#">正在考试</a></li>
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
        <h3>试卷号：${paperId}&nbsp;&nbsp;&nbsp;&nbsp;试卷科目：${subjectName}</h3>
        <h3>试卷名称：${paperName}</h3>
        <hr>
        <form id="questionForm" action="testing/submit?paperId=${paperId}&questionNum=${questionNum}" method="post"
              onSubmit="return submitCheck()">
            <c:forEach var="testingBean" items="${testingBeans}" varStatus="loop">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">${loop.count}、${testingBean.question}（${testingBean.grade}分）</h3>
                    </div>
                    <div class="panel-body">
                        <c:if test="${testingBean.questionType==1}">
                            <%
                                char c = 64;
                                request.setAttribute("c", c);
                            %>
                            <c:forEach var="choice" items="${testingBean.choices}" varStatus="loop2">
                                <h3 class="answer">
                                    <%
                                        c++;
                                        request.setAttribute("c", c);
                                    %>
                                    <input class="answerRadio" type="radio" name="answer${loop.count}"
                                           value="${loop2.count}">
                                    [${c}]&nbsp;${choice}</h3>
                            </c:forEach>
                        </c:if>
                        <c:if test="${testingBean.questionType==2}">
                            <%
                                char c = 64;
                                request.setAttribute("c", c);
                            %>
                            <c:forEach var="choice" items="${testingBean.choices}" varStatus="loop2">
                                <h3 class="answer">
                                    <%
                                        c++;
                                        request.setAttribute("c", c);
                                    %>
                                    <input class="answerCheckbox" type="checkbox" name="answer${loop.count}"
                                           value="${loop2.count}">
                                        ${c}&nbsp;${choice}</h3>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <button class="btn btn-lg btn-primary btn-block" type="submit">提交试卷</button>
        </form>
    </div>
</div>
</body>
</html>