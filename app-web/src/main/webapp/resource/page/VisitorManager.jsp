<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/13
  Time: 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="include.jsp" %>
    <title>单车管理</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
      </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
    </span> <%--<span class="r">共有数据：<strong>88</strong> 条</span>--%> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">ID</th>
                <th width="100">游客名</th>
                <th width="40">ip</th>
                <th width="150">url</th>
                <th width="50">访问时间</th>
                <th width="300">留言内容</th>

            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">

    var table;
    var column=[
        {"data": "id"},
        {"data": "userId"},
        {"data": "userName"},
        {"data": "password"},
        {"data": "sex"},
        {"data": "phone"},
        {"data": "departent"},
        {"data": "ruleId" },
        {"data": "realName" },
        {"data": "ctime" },
        {"data": "mtime" }
    ];
    $(function(){


        table= $('.table-sort').dataTable({
            "aaSorting": [[ 1, "desc" ]],//默认第几个排序
            "bStateSave": true,//状态保存
            <%--"sAjaxSource": '<%=path %>/app/getBikes',--%>
//            "columns": column,
            "serverSide": true,
            "ordering": false, // 禁止排序
            "ajax": {
                "url": "<%=path %>/user/getVisitorMsg",
                "type": "post",
                "data": function (data) {

                }
            },
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "id",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return "<input type=\"checkbox\" value='"+data+"' name=\"\" value=\"\">";
                    }
                },
                {
                    "targets": [1],
                    "data": "id",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [2],
                    "data": "name",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [3],
                    "data": "ip",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [4],
                    "data": "url",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [5],
                    "data": "ctime",
                    "render": function (data, type, full) {
                        return formatDateTime(data);
                    }
                },
                {
                    "targets": [6],
                    "data": "remark",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [6],
                    "data": "remark",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                }

            ]
        });

    });

    function reloadData() {
//        table.fnClearTable();
//        table.fn
        var table = $('.table-sort').DataTable();
        table.ajax.reload();
    }
    /*用户-添加*/
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-查看*/
    function member_show(title,url,id,w,h){
        layer_show(title, url, w, h);
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '<%=path %>/user/deleteUser?id=' + id,
                success: function(data){
                    if(data.status) {
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        reloadData();
                    }else {
                        layer.msg('删除失败!', {icon: 1, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg(data, {icon: 1, time: 1000});
                },
            });
        });
    }

    function member_modify(title, url, str, w, h, id) {
        layer.open({
            type: 2,
            area: ["510px", "510px"],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: "更新用户信息",
            content: url + "?id=" + id
        });
    }
    /*用户-停用*/
    function member_stop(obj,id,status){
        console.info(id)
        layer.confirm('确认要停用吗？', function (index) {
            $.ajax({
                type: 'GET',
                url: '<%=path %>/user/stopUser?id=' + id + "&status=" + status,
                success: function (data) {
                    if (data.status) {
//                        $('#stopIcon').attr('title','启用');
//                        $('#stopIcon').html('<i class="Hui-iconfont">&#xe631;</i>');
//                        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                        layer.msg(data.msg, {icon: 1, time: 1000});
                        reloadData();
                    } else {
                        layer.msg(data.msg, {icon: 5, time: 1000});
                    }
                },
                error: function (data) {
                    layer.msg("系统异常，请重试", {icon: 5, time: 1000});
                },
            });
        });
    }

    /*用户-启用*/
    function member_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '<%=path %>/user/stopUser',
                dataType: 'json',
                success: function(data){
//  $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');

//                    $('#stopIcon').prepend('<i class="Hui-iconfont">&#xe631;</i>');
//                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
                    $(obj).remove();
                    layer.msg('已启用!',{icon: 6,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }
    /*用户-编辑*/
    function member_edit(id){

        layer.open({
            type: 2,
            area: ['510px','500px'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: "更新用户信息",
            content: "<%=path %>/user/updateUserIndex" + "?id=" + id
        });
    }
    /*密码-修改*/
    function change_password(id){
        layer.open({
            type: 2,
            area: ['600px','270px'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: "修改密码",
            content: "<%=path %>/user/changePasswordIndex" + "?id=" + id
        });
    }

    /*用户-删除*/
    function delBike(id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '<%=path %>/app/deleteUser?id=' + id,
                dataType: 'json',
                success: function(data){
                    if(data.status) {
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        reloadData();
                    }else {
                        layer.msg('删除失败!', {icon: 1, time: 1000});
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }
    function formatDateTime(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };


    function batchDelele() {
        layer.confirm('确认要删除吗？',function(index){
            var td='';
            var leng = $(".table-sort tr").length;
            var filter_numbs = new Array();
            for (var i = 1; i <= leng; i++) {
                td = $(".table-sort tr").eq(i).find("td:first").find("input[type='checkbox']");
                var isChecked = td.is(':checked');
                if (isChecked) {
                    filter_numbs.push(td.val());
                }
            }
            var ids=JSON.stringify(filter_numbs);
            console.log(ids);
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: '<%=path %>/user/batchDelete',
                dataType: 'json',
                data:ids,
                success: function(data){
                    if(data.status) {
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        reloadData();
                    }else {
                        layer.msg(data.msg, {icon: 2, time: 1000});
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }


</script>
</body>
</html>
