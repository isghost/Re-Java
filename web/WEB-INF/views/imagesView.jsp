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
<body>
    <%--<form action="/images/upload1" method="post" enctype="multipart/form-data">--%>
        <%--<input id="fileupload1" type="file" name="image" multiple>--%>
        <%--<<input type="submit">>--%>
    <%--</form>--%>
<%@include file="footer.jsp" %>
    <input id="fileupload" type="file" accept="image/*" name="image" data-url="/images/upload1" multiple>
    <script src="/js/libs/jqueryfileup/vendor/jquery.ui.widget.js"></script>
    <script src="/js/libs/jqueryfileup/jquery.iframe-transport.js"></script>
    <script src="/js/libs/jqueryfileup/jquery.fileupload.js"></script>
    <script>
        $(function () {
            $('#fileupload').fileupload({
                dataType: 'json',
                done: function (e, data) {
                    console.log("????");
                    $.each(data.result.files, function (index, file) {
                        $('<p/>').text(file.name).appendTo(document.body);
                    });
                }
            });
        });
    </script>
</body>
</html>
