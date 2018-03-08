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
    <title>Title</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户中心 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="text-c"> 创建时间：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
        <input type="text" class="input-text" style="width:250px" placeholder="输入单车名称" id="bikeName" name="name">
        <button type="submit" onclick="reload()" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索单车</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
        <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
            批量删除</a>
        <a href="javascript:;" onclick="member_add('添加单车','addBike','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增单车</a>
    </span> <span class="r">共有数据：<strong>88</strong> 条</span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-hover table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="80">id</th>
                <th width="80">名称</th>
                <th width="100">类型</th>
                <th width="40">图片</th>
                <th width="90">备注</th>
                <th width="150">单价</th>
                <th width="">租金</th>
                <th width="130">创建时间</th>
                <th width="70">修改时间</th>
            </tr>
            </thead>
            <tbody>
            <%--<tr class="text-c">--%>
                <%--<td><input type="checkbox" value="1" name=""></td>--%>
                <%--<td>1</td>--%>
                <%--<td><u style="cursor:pointer" class="text-primary" onclick="member_show('张三','member-show.html','10001','360','400')">张三</u></td>--%>
                <%--<td>男</td>--%>
                <%--<td>13000000000</td>--%>
                <%--<td>admin@mail.com</td>--%>
                <%--<td class="text-l">北京市 海淀区</td>--%>
                <%--<td>2014-6-11 11:11:42</td>--%>
                <%--<td class="td-status"><span class="label label-success radius">已启用</span></td>--%>
                <%--<td class="td-manage"><a style="text-decoration:none" onClick="member_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="member_edit('编辑','member-add.html','4','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','270')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>--%>
            <%--</tr>--%>
            </tbody>
        </table>
    </div>
</div>
<script type="text/javascript">

    var table;
    function reload() {
        $('.table-sort').DataTable().ajax.reload();
    }
//    var column=[
//        {"data": "id", name:"id"},
//        {"data": "name", name:"name" },
//        {"data": "type", name:"type"},
//        {"data": "pictureUrl", name:"pictureUrl" },
//        {"data": "remark", name:"remark"},
//        {"data": "price", name:"price" },
//        {"data": "rent", name:"rent" },
//        {"data": "ctime", name:"ctime" },
//        {"data": "mtime", name:"mtime"  }
//    ];

var column=[
    {"data": "id"},
    {"data": "name"},
    {"data": "type"},
    {"data": "pictureUrl"},
    {"data": "remark"},
    {"data": "price"},
    {"data": "rent"},
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
            "ajax": {
                "url": "<%=path %>/app/getBikes",
                "type":"post",
                "data": function (data) {
                    data.pageIndex = (data.start / data.length) + 1;
                    data.pageSize=50;
                    data.name = $("#bikeName").val();
                    data.datemax= $("#datemax").val();
                    data.datemin= $("#datemin").val();

                }
            },
//            "fnServerParams": function (aoData) {
//                aoData.push({
//                        name: "name",
//                        value: $("#bikeName").val()
//                    },
//                    {
//                        name: "datemax",
//                        value: $("#datemax").val()
//                    },
//                    {
//                        name: "date",
//                        value: $("#datemin").val()
//                    }
//                )
//
//            },
            "columnDefs": [
                {
                    "targets": [0],
                    "data": "id",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return "<input type=\"checkbox\" name=\"\" value=\"\">";
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
                    "data": "type",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [4],
                    "data": "pictureUrl",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return "<img width='100' class='picture-thumb' src='"+data+"'>";
                    }
                },
                {
                    "targets": [5],
                    "data": "remark",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [6],
                    "data": "price",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [7],
                    "data": "rent",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [8],
                    "data": "ctime",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return formatDateTime(data);
                    }
                },
                {
                    "targets": [9],
                    "data": "mtime",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return formatDateTime(data);
                    }
                }
        ]
        });

    });
    /*用户-添加*/
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-查看*/
    function member_show(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
                    $(obj).remove();
                    layer.msg('已停用!',{icon: 5,time:1000});
                },
                error:function(data) {
                    console.log(data.msg);
                },
            });
        });
    }

    /*用户-启用*/
    function member_start(obj,id){
        layer.confirm('确认要启用吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
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
    function member_edit(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*密码-修改*/
    function change_password(title,url,id,w,h){
        layer_show(title,url,w,h);
    }
    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '',
                dataType: 'json',
                success: function(data){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
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
</script>
</body>
</html>
