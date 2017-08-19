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
    <title>图片浏览</title>
</head>
<%-- TODO 美化界面--%>
<body>
    <%--<form action="/image/upload1" method="post" enctype="multipart/form-data">--%>
        <%--<input id="fileupload1" type="file" name="image" multiple>--%>
        <%--<<input type="submit">>--%>
    <%--</form>--%>
    <div class="container">
        <input id="fileupload" type="file" accept="image/*" name="image" data-url="/image/upload1" multiple>
        <div class="uploadWrapper">

        </div>
    </div>
<%@include file="footer.jsp" %>
    <script src="/js/libs/jqueryfileup/vendor/jquery.ui.widget.js"></script>
    <script src="/js/libs/jqueryfileup/jquery.iframe-transport.js"></script>
    <script src="/js/libs/jqueryfileup/jquery.fileupload.js"></script>
    <script>
        $(function () {
            $('#fileupload').fileupload({
                dataType: 'json',
                done: function (e, data) {
                    $.each(data.result, function (index, status) {

                        let newP = $('<p/>');
                        if(status){
                            newP.text(index + " 上传成功");
                        }
                        else{
                            newP.text(index + " 失败，请稍后重试！")
                        }
                        newP.appendTo(".uploadWrapper");
                    });
                }
            });
        });
    </script>
</body>
</html>
