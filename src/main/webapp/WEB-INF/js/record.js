layui.use(['table','form','jquery','laypage','layer', 'element', 'laydate'], function () {
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var laypage = layui.laypage;

    var element = layui.element;

    var layer=layui.layer;

    var laydate = layui.laydate;

    //执行一个table 实例
    var tableIns = table.render({
        elem: '#record',//需要实例化表格的id
        height: 600,
        initSort: {
            field: 'no' //排序字段，对应 cols 设定的各字段名
            ,type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            },
        method:'post',

        url: '../test/getAll' //向后台发送请求获取对应的数据
        ,   //   w为什么要写这样的请求？
        cellMinWidth: 80
        ,
        title: '收支记录',
        page: true //开启分页
        ,
        limit: 10,
        limits: [10, 15, 20]
        ,
        toolbar: '#headDemo' //default表示框架的默认按钮,大多数情况需要自定义按钮
        ,
        parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.msg, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data //解析数据列表
            };
        },
        request: {
            pageName: 'page' // 页码的参数名称，默认：page
            ,limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        ,
        cols: [
            [ //为什么要展示下面的这些数据？   list<video>-->转换成了json
                {
                    type: 'checkbox', //复选框
                  //  fixed: 'left'  //固定在最左侧
                }, {
                field: 'no', //必须和返回的json格式数据的key值一摸一样
                title: 'NO',
                sort: true, //排序
                width: 70,
               // fixed: 'left'
            }, {
                field: 'userName',
                title: '用户',
                width: 95,
            }, {
                field: 'money',
                title: '金额',
                width: 90,
                sort: true,
            }, {
                field: 'status',
                title: '收支',
                width: 100,
                templet: function (d) {
                    //可以在这里写逻辑关系
                    //根据0  1   显示不同的状态
                    var s = d.status
                    var vid = d.no
                    if (s == 1) {
                        return '<input type="checkbox"  value="' + vid + '" checked  name="status"  lay-skin="switch" lay-text="收入|支出" lay-filter="moneystatus">';
                    } else if (s == 0) {
                        return '<input type="checkbox"  value="' + vid + '" name="status"  lay-skin="switch" lay-text="收入|支出" lay-filter="moneystatus">';
                    }
                }
            }, {
                field: 'info',
                title: '备注',
                width: '48%',
                align: 'center',
            }, {
                field: 'time',
                title: '时间',
                width: 105,
                align: 'right',
            }, {
                fixed: 'right',
                width: 165,
                align: 'center',
                toolbar: '#barDemo'
            }
            ]
        ]

    });

    table.on('sort(test)', function(obj){
        console.log(obj.field);
        table.reload('record', {
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                field: obj.field //排序字段   在接口作为参数字段  field order
                ,order: obj.type //排序方式   在接口作为参数字段  field order
            }
        });
    });

    //监听头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
        var datas = checkStatus.data; //获取选中的数据


        var ids = new Array();
        for(var i in datas){
            ids[i] = datas[i].no;
        }
        switch(obj.event){
            case 'add':
                layer.open({

                    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                    type: 2,
                    title: "添加收支记录",
                    area: ['420px', '400px'],
                    content: 'add.html',//引用的弹出层的页面层的方式加载修改界面表单
                });
                break;
            case 'delete':
                if(datas.length === 0){
                    layer.msg('请选择一行');
                } else {
                    layer.confirm('确认删除这些数据吗？', function(index){
                        $.ajax({ //jquery的ajax形式
                            type: 'post',//请求的方式
                            url: '../test/deleteByNos',//向后台发送请求的地址
                            data: { //表示向后台传入的参数 key-value
                                'ids':ids,
                            },
                            dataType: 'json',//告诉后台返回的数据必须是json格式的数据
                            traditional:true,//传数组必须
                            success:function (res) {
                                if(res.code == 200){
                                    layer.alert(res.msg,{icon:1},function (index) {
                                        layer.close(index);
                                        tableIns.reload();
                                    })
                                }else{
                                    layer.alert(res.msg,{icon:2},function (index) {
                                        layer.close(index);
                                    })
                                }
                            }
                        })
                    });
                }
                break;
        };
    });
    //监听行工具栏
    table.on('tool(test)', function(obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"

        //行
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
        var vid = data.no;


        if(layEvent === 'detail'){ //查看
            //do somehing
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确认删除这些数据吗？', function(index){
                $.ajax({ //jquery的ajax形式
                    type: 'post',//请求的方式
                    url: '../test/deleteByNo',//向后台发送请求的地址
                    data: { //表示向后台传入的参数 key-value
                        'no': vid,
                    },
                    dataType: 'json',//告诉后台返回的数据必须是json格式的数据
                    success: function (res) { // 当请求成功以后的回调函数  res会直接接受后台返回的json格式的数据
                        if(res.code == 200){
                            layer.alert(res.msg,{icon:1},function (index) {
                                layer.close(index);
                                tableIns.reload();

                            })
                        }else{
                            layer.alert(res.msg,{icon:2},function (index) {
                                layer.close(index);
                            })
                        }
                    }


                })
            });
        } else if(layEvent === 'edit'){ //编辑
            $.ajax({
                type: 'post',//请求的方式
                url: '../test/getEdit',//向后台发送请求的地址
                data: { //表示向后台传入的参数 key-value
                    'no': vid,
                },
                dataType: 'json',
                success: function (res) {


                    layer.open({

                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 2,
                        title: "修改收支记录信息",
                        area: ['420px', '400px'],
                        content: 'edit.html',//引用的弹出层的页面层的方式加载修改界面表单
                        success: function(layero, index){
                            var body = layer.getChildFrame('body',index);

                            body.find('#no').val(res.no);
                            body.find('#userId').val(res.userId);
                            body.find('#money').val(res.money);

                            body.find('#info').val(res.info);
                            body.find('#time').val(res.time);

                        }

                    });
                }
            })
        } else if(layEvent === 'LAYTABLE_TIPS'){
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });

    form.on('switch(moneystatus)', function (data) {
        //向后台发送请求  ajax  使用jquery的形式编写ajax
        var check = data.elem.checked == true ? 1 : 0;
        var vid = data.value;

        $.ajax({ //jquery的ajax形式
            type: 'post',//请求的方式
            url: '../test/updataStatus',//向后台发送请求的地址
            data: { //表示向后台传入的参数 key-value
                'status': check,
                'no': vid,
            },
            dataType: 'json',//告诉后台返回的数据必须是json格式的数据
            success: function (res) { // 当请求成功以后的回调函数  res会直接接受后台返回的json格式的数据
                layer.msg(res.msg);
            }


        })

    });


    $("#re").on("click", function () {
        table.reload('record',{
            url: "../test/getSomeData",
            request: {
                pageName: 'page' // 页码的参数名称，默认：page
                ,limitName: 'limit' //每页数据量的参数名，默认：limit
            },
            where: { //设定异步数据接口的额外参数，任意设
                "moneymin": $("#moneymin").val(),
                "moneymax": $("#moneymax").val(),
                "info": $("#info").val(),
                "status": $("#status").val(),
            }
            , page: {
                curr: 1 //重新从第 1 页开始
            }
        })
    })

});