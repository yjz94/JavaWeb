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
    <!--
          /$$$$$$  /$$           /$$       /$$                           /$$
         /$$__  $$|__/          | $$      | $$                          | $$
        | $$  \__/ /$$  /$$$$$$$| $$$$$$$ | $$  /$$$$$$  /$$$$$$$   /$$$$$$$      /$$$$$$$ /$$$$$$$
        | $$$$    | $$ /$$_____/| $$__  $$| $$ |____  $$| $$__  $$ /$$__  $$     /$$_____/| $$__  $$
        | $$_/    | $$|  $$$$$$ | $$  \ $$| $$  /$$$$$$$| $$  \ $$| $$  | $$    | $$      | $$  \ $$
        | $$      | $$ \____  $$| $$  | $$| $$ /$$__  $$| $$  | $$| $$  | $$    | $$      | $$  | $$
        | $$      | $$ /$$$$$$$/| $$  | $$| $$|  $$$$$$$| $$  | $$|  $$$$$$$ /$$|  $$$$$$$| $$  | $$
        |__/      |__/|_______/ |__/  |__/|__/ \_______/|__/  |__/ \_______/|__/ \_______/|__/  |__/
    -->
    <title>Article - FishLand</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="http://127.0.0.1:8080/JavaWeb/">
    <link rel="icon" href="imgs/fishland.png" sizes="32x32" type="image/png">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">

    <!-- 代码高亮 highlight CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css" rel="stylesheet">

    <!-- 自定义样式 -->
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        /*控制图标大小*/
        .feather {
            width: 20px;
            height: 20px;
            /*stroke: #1E90FF;*/
            stroke-width: 2;
            stroke-linecap: round;
            stroke-linejoin: round;
            fill: none;
        }

        .article-jumbotron {
            padding-bottom: 3rem;
            margin-bottom: 0;
            background-color: #fff;
        }

        @media (min-width: 768px) {
            .article-jumbotron {
                padding-bottom: 4rem;
            }
        }

        .article-jumbotron p:last-child {
            margin-bottom: 0;
        }

        .article-jumbotron h1 {
            font-weight: 300;
        }

        .article-jumbotron .container {
            max-width: 40rem;
        }

        footer {
            padding-top: 3rem;
            padding-bottom: 3rem;
        }

        footer p {
            margin-bottom: .25rem;
        }

    </style>
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
    <div class="article-jumbotron" style="height: 0;"></div>
    <div class="container">
        <jsp:include page="../common/showArticle.jsp"/>
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

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/highlight.js/11.2.0/highlight.min.js"></script>

<script></script>
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