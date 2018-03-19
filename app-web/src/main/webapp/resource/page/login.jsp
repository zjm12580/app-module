﻿<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="include.jsp" %>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>后台登录 - H-ui.admin v3.1</title>
    <meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value=""/>
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="userForm" class="form form-horizontal"  method="post">
        <div class="row cl">
            <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
            <div class="formControls col-xs-8">
                <input id="username" name="userName" type="text" placeholder="用户名" class="input-text size-L">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
            <div class="formControls col-xs-8">
                <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <input class="input-text size-L" name="indentifyCode" type="text" placeholder="验证码"
                       onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}"
                       value="验证码:" style="width:150px;">
                <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a></div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <label for="online">
                    <input type="checkbox" name="online" id="online" value="">
                    使我保持登录状态</label>
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-xs-offset-3">
                <input id="login" name="" class="btn btn-success radius size-L"
                       value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                <%--<input name="" type="reset" class="btn btn-default radius size-L"--%>
                       <%--value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">--%>
            </div>
        </div>
        </form>
    </div>
</div>
<div class="footer">Copyright 你的公司名称 by H-ui.admin v3.1</div>
<!--此乃百度统计代码，请自行删除-->
<script>


    function toLogin() {
        var userName = $("#username").val();
        var password = $("#password").val();
        console.info(userName+"---"+password);
        var param={
            userName:userName,
            password:password
        }
        $.ajax({
            url: "<%=host %>/app/toLogin",
            dataType:"json",
            contentType: "application/json; charset=utf-8",
            type: 'POST', //GET,
            data:JSON.stringify(param),
            success: function (data) {
                console.info(data.status)
                if (data == null||data.status==false) {
                    layer.msg('系统异常', {icon: 5});
                }
                if (data.status) {
                    window.location = "<%=host %>/app/index";
                }
            },
            error: function (xhr, textStatus) {
                console.log('错误')
                console.log(xhr)
                console.log(textStatus)
                layer.msg('返回错误', {icon: 5});
            }
        })
    }
    $(function () {
        $("#login").bind("click", function () {
            toLogin();
        });
    });
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
<!--/此乃百度统计代码，请自行删除
</body>
</html>