<%--
  Created by ccy.
  User: ccy
  Date: 2017/8/13
  Time: 16:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jsp" %>
    <link href="/css/imageView.css" rel="stylesheet">
    <title>图片浏览</title>
</head>
<body>
    <div class="container">
        <div style="margin: 30px;"></div>
        <a href="/image/imageView?type=other"><button class="otherImage btn btn-primary btn-lg">最近漫友收藏</button></a>
        <a href="/image/imageView?type=self"><button class="selfImage btn btn-primary btn-lg">我的收藏</button></a>
        <a href="/image/imagesUpload"><button class="selfImage btn btn-primary btn-lg">上传图片</button></a>
        <p style="text-align: center" id="loading"><i class="fa fa-spinner fa-spin fa-2x fa-fw "></i>加载中...</p>
        <div></div>
        <div class="imageContainer">
            <div class="imageWrapper" id="imageWrapperID">
                <img src="" class="imageCeil" id="templetCeil">
                <div class="imageDesc">
                    <div class="imageHeader">
                        <div class="collection">
                            <span>
                                <i class="fa fa-star" aria-hidden="true"></i>
                            </span>
                        </div>
                    </div>
                    <div class="imageBottom">
                        <span class="imageName">未命名</span>
                    </div>
                </div>

            </div>
        </div>
        <div style="clear: both"></div>
        <nav aria-label="Page navigation" class="navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous" id="prePage">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li id="page"><a href="#">1</a></li>
                <li>
                    <a href="#" aria-label="Next" id="nextPage">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>

<%@include file="footer.jsp" %>
    <script src="/js/imageView/imageView.js"></script>
</body>
</html>
