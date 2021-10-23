<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/20
  Time: 5:14 下午
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
    <link href="../css/jquery-tagsinput.min.css" rel="stylesheet">

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
                                    <a class="nav-link" href="#"><span data-feather="arrow-right"></span> 新增文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 修改文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 删除文章 </a>
                                </li>
                                <li class="list-group-item">
                                    <a class="nav-link" href="#"> 素材管理 </a>
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
            <form target="_self" action="http://localhost:8080/JavaWeb/insertArticle" method="post">
                <input type="hidden" name="content" id="content"/>
                <input type="hidden" name="action" id="action" value="insertArticle"/>
                <div class="input-group mb-3" style="margin-top: 10px;">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputTitle">标题</span>
                    </div>
                    <input name="title" id="title" type="text" class="form-control" aria-label="Sizing example input"
                           aria-describedby="inputTitle">
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroup-sizing-default">分类</span>
                    </div>
                    <input type="hidden" name="tags" id="tags" data-role='tags-input'/>
                </div>
                <div id="div1"></div>
                <input type="submit" class="btn btn-primary"/>
            </form>
        </main>
    </div>
</div>

<!-- 引入 wangEditor.min.js -->
<script type="text/javascript">
    const E = window.wangEditor
    const editor = new E('#div1')
    editor.config.height = 700

    // 配置 onchange 回调函数
    editor.config.onchange = function (newHtml) {
        setVal(newHtml)
    };

    // 配置触发 onchange 的时间频率，默认为 200ms
    editor.config.onchangeTimeout = 500; // 修改为 500ms

    // 配置图片上传地址
    editor.config.uploadImgServer = 'http://localhost:8080/JavaWeb/editUploadImg?action=editUploadImg'

    // 默认情况下，显示所有菜单
    editor.config.menus = [
        'head',
        'bold',
        'fontSize',
        'fontName',
        'italic',
        'underline',
        'strikeThrough',
        'indent',
        'lineHeight',
        'foreColor',
        'backColor',
        'link',
        'list',
        'todo',
        'justify',
        'quote',
        'emoticon',
        'image',
        'table',
        'code',
        'splitLine',
        'undo',
        'redo',
    ]

    // 类型限制
    editor.config.uploadImgAccept = ['jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp'];

    // 设置每次上传文件个数（默认100个）
    editor.config.uploadImgMaxLength = 1

    editor.create()

    // 设置内容到隐藏域
    function setVal(newHtml) {
        $('#content').val(newHtml)
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="../js/bootstrap.bundle.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
<script src="../js/dashboard.js"></script>
<script src="../js/jquery-tagsinput.min.js"></script>
<script>
    $(document).ready(function () {
        $('[data-role="tags-input"]').tagsInput()
    });
</script>
</body>
</html>