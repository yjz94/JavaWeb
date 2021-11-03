<%--
  Created by IntelliJ IDEA.
  User: yujiangzhong
  Date: 2021/10/20
  Time: 6:15 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>Admin - Attachment - FishLand</title>
    <%@ include file="../common/baseLib.jsp" %>

    <!-- admin css -->
    <link href="css/attachment.css" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/wangeditor@latest/dist/wangEditor.min.js"></script>
</head>
<body>
<div>
    <form action="http://localhost:8080/JavaWeb/uploadImg" target="myIframe" enctype="multipart/form-data"
          method="post">
        <div class="form-group">
            <input type="hidden" value="uploadImg" name="action" id="action">
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

        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card" style="width: 18rem; margin: 10px;">
                <img src="../../../imgs/temp1.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">attment1</h5>
                    <p class="card-text">这是附件的简......</p>
                </div>
            </div>
        </div>

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

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/feather-icons/4.9.0/feather.min.js"></script>
<%--图标--%>
<script src="https://cdn.bootcdn.net/ajax/libs/Chart.js/3.5.0/chart.min.js"></script>
<script src="js/admin.js"></script>
</body>
</html>