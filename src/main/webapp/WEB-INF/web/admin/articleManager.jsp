<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/11/3
  Time: 5:33 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh">
<head>
    <title>Admin - ArticleManager - FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>

    <!-- 代码高亮样式 highlight CSS -->
    <link href="https://cdn.bootcdn.net/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css" rel="stylesheet">
</head>
<body style="padding: 10px;">
<form action="admin/API/article/search" method="post" style="padding: 10px;">
    <div class="form-row">
        <div class="form-group col-md-4">
            <input type="text" class="form-control" id="inputEmail4" placeholder="标题">
        </div>
        <div class="form-group col-md-4">
            <select class="form-control" id="exampleFormControlSelect3">
                <option>标签选择</option>
                <option value="0">禁用</option>
                <option value="1">启用</option>
            </select>
        </div>
        <div class="form-group col-md-4">
            <button type="submit" class="btn btn-primary" onclick="insertArticle()">搜索</button>
        </div>
    </div>
</form>
<%--添加一个隐藏的iframe可以让form在提交时避免跳转--%>
<iframe id="hiddenIframe" name="hiddenIframe" style="display: none;" class="iframes"></iframe>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
    <tr>
        <th scope="col">id</th>
        <th scope="col" style="width: 240px;">创建时间</th>
        <th scope="col" style="width: 240px;">修改时间</th>
        <th scope="col">标题</th>
        <th scope="col">标签</th>
        <th scope="col" style="width: 60px;">状态</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${articles}" var="article">
    <tr class="articleTable">
        <input type="hidden" value="${article.articleId}"/>
        <td>${article.id}</td>
        <td>${article.createDate}</td>
        <td>${article.updateDate}</td>
        <td>${article.title}</td>
        <td>${article.tags}</td>
        <td>${article.status==1?'启用':'禁用'}</td>
    </tr>
    </c:forEach>

</table>

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

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1"
     aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="articleId" id="articleId">
                <div style="padding: 10px;">
                    <h1 id="title"></h1>
                </div>
                <div style="padding: 10px;margin-top: 10px; border-radius: 5px;background-color: #f8f8f8;">
                    <div class="bar-content">
                        <span class="time" id="createDate"></span>
                        <span data-feather="eye" style="padding-left: 5px;"></span>
                        <span class="read-count" id="read"></span>
                        <span data-feather="thumbs-up" style="padding-left: 5px;"></span>
                        <span class="read-count" id="thumbsUp"></span>
                        <span data-feather="thumbs-down" style="padding-left: 5px;"></span>
                        <span class="read-count" id="thumbsDown"></span>
                        <span data-feather="message-square" style="padding-left: 5px;"></span>
                        <span class="read-count" id="message"></span>
                    </div>
                    <div class="blog-tags-box">
                        <div class="tags-box artic-tag-box">
                            <span class="label">文章标签：</span><span class="label" id="tags"></span>
                        </div>
                    </div>
                </div>
                <div style="padding: 10px;margin-top: 10px;" id="content"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="deleteArticle()">删除</button>
                <button type="button" class="btn btn-primary" onclick="toUpdateArticle()">修改</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/highlight.js/11.2.0/highlight.min.js"></script>
<script>

</script>
<script type="text/javascript">
    (function () {
        // 开启feather图标的渲染
        feather.replace()

        // 代码高亮
        hljs.highlightAll()
    })()

    $('.articleTable').click(function () {
        const articleId = $(this).children('input').attr('value')
        // 异步请求
        $.ajax({
            url: "admin/API/article/show?articleId=" + articleId,
            type: "get",
            dataType: 'json'
        }).done(function (data) {// 请求成功的回调
            $('#articleId').val(articleId)
            $('#title').html(data.article.title)
            $('#createDate').html(data.article.createDate)
            $('#read').html(data.praise.read)
            $('#thumbsUp').html(data.praise.thumbsUp)
            $('#thumbsDown').html(data.praise.thumbsDown)
            $('#title').html(data.article.title)
            $('#message').html(data.praise.message)
            $('#tags').html(data.article.tags)
            $('#content').html(data.article.content)
        });

        // 代码高亮
        $('pre code').each(function (index, element) {
            var that = $(this);
            var html = that.html().trim();
            that.empty();
            that.text(html);

            console.log('sasasasas')
        });

        $('#exampleModal').modal('show')
    });

    hljs.highlightAll()

    function toUpdateArticle() {
        location.href = "admin/article?articleId=" + $('#articleId').val()
    }

    function deleteArticle() {
        const articleId = $('#articleId').val()
        $.ajax({
            url: "admin/API/article/delete?articleId=" + articleId,
            type: "get",
            dataType: 'json'
        }).done(function (data) {// 请求成功的回调
            if (data.error === 0) {
                alert('删除成功！');
                location.href = 'admin/articleManager';
            } else {
                alert('删除失败！')
            }
        });
    }

</script>
</body>
</html>