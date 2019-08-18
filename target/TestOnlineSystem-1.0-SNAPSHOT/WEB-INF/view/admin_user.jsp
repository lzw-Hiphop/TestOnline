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

        .btn {
            margin: 5px;
        }

        .addTestppaper {
            float: right;
        }
    </style>
</head>
<body>
<script>
    if ("${state}" == "success") {
        alert("修改成功！")
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
        <h2>考生列表<a href="${pageContext.request.contextPath}/accept" class="btn btn-primary addTestppaper">
            新增考生</a></h2>
        <hr>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>考生号</th>
                    <th>考生用户名</th>
                    <th>考生真实姓名</th>
                    <th>考生密码</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="u" items="${pageUtil.list}">
                    <tr>
                        <td>${u.userid}</td>
                        <td>${u.username}</td>
                        <td>${u.realname}</td>
                        <td>${u.password}</td>
                        <td><a href="${pageContext.request.contextPath}/adminSelect?userid=${u.userid}"
                               class="btn btn-xs btn-info">修改</a>
                            <a href="${pageContext.request.contextPath}/adminDelete?userid=${u.userid}"
                               class="btn btn-xs btn-danger">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!--分页-->
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <!--第一页-->
                <li>
                    <c:choose>
                    <c:when test="${pageUtil.pageIndex - 1 > 0}">
                    <a href="admin_user?pageIndex=1" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                    </c:when>
                        <c:otherwise>
                        <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                        </li>
                        </c:otherwise>
                    </c:choose>
                </li>
                <!--页数-->
                <c:forEach var = "i" begin="1" end="${pageUtil.pageCount}" step="1">
                    <c:choose>
                    <c:when test="${pageUtil.pageIndex==i}">
                        <li class="active"><a href="admin_user?pageIndex=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="admin_user?pageIndex=${i}">${i}</a></li>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
                <!--尾页-->
                <li>
                    <c:choose>
                    <c:when test="${pageUtil.pageIndex  < pageUtil.pageCount}">
                    <a href="admin_user?pageIndex=${pageUtil.pageCount}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                    </c:when>
                    <c:otherwise>
                        <li class="disabled">
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                        </li>
                    </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </nav>

    </div>
</div>
</body>
</html>