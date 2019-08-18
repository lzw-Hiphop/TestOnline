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

        .questionGrade {
            margin-left: 20px;
            width: 40px;
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
            return confirm("确认要保存吗？")
        }

        function questionTypeChange(selectNode) {
            var name = "answer" + selectNode.id;
            var nodes = document.getElementsByName(name);
            for (var i = 0; i < nodes.length; i++) {
                if (selectNode.value == 1) {
                    nodes[i].type = "radio";
                } else {
                    nodes[i].type = "checkbox";
                }
            }
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
        <form id="testPaperForm" action="admin_test_detail/update?paperId=${paperId}&questionNum=${questionNum}"
              method="post" onSubmit="return submitCheck()">
            <h3>试卷号：${paperId}</h3>
            <h3 class="form-inline">试卷科目：
                <select class="form-control" name="subjectId">
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.subjectid}"
                                <c:if test="${subjectId == subject.subjectid}">
                                    selected
                                </c:if>
                        >${subject.subjectname}</option>
                    </c:forEach>
                </select>
            </h3>
            <h3 class="form-inline">试卷名称：
                <input class="form-control" type="text" name="paperName" value="${paperName}" required>
            </h3>
            <hr>
            <c:forEach var="adminTestDetailBean" items="${adminTestDetailBeans}" varStatus="loop">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class=form-inline>题目&nbsp;${loop.count}：&nbsp;&nbsp;&nbsp;&nbsp;分数
                            <input class="form-control questionGrade" type="number" name="grade${loop.count}"
                                   value="${adminTestDetailBean.grade}">
                            &nbsp;&nbsp;&nbsp;&nbsp;类型
                            <select class="form-control" name="questionType${loop.count}" id="${loop.count}"
                                    onchange="questionTypeChange(this)">
                                <c:if test="${adminTestDetailBean.questionType == 1}">
                                    <option value="1" selected>单选题</option>
                                    <option value="2">多选题</option>
                                </c:if>
                                <c:if test="${adminTestDetailBean.questionType == 2}">
                                    <option value="1">单选题</option>
                                    <option value="2" selected>多选题</option>
                                </c:if>
                            </select>
                        </h3>
                        <input class="form-control" type="text" name="question${loop.count}"
                               value="${adminTestDetailBean.question}">
                    </div>
                    <div class="panel-body">
                        <%
                            char c = 64;
                            request.setAttribute("c", c);
                        %>
                        <c:forEach var="choice" items="${adminTestDetailBean.choices}" varStatus="loop2">
                            <c:if test="${adminTestDetailBean.questionType == 1}">
                                <%
                                    c++;
                                    request.setAttribute("c", c);
                                %>
                                <h3><input class="answerRadio" type="radio" name="answer${loop.count}"
                                           value="${loop2.count}"
                                <c:if test="${loop2.count==adminTestDetailBean.answer.get(0)}">
                                           checked
                                </c:if>
                                >选项${c}</h3>
                            </c:if>
                            <c:if test="${adminTestDetailBean.questionType == 2}">
                                <%
                                    c++;
                                    request.setAttribute("c", c);
                                %>
                                <h3><input class="answerCheckbox" type="checkbox" name="answer${loop.count}"
                                           value="${loop2.count}"
                                <c:forEach var="answer" items="${adminTestDetailBean.answer}">
                                <c:if test="${loop2.count==answer}">
                                           checked
                                </c:if>
                                </c:forEach>
                                >选项${c}</h3>
                            </c:if>
                            <input class="form-control" type="text" name="choice${loop.count}"
                                   value="${choice}">
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
            <button class="btn btn-lg btn-primary btn-block" type="submit">保存试卷</button>
        </form>
    </div>
</body>
</html>