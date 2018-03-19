<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="com.app.entity.Bike"%>--%>
<%@ page import="java.util.*"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <%@ include file="include.jsp" %>
    <title>Title</title>
</head>
<body>
<article class="cl pd-20">
    <form action="<%=path %>/app/add" method="post" enctype="multipart/form-data"  class="form form-horizontal" id="form-member-add">
        <input type="hidden" class="input-text" value="${user.id}"  placeholder="" id="id" name="id">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>用户名：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.userName}" placeholder="" id="name" name="userName">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>手机号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.phone}" placeholder=""  id="price" name="phone">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>邮箱：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="${user.email}" placeholder=""  id="emaail" name="emaail">
            </div>
        </div>
        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-3">附件：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">--%>
				<%--<input class="input-text upload-url" type="text" name="uploadfile" id="uploadfile" readonly nullmsg="请添加附件！" style="width:200px">--%>
				<%--<a href="javascript:;" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>--%>
				<%--<input type="file" multiple name="file" class="input-file">--%>
				<%--</span> </div>--%>
        <%--</div>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">部门：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="type">
					<option value="" selected>请选择部门</option>
					<option value="1">客服</option>
					<option value="2">财务</option>
					<option value="3">管理</option>
				</select>
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,100)">${bike.remark}</textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" onclick="updateUser()" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>


<script>

    $(function() {
//        alert("$!{bike.name}");
        <%--$.ajax({--%>
        <%--url: "<%=path %>/app/getBikes?id="+id,--%>
        <%--type: 'POST',--%>
        <%--cache: false,--%>
        <%--data: formData,--%>
        <%--processData: false,--%>
        <%--contentType: false--%>
        <%--}).done(function (res) {--%>
        <%--alert(res);--%>
        <%--});--%>
    })
    function updateUser() {
        var formData = new FormData($("#form-member-add")[0]);
        $.ajax({
            url: "<%=path %>/user/updateUser",
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function (res) {
            layer.msg(res.msg, {icon: 1, time: 1000});
        });
    }

</script>
</body>
</html>
