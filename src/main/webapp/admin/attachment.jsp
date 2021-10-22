<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/20
  Time: 6:15 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Dashboard Template · Bootstrap v4.6</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.6/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

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
    </style>

    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
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
                        <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo"
                             data-parent="#accordionExample">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 查询文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 新增文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 修改文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 删除文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"><span data-feather="arrow-right"></span> 素材管理 </a>
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
                                <li class="list-group-item"><a class="nav-link" href="#"> 新增用户 </a></li>
                                <li class="list-group-item"><a class="nav-link" href="#"> 管理用户 </a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
            <div>
                <form action="http://localhost:8080/JavaWeb/uploadImg" target="myIframe" enctype="multipart/form-data"
                      method="post">
                    <div class="form-group">
                        <input type="hidden" value="uploadAttachment" name="action" id="action">
                        <label for="attachmentName">名称</label>
                        <input type="text" name="attachmentName" id="attachmentName" class="form-control-file">
                        <label for="attachment">文件</label>
                        <input type="file" name="attachment" id="attachment" class="form-control-file">
                    </div>
                    <input type="submit" class="btn btn-primary"/>
                </form>
                <%--添加一个隐藏的iframe可以让form在提交时避免跳转--%>
                <iframe id="myIframe" name="myIframe" style="display: none;" class="iframes"></iframe>
            </div>
            <div style="width: 100%;background-color: #1b1e21;height: 1px; margin: 10px 0;"></div>
            <div>
                <div class="row row-cols-4">
                    <%
                        for (int i = 0; i < 12; i++) {

                    %>
                    <div class="col">
                        <div class="card" style="width: 18rem; margin: 10px;">
                            <img src="../imgs/temp1.png" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">attment1</h5>
                                <p class="card-text">这是附件的简......</p>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>

            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link" href="#">Next</a>
                    </li>
                </ul>
            </nav>
        </main>
    </div>
</div>

<!-- 引入 wangEditor.min.js -->
<script type="text/javascript">
    const E = window.wangEditor
    const editor = new E('#div1')
    editor.config.height = 700
    // 或者 const editor = new E( document.getElementById('div1') )
    editor.create()
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="../js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
<script src="../js/dashboard.js"></script>


</body>
</html>