<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/22
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="include.jsp" %>
</head>
<body>
<form id="form-member-add">
<div class="row cl" style="padding-top: 100px;padding-left: 50px">
    <input name="ip" value="<%=ip%>">
    <%--<label class="form-label col-xs-4 col-sm-3">：</label>--%>
    <div class="formControls col-xs-8 col-sm-9">
        <textarea name="remark" cols="" rows="" class="textarea" placeholder="说点什么...最少输入10个字符""></textarea>
        <%--<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>--%>
    </div>
</div>

<div class="row cl" style="padding-top: 50px;">
    <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
        <input class="btn btn-primary radius" onclick="addMsg()" value="&nbsp;&nbsp;留言&nbsp;&nbsp;">
    </div>
</div>
    <form>

</body>

<script type="text/javascript">

    function addMsg() {
        var formData = new FormData($("#form-member-add")[0]);
        $.ajax({
            url: "<%=path %>/user/addMsg",
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function (res) {
            layer.msg(res.msg, {icon: 1, time: 1000});
        });
    }
    $(function() {

    })
</script>
</html>
