<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by ccy.
  User: ccy
  Date: 2017/8/6
  Time: 20:23
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jsp" %>
    <link href="/css/index.css" rel="stylesheet">
    <title>轻说网</title>
</head>
<body>
    <div class="container" >
        <h1 style="text-align: center">轻说网</h1>
        <h6 style="text-align: right">狗群友要的五光十色背景</h6>
        <div class="item" style="background-color: #aeabd8">
            <a href="/profile">
                <div class="icon"><img src="/images/A.jpg" alt=""></div>
            </a>
            <div class="itemName"><h1>个人信息</h1></div>
            <div class="itemDesc">查看用户ID，帐号名称，加密盐</div>
        </div>
        <%--<div class="item" style="background-color: #d2a689" >--%>
            <%--<a href="/subpage">--%>
                <%--<div class="icon"><img src="/images/D.jpg" alt=""></div>--%>
            <%--</a>--%>
            <%--<div class="itemName"><h1>普通页面</h1></div>--%>
            <%--<div class="itemDesc">不需要登录就能直接访问</div>--%>
        <%--</div>--%>

        <div class="item" style="background-color: #ded2c2">
            <a href="/account/login">
                <div class="icon"><img src="/images/L.jpg" alt=""></div>
            </a>
            <div class="itemName"><h1>登录</h1></div>
            <div class="itemDesc">用户登录界面</div>
        </div>
        <div class="item" style="background-color: #dadfe3">
            <a href="/account/register">
                <div class="icon"><img src="/images/icons/dd.jpg" alt=""></div>
            </a>
            <div class="itemName"><h1>注册</h1></div>
            <div class="itemDesc">用户注册，新增验证码功能</div>
        </div>
        <div class="item" style="background-color: #f1f1f1">
            <a href="/image/otherImageView">
                <div class="icon"><img src="/images/icons/aa.jpg" alt=""></div>
            </a>
            <div class="itemName"><h1>图片浏览</h1></div>
            <div class="itemDesc">查看自己或者别人上传的图片</div>
        </div>
        <div class="item" style="background-color: #dcdcdc">
            <a href="/image/imagesUpload">
                <div class="icon"><img src="/images/icons/bb.jpg" alt=""></div>
            </a>
            <div class="itemName"><h1>上传图片</h1></div>
            <div class="itemDesc">可以上传各种类型的图片，最多5M，腾讯云50G流量</div>
        </div>
        <c:if test="${login == true}">
            <div class="item" style="background-color: #d7e7f3">
                <a href="/account/logout">
                    <div class="icon"><img src="/images/Y.jpg" alt=""></div>
                </a>
                <div class="itemName"><h1>退出登录</h1></div>
                <div class="itemDesc">退出登录</div>
            </div>
        </c:if>
        <%--<h3><img src="/images/A.jpg" alt="" style="height: 100px;width: 100px;"><a href="/profile">这里是户个人信息，登录后可以访问</a></h3>--%>
        <%--<h3><img src="/images/D.jpg" alt="" style="height: 100px;width: 100px;"><a href="/subpage">这里是不需要登录的子界面</a></h3>--%>
        <%--<h3><img src="/images/L.jpg" alt="" style="height: 100px;width: 100px;"><a href="/account/login">这里是登录界面，目前已经完成</a></h3>--%>
        <%--<h3><img src="/images/S.jpg" alt="" style="height: 100px;width: 100px;"><a href="/account/register">这里是注册界面，目前基本完成</a></h3>--%>
        <%--<h3 style="color:red"><img src="/images/icons/aa.jpg" alt="" style="height: 100px;width: 100px;"><a href="/image/imageView">获取收藏的图片信息</a></h3>--%>
        <%--&lt;%&ndash;<h3 style="color:red"><img src="/images/S.jpg" alt="" style="height: 100px;width: 100px;"><a href="/images/collections?pageNum=1">获取收藏的图片信息</a></h3>&ndash;%&gt;--%>
        <%--<h3 style="color:red"><img src="/images/icons/bb.jpg" alt="" style="height: 100px;width: 100px;"><a href="/image/imagesUpload">图片上传界面</a></h3>--%>
        <%--<c:if test="${login == true}">--%>
            <%--<h3><img src="/images/Y.jpg" alt="" style="height: 100px;width: 100px;"><a href="/account/logout">退出登录</a></h3>--%>
        <%--</c:if>--%>
    </div>
<%@include file="footer.jsp" %>
</body>
</html>
