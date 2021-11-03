<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/17
  Time: 12:23 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <title>Login - FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>

    <!-- Site icon -->
    <link rel="icon" href="imgs/fishland.png" sizes="32x32" type="image/png">
    <!-- Custom styles -->
    <link href="css/login.css" rel="stylesheet">

</head>
<body class="text-center">
<form class="form-signin" action="API/user/verify" method="post">
    <img class="mb-4" src="imgs/fishland.svg" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">登陆</h1>

    <c:if test="${requestScope.message != null}">
        <div class="alert alert-danger" role="alert">
                ${requestScope.message}
        </div>
    </c:if>

    <label for="email" class="sr-only">Email</label>
    <input type="email" value="${requestScope.email}" name="email" id="email" class="form-control" placeholder="邮箱"
           required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" value="${requestScope.password}" name="password" id="password" class="form-control"
           placeholder="密码" required>
    <div class="input-group">
        <label for="code" class="sr-only">Code</label>
        <input name="code" id="code" class="form-control" placeholder="验证码" required>
        <div class="input-group-prepend">
            <img src="code.jpg" class="img-thumbnail" alt="...">
        </div>
    </div>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me" height="50"> 记住
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
    <p class="mt-5 mb-3 text-muted">
    <p>Copyright © 2021-2022</p></p>
</form>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>

