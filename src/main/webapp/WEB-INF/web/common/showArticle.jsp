<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/29
  Time: 6:39 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="zh">
<head>
    <title>ShowArticle - FishLand</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="http://127.0.0.1:8080/JavaWeb/">
    <link rel="icon" href="imgs/fishland.png" sizes="32x32" type="image/png">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div style="padding: 10px;">
    <h1>${article.title}</h1>
</div>
<div style="padding: 10px;margin-top: 10px; border-radius: 5px;">
    <div class="bar-content">
        <span class="time">${article.createDate}</span>

        <span data-feather="eye" style="padding-left: 5px;"></span>
        <span class="read-count">${praise.read}</span>

        <span data-feather="thumbs-up" style="padding-left: 5px;"></span>
        <span class="read-count">${praise.thumbsUp}</span>

        <span data-feather="thumbs-down" style="padding-left: 5px;"></span>
        <span class="read-count">${praise.thumbsDown}</span>

        <span data-feather="message-square" style="padding-left: 5px;"></span>
        <span class="read-count">${praise.message}</span>
    </div>
    <div class="blog-tags-box">
        <div class="tags-box artic-tag-box">
            <span class="label">文章标签：</span>${article.tags}
        </div>
    </div>
</div>
<div style="padding: 10px;margin-top: 10px;">
    ${article.content}
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script>
    (function () {
        // 开启feather图标的渲染
        feather.replace()
    })()
</script>
</body>
</html>