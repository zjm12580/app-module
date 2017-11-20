<%--
  Created by IntelliJ IDEA.
  User: zhujiamin
  Date: 2017/11/2
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath() + "resource";
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + "resource";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <%--<script type="text/javascript" src="<%=basePath %>/zhujiamin.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/h-ui.admin/css/style.css"/>

    <link href="<%=basePath %>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript"
            src="<%=basePath %>/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>

    <!--_footer 作为公共模版分离出去-->
    <script type="text/javascript" src="<%=basePath %>/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/h-ui.admin/js/H-ui.admin.js"></script>
    <!--/_footer 作为公共模版分离出去-->

    <!--请在下方写此页面业务相关的脚本-->
    <script type="text/javascript" src="<%=basePath %>/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>

    <%--<script type="text/javascript"--%>
    <%--src="<%=path %>/lib/html5shiv.js"></script>--%>
</head>
<body>

</body>
</html>
