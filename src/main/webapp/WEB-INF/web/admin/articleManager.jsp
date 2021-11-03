<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/11/3
  Time: 5:33 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <title>Admin - ArticleManager - FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>
</head>
<body>
<form action="admin/API/article/search" method="post" style="padding: 10px;">
    <div class="form-row">
        <div class="form-group col-md-4">
            <label for="inputEmail4">标题关键字</label>
            <input type="email" class="form-control" id="inputEmail4">
        </div>
        <div class="form-group col-md-4">
            <label for="exampleFormControlSelect1">标签</label>
            <select class="form-control" id="exampleFormControlSelect1">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <label for="exampleFormControlSelect3">状态</label>
            <select class="form-control" id="exampleFormControlSelect3">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
            </select>
        </div>
    </div>
    <button type="submit" class="btn btn-primary" onclick="insertArticle()">搜索</button>
</form>
<%--添加一个隐藏的iframe可以让form在提交时避免跳转--%>
<iframe id="hiddenIframe" name="hiddenIframe" style="display: none;" class="iframes"></iframe>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">First</th>
        <th scope="col">Last</th>
        <th scope="col">Handle</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="10" var="i">
    <tr class="articleTable">
        <input type="hidden" value="${i}"/>
        <th scope="row">${i}</th>
        <td>Mark${i}</td>
        <td>Otto${i}</td>
        <td>@mdo${i}</td>
    </tr>
    </c:forEach>

</table>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
    $('.articleTable').click(function () {
        location.href = "admin/article?articleId=" + this.children('input').value()
    });
</script>
</body>
</html>
