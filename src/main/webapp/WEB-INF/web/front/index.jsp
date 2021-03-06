<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/24
  Time: 1:12 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <title>FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>

    <!-- 前段自定义样式 -->
    <link href="css/front.css" rel="stylesheet">
</head>
<body>

<jsp:include page="../common/indexHead.jsp"/>
<main role="main">
    <section class="jumbotron text-center abstract-index">
        <div class="container">
            <h1 style="color: #ffffff;">个人分享网站</h1>
            <p class="lead text-muted">这是一个分享网站，主要目的是练习JavaWeb，同时也分享一些自己认为比较好的技术、技巧和资源。</p>
            <p>
                <a href="#" class="btn btn-primary my-2" data-toggle="modal" data-target="#staticBackdrop">关注</a>
                <a href="#" class="btn btn-secondary my-2" data-toggle="modal" data-target="#staticBackdrop">留言</a>
            </p>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">
            <div class="row">
                <!-- Blog entries-->
                <div class="col-lg-8">
                    <!-- Featured blog post-->
                    <div class="card mb-4">
                        <a target="_blank" href="article?articleId=${topArticle.articleId}">
                            <img class="card-img-top" src="${topArticleCover}" alt="..."/>
                        </a>
                        <div class="card-body">
                            <div class="small text-muted">${topArticle.createDate}</div>
                            <h2 class="card-title">${topArticle.title}</h2>
                            <p class="card-text p-show-line-3">${topArticle.text}</p>
                            <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                <span data-feather="thumbs-up" style="color: #1E90FF;"></span> ${topPraise.thumbsUp}
                            </a>
                            <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                <span data-feather="thumbs-down"></span> ${topPraise.thumbsDown}
                            </a>
                            <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                <span data-feather="message-square"></span> ${topPraise.message}
                            </a>
                        </div>
                    </div>

                    <!-- Nested row for non-featured blog posts-->
                    <div class="row">
                        <c:forEach var="article" items="${articleList}">
                            <div class="col-lg-6">
                                <!-- Blog post-->
                                <div class="card mb-3">
                                    <a target="_blank" href="article?articleId=${article.articleId}">
                                        <img class="card-img-top" src="${coverMap[article.articleId]}"
                                             style="height: 200px;"/>
                                    </a>
                                    <div class="card-body">
                                        <div class="small text-muted">${article.createDate}</div>
                                        <h2 class="card-title h4">${article.title}</h2>
                                        <p class="card-text p-show-line-3">${article.text}</p>
                                        <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                            <span data-feather="thumbs-up"
                                                  style="color: #1E90FF;"></span>${praiseMap[article.articleId].thumbsUp}
                                        </a>
                                        <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                            <span data-feather="thumbs-down"></span> ${praiseMap[article.articleId].thumbsDown}
                                        </a>
                                        <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                            <span data-feather="message-square"></span> ${praiseMap[article.articleId].message}
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Pagination-->
                    <nav aria-label="Pagination">
                        <hr class="my-0"/>
                        <ul class="pagination justify-content-center my-4">
                            <li class="page-item ${map.previous == null?'disabled':''}">
                                <a class="page-link" onclick="showArticlePage('index?page=${map.previous}')"
                                   aria-disabled="true">上一页</a>
                            </li>
                            <c:forEach items="${map.item}" var="item">
                                <li class="page-item ${map.disabled == item ? 'active':''}">
                                    <a class="page-link"
                                       onclick="showArticlePage('index?page=${item}')">
                                            ${item}
                                    </a>
                                </li>
                            </c:forEach>
                            <li class="page-item ${map.next == null?'disabled':''}">
                                <a class="page-link"
                                   onclick="showArticlePage('index?page=${map.next}')">下一页</a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <!-- Side widgets-->
                <div class="col-lg-4">
                    <!-- Search widget-->
                    <div class="card mb-4">
                        <div class="card-header">Search</div>
                        <div class="card-body">
                            <div class="input-group">
                                <form class="form-inline my-2 my-lg-0">
                                    <input class="form-control mr-sm-2" type="text" placeholder="搜索" aria-label="搜索">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Categories widget-->
                    <div class="card mb-4">
                        <div class="card-header">Categories</div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-6">
                                    <ul class="list-unstyled mb-0">
                                        <li><a href="#!">Web Design</a></li>
                                        <li><a href="#!">HTML</a></li>
                                        <li><a href="#!">Freebies</a></li>
                                    </ul>
                                </div>
                                <div class="col-sm-6">
                                    <ul class="list-unstyled mb-0">
                                        <li><a href="#!">JavaScript</a></li>
                                        <li><a href="#!">CSS</a></li>
                                        <li><a href="#!">Tutorials</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Side widget-->
                    <div class="card mb-4">
                        <div class="card-header">Side Widget</div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                        <div class="card-body">You can put anything you want inside of these side widgets. They are easy
                            to use,
                            and feature the Bootstrap 5 card component!
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>
<jsp:include page="../common/indexFooter.jsp"/>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script>
    (function () {
        // 开启feather图标的渲染
        feather.replace()
    })()

    function showArticlePage(url) {
        location.href = url
    }
</script>
</body>
</html>