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
        <button  onclick="reloadData()" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜索单车</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
        <a href="javascript:;" onclick="batchDelele()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
            批量删除</a>
        <a href="javascript:;" onclick="member_add('添加单车','addBike','','510')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 新增单车</a>
    </span> <%--<span class="r">共有数据：<strong>88</strong> 条</span>--%> </div>
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
                <th width="70">创建时间</th>
                <th width="70">修改时间</th>
                <th width="40">操作</th>
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
            "ordering": false, // 禁止排序
            "ajax": {
                "url": "<%=path %>/app/getBikes",
                "type": "post",
                "data": function (data) {
                    data.name = $("#bikeName").val();
                    data.datemax = $("#datemax").val();
                    data.datemin = $("#datemin").val();

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
                    "data": "type",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return data;
                    }
                },
                {
                    "targets": [4],
                    "data": "pictureUrl",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return "<img width='100' class='picture-thumb' src='"+"<%=path %>/app/downloadPhoto?fileId="+data+"'>";
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
                },
                {
                    "targets": [10],
                    "data": "id",
                    "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                        return "<a style='text-decoration:none' onclick=\"member_modify('编辑单车','updateBikeIndex','','510','500',"+data+")\" href='javascript:;' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a>" +
                            "<a style='text-decoration:none' class='ml-5' onclick=\"delBike('"+data+"')\"  href='javascript:;' title='编辑'><i class='Hui-iconfont'>&#xe6e2;</i></a>";
                    }
                }
//                <a style="text-decoration:none" onclick="product_brand_edit('品牌编辑','codeing.html','1')" href="javascript:;" title="编辑"><i class="Hui-iconfont"></i></a>
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
        layer_show(title,url,w,h);
    }

    function member_modify(title, url, str, w, h, id) {
        layer.open({
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false, //不固定
            maxmin: true,
            shade: 0.4,
            title: title,
            content: url + "?id=" + id
        });
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
    function delBike(id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                type: 'POST',
                url: '<%=path %>/app/delete?id=' + id,
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
                url: '<%=path %>/app/batchDelete',
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
