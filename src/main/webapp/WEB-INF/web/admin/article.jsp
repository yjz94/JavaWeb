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
    <%@ include file="../common/baseLib.jsp" %>

    <!-- tags CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/jquery-tagsinput/1.3.6/jquery.tagsinput.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/article.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
</head>
<body>
<form action="admin/API/article/insert" method="post">
    <input type="hidden" name="articleId" id="articleId" value="${articleId}"/>
    <input type="hidden" name="content" id="content"/>
    <input type="hidden" name="text" id="text"/>
    <input type="hidden" name="crudType" id="crudType" value="${crudType}"/>

    <div class="form-group">
        <label for="title">标题</label>
        <input type="text" class="form-control" name="title" id="title" value="${title}"
               aria-describedby="emailHelp">
        <small id="titleHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>

    <div class="form-group">
        <label for="tags">分类</label>
        <input type="text" class="form-control" name="tags" id="tags" value="${tags}"
               aria-describedby="tagsHelp">
        <small id="tagsHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>

    <div class="form-group">
        <div id="div1">
            ${content}
        </div>
    </div>

    <div class="form-group" style="text-align: center;">
        <button type="submit" class="btn btn-primary" onclick="insertArticle()">提交</button>
        <button type="button" class="btn btn-primary" onclick="waitCon()">预览</button>
        <c:if test="${crudType == 'insert'}">
            <button type="submit" class="btn btn-primary" onclick="draftSave()">保存</button>
        </c:if>
    </div>
</form>
<%--添加一个隐藏的iframe可以让form在提交时避免跳转--%>
<iframe id="hiddenIframe" name="hiddenIframe" style="display: none;" class="iframes"></iframe>
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
    editor.config.uploadImgServer = 'admin/API/attachment/insert/editor'

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
        });

        setVal(editor.txt.html())
    });

    /*$(function () {
        editor.txt.html('')
    })*/

    // 设置内容到隐藏域
    function setVal(newHtml) {
        // 使用函数名 filterXSS，用法一样
        $('#content').val(newHtml)
        $('#text').val(editor.txt.text())
    }

    // 保存为草稿
    function draftSave() {
        $('form').attr("action", "http://127.0.0.1:8080/JavaWeb/admin/API/article/insert/draft");
    }

    // 保存文章
    function insertArticle() {
        $('form').attr("action", "http://127.0.0.1:8080/JavaWeb/admin/API/article/insert");
    }

    function waitCon() {
        alert("还在建设中......");
    }
</script>

</body>
</html>