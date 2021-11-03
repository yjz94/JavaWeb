<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/29
  Time: 11:45 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <title>Article - FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>

    <!-- 代码高亮样式 highlight CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="css/front.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../common/indexHead.jsp"/>
<main role="main">
    <div class="article-jumbotron" style="height: 0;"></div>
    <div class="container">
        <jsp:include page="../common/showArticle.jsp"/>
    </div>
</main>
<jsp:include page="../common/indexFooter.jsp"/>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/highlight.js/11.2.0/highlight.min.js"></script>
<script>
    (function () {
        // 开启feather图标的渲染
        feather.replace()

        // 开启highlight代码高亮
        hljs.highlightAll()
    })()
</script>
</body>
</html>