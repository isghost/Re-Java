<%--
  Created by ccy.
  User: ccy
  Date: 2017/8/10
  Time: 23:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jsp" %>
    <link href="/css/imageUpload.css" rel="stylesheet">
    <title>图片浏览</title>
</head>
<%-- TODO 美化界面--%>
<body>

    <div class="container">
        <a href="/image/imageView" ><button class="selfImage btn btn-primary btn-lg" style="margin: 30px">查看收藏图片</button></a>
        <div class="uploadWrapper">
            <span class="btn btn-success fileInput-button" onclick="fileupload.click()">
                <span>图片上传</span>
                <input id="fileupload" type="file" accept="image/*" name="image" data-url="/image/upload1" multiple style="display: none">
            </span>
            <p class="uploadTip">
                可以拖曳图片到该区域完成，完成上传
            </p>
            <p class="uploadTip2">
                支持单张 5M 以内图片上传
            </p>
            <p class="uploadWarn">
                [注意]请严格遵守当地法律法规。
            </p>
        </div>
        <div class="imageUploadStatus">
            <div class="statusItem" id="statusItemTemp">
                <img src="">
                <span class="imageName">图片名称</span>
                <span class="imageStatusDesc">状态描述</span>
            </div>
        </div>
    </div>
<%@include file="footer.jsp" %>
    <script src="/js/libs/jqueryfileup/vendor/jquery.ui.widget.js"></script>
    <script src="/js/libs/jqueryfileup/jquery.iframe-transport.js"></script>
    <script src="/js/libs/jqueryfileup/jquery.fileupload.js"></script>
    <script src="/js/imageView/imageUpload.js"></script>
</body>
</html>
