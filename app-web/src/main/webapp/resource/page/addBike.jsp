<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/4
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="include.jsp" %>
    <title>Title</title>
</head>
<body>
<article class="cl pd-20">
    <form action="<%=path %>/app/add" method="post" enctype="multipart/form-data"  class="form form-horizontal" id="form-member-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单车名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="name" name="name">
            </div>
        </div>
        <%--<div class="row cl">--%>
            <%--<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>性别：</label>--%>
            <%--<div class="formControls col-xs-8 col-sm-9 skin-minimal">--%>
                <%--<div class="radio-box">--%>
                    <%--<input name="sex" type="radio" id="sex-1" checked>--%>
                    <%--<label for="sex-1">男</label>--%>
                <%--</div>--%>
                <%--<div class="radio-box">--%>
                    <%--<input type="radio" id="sex-2" name="sex">--%>
                    <%--<label for="sex-2">女</label>--%>
                <%--</div>--%>
                <%--<div class="radio-box">--%>
                    <%--<input type="radio" id="sex-3" name="sex">--%>
                    <%--<label for="sex-3">保密</label>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>单价：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="price" name="price">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>租金：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" placeholder="@" name="rent" id="rent">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">附件：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
				<input class="input-text upload-url" type="text" name="uploadfile" id="uploadfile" readonly nullmsg="请添加附件！" style="width:200px">
				<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
				<input type="file" multiple name="file" class="input-file">
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">类型：</label>
            <div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select class="select" size="1" name="type">
					<option value="" selected>选择单车类型</option>
					<option value="1">类型1</option>
					<option value="2">类型2</option>
					<option value="3">类型3</option>
				</select>
				</span> </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">备注：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="remark" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="textarealength(this,100)"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" onclick="addBike()" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>


<script>

    function addBike() {
        var formData = new FormData($("#form-member-add")[0]);
        $.ajax({
            url: "<%=path %>/app/add",
            type: 'POST',
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function (res) {
            alert(res);
        });
    }

</script>
</body>
</html>
