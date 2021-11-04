<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/19
  Time: 5:29 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>Admin - FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>

    <!-- admin css -->
    <link href="css/admin.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">FishLand</a>
    <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse"
            data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="#">退出</a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="sidebar-sticky pt-3">
                <div class="accordion nav flex-column" id="accordionExample">
                    <div class="card nav-item">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <a class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                   data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    <span data-feather="home"></span> 网站数据
                                </a>
                            </h2>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header" id="headingTwo">
                            <h2 class="mb-0">
                                <a class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                   data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                                    <span data-feather="book"></span> 文章操作
                                </a>
                            </h2>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                             data-parent="#accordionExample">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <a class="nav-link" href="admin/article" target="adminPage">
                                        <span data-feather="file-plus"></span> 新增文章 <span
                                            data-feather="arrow-left"></span>
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="admin/articleManager" target="adminPage">
                                        <span data-feather="file-text"></span> 文章管理
                                    </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="admin/attachment" target="adminPage">
                                        <span data-feather="file"></span> 素材管理
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <a class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                   data-target="#collapseThree" aria-expanded="true" aria-controls="collapseThree">
                                    <span data-feather="user"></span> 用户操作
                                </a>
                            </h2>
                        </div>
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree"
                             data-parent="#accordionExample">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <a class="nav-link" href="#">
                                        <span data-feather="user-plus"></span> 新增用户
                                    </a>
                                </li>
                                <li class="list-group-item"><a class="nav-link" href="#"><span
                                        data-feather="users"></span> 用户管理 </a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <iframe src="admin/main" name="adminPage" id="adminPage" style="overflow:visible;" width="100%"
                    height="100%" frameBorder="0" marginheight="0" marginwidth="0"
                    onload="iframeLoad()"></iframe>
        </main>
    </div>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script>
    feather.replace()

    /*// document.domain = "caibaojian.com";
    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
        console.log("1111")
    };

    window.onload = function () {
        setIframeHeight(document.getElementById('adminPage'));
    };

    function reinitIframe() {
        var iframe = document.getElementById("adminPage");
        try {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(bHeight, dHeight);
            iframe.height = height;
            console.log(height);
        } catch (ex) {
        }
    }*/

    /*window.onresize = function () {
        reinitIframe();
    }*/

    function reinitIframe() {
        var iframe = document.getElementById("adminPage");
        try {
            var bHeight = iframe.contentWindow.document.body.scrollHeight;
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            var height = Math.min(bHeight, dHeight);
            iframe.height = height + 50;
            // console.log(iframe.height);
        } catch (ex) {
        }
    }

    function iframeLoad() {
        document.getElementById("adminPage").height = 0;
        document.getElementById("adminPage").height =
            document.getElementById("adminPage").contentWindow.document.body.scrollHeight + 20;
    }

    window.onload = function () {
        iframeLoad()
    };

    // 定时触发
    // window.setInterval("reinitIframe()", 200);

</script>
</body>
</html>