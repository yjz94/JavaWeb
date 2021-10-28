<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/20
  Time: 5:14 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <title>Admin - Attachment - FishLand</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="http://localhost:8080/JavaWeb/">
    <link rel="icon" href="imgs/fishland.png" sizes="32x32" type="image/png">

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcdn.net/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/article.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
</head>
<body>

<form target="_self" action="http://localhost:8080/JavaWeb/API/article/insert" method="post">
    <input type="hidden" name="articleId" id="articleId" value="${articleId}"/>
    <input type="hidden" name="content" id="content" onchange="draftSave()" value="${content}"/>

    <div class="form-group">
        <label for="title">标题</label>
        <input type="text" class="form-control" name="title" id="title" onchange="draftSave()" value="${title}"
               aria-describedby="emailHelp">
        <small id="titleHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>

    <div class="form-group">
        <label for="tags">分类</label>
        <input type="text" class="form-control" name="tags" id="tags" onchange="draftSave()" value="${tags}"
               aria-describedby="tagsHelp">
        <small id="tagsHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>

    <div class="form-group">
        <div id="div1"></div>
    </div>

    <div class="form-group" style="text-align: center;">
        <button type="submit" class="btn btn-primary">提交</button>
        <button type="button" class="btn btn-primary">预览</button>
        <button type="button" class="btn btn-primary">保存</button>
    </div>
</form>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.js"></script>

<!-- 引入 wangEditor.min.js -->
<script type="text/javascript">
    const E = window.wangEditor
    const editor = new E('#div1')
    editor.config.height = 500

    // 配置 onchange 回调函数
    editor.config.onchange = function (newHtml) {
        setVal(newHtml)
    };

    // 配置触发 onchange 的时间频率，默认为 200ms
    editor.config.onchangeTimeout = 500; // 修改为 500ms

    // 配置图片上传地址
    editor.config.uploadImgServer = 'API/attachment/insert/editor'

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

    // 设置图片上传自定义参数
    editor.config.uploadImgParams = {'master': $('#articleId').val()}

    editor.create()

    $(document).ready(function () {
        $('#tags').tagsInput({
            'height': 'auto',
            'width': 'auto'
        })
    });

    $(function () {
        editor.txt.html('${content}')
    })

    // 保存为草稿
    function draftSave() {

        // 获得title
        const title = $('#title').val()
        // 获得content
        const content = $('#content').val()
        // 获得tags
        const tags = $('#tags').val()
        // 获得articleId
        const articleId = $('#articleId').val()

        $.ajax({
            type: "POST",
            url: "API/article/draftSave",
            data: 'articleId=' + articleId + '&content=' + content + '&title=' + title + '&tags=' + tags,
            dataType: "json",
            success: function (data) {
                console.log(data)
            }
        });
    }

    // 设置内容到隐藏域
    function setVal(newHtml) {
        $('#content').val(newHtml)
    }
</script>

</body>
</html>