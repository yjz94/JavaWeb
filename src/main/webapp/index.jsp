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
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>FishLand</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link rel="icon" href="imgs/fishland.png" sizes="32x32" type="image/png">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        .abstract-index {
            background-image: url("imgs/bg.png");
            background-position: center 0;
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            -moz-background-size: cover;
            -ms-background-size: cover;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        /*表示只显示两行，并且第二行超出部分显示...*/
        .p-show-line-3 {
            display: -webkit-box;
            overflow: hidden;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3;
        }

        .feather {
            width: 20px;
            height: 20px;
            /*stroke: #1E90FF;*/
            stroke-width: 2;
            stroke-linecap: round;
            stroke-linejoin: round;
            fill: none;
        }
    </style>

    <!-- Custom styles for this template -->
    <link href="css/album.css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container">
            <a class="navbar-brand" href="#">
                <img src="imgs/fishland.svg" width="30" height="30" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">博客</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">头条</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">游戏</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown01">
                            <a class="dropdown-item" href="#">戴森球</a>
                            <a class="dropdown-item" href="#">英雄联盟</a>
                            <a class="dropdown-item" href="#">云顶之弈</a>
                        </div>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">分享</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">关于</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>


<main role="main">
    <section class="jumbotron text-center abstract-index">
        <div class="container">
            <h1 style="color: #ffffff;">个人分享网站</h1>
            <p class="lead text-muted">这是一个分享网站，主要目的是练习JavaWeb，同时也分享一些自己认为比较好的技术、技巧和常识。</p>
            <p>
                <a href="#" class="btn btn-primary my-2">关注</a>
                <a href="#" class="btn btn-secondary my-2">留言</a>
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
                        <a href="#!">
                            <img class="card-img-top" src="imgs/default.jpeg" alt="..."/>
                        </a>
                        <div class="card-body">
                            <div class="small text-muted">January 1, 2021</div>
                            <h2 class="card-title">Featured Post Title</h2>
                            <p class="card-text p-show-line-3">Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                                Reiciendis
                                aliquid
                                atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero
                                voluptate voluptatibus possimus, veniam magni quis!</p>
                            <%--<a class="btn btn-primary" href="#!">Read more →</a>--%>
                            <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                <span data-feather="thumbs-up" style="color: #1E90FF;"></span> 134
                            </a>
                            <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                <span data-feather="thumbs-down"></span> 134
                            </a>
                            <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                <span data-feather="message-square"></span> 7890
                            </a>
                        </div>
                    </div>

                    <!-- Nested row for non-featured blog posts-->
                    <div class="row">
                        <c:forEach begin="1" end="10" var="i">
                            <div class="col-lg-6">
                                <!-- Blog post-->
                                <div class="card mb-3">
                                    <a href="#!">
                                        <img class="card-img-top" src="imgs/default.jpeg" alt="..."/>
                                    </a>
                                    <div class="card-body">
                                        <div class="small text-muted">January 1, 2021</div>
                                        <h2 class="card-title h4">Post Title</h2>
                                        <p class="card-text p-show-line-3">Lorem ipsum dolor sit amet, consectetur
                                            adipisicing elit.
                                            Reiciendis
                                            aliquid atque, nulla.${i}</p>
                                            <%--<a class="btn btn-primary" href="#!">Read more →</a>--%>
                                        <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                            <span data-feather="thumbs-up" style="color: #1E90FF;"></span> 134
                                        </a>
                                        <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                            <span data-feather="thumbs-down"></span> 134
                                        </a>
                                        <a href="#" class="badge badge-light" style="margin: 0 5px;">
                                            <span data-feather="message-square"></span> 7890
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
                            <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1"
                                                              aria-disabled="true">Newer</a>
                            </li>
                            <li class="page-item active" aria-current="page"><a class="page-link" href="#!">1</a></li>
                            <li class="page-item"><a class="page-link" href="#!">2</a></li>
                            <li class="page-item"><a class="page-link" href="#!">3</a></li>
                            <li class="page-item disabled"><a class="page-link" href="#!">...</a></li>
                            <li class="page-item"><a class="page-link" href="#!">15</a></li>
                            <li class="page-item"><a class="page-link" href="#!">Older</a></li>
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
<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p>
        <p class="float-right">
            <a href="#">回到顶部</a>
        </p>
        <p>
            <a href="/">FishLand</a>
            <a href="#">(京ICP备********号 | 京公网安备*******号)</a>
        </p>
    </div>
</footer>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script>
    (function () {
        // 开启feather图标的渲染
        feather.replace()
    })()
</script>
</body>
</html>