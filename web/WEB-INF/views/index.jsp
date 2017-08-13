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
    <title>首页</title>
</head>
<body>
    <div class="container">
        <h1>这里是首页</h1>

        <h3><img src="/images/A.jpg" alt="" style="height: 100px;width: 100px;"><a href="/profile">这里是户个人信息，登录后可以访问</a></h3>
        <h3><img src="/images/D.jpg" alt="" style="height: 100px;width: 100px;"><a href="/subpage">这里是不需要登录的子界面</a></h3>
        <h3><img src="/images/L.jpg" alt="" style="height: 100px;width: 100px;"><a href="/account/login">这里是登录界面，目前已经完成</a></h3>
        <h3><img src="/images/S.jpg" alt="" style="height: 100px;width: 100px;"><a href="/account/register">这里是注册界面，目前基本完成</a></h3>
        <h3 style="color:red"><img src="/images/icons/aa.jpg" alt="" style="height: 100px;width: 100px;"><a href="/images/imageView">获取收藏的图片信息</a></h3>
        <%--<h3 style="color:red"><img src="/images/S.jpg" alt="" style="height: 100px;width: 100px;"><a href="/images/collections?pageNum=1">获取收藏的图片信息</a></h3>--%>
        <h3 style="color:red"><img src="/images/icons/bb.jpg" alt="" style="height: 100px;width: 100px;"><a href="/images/imagesUpload">图片上传界面</a></h3>
        <c:if test="${login == true}">
            <h3><img src="/images/Y.jpg" alt="" style="height: 100px;width: 100px;"><a href="/account/logout">退出登录</a></h3>
        </c:if>
    </div>
<%@include file="footer.jsp" %>
</body>
</html>
