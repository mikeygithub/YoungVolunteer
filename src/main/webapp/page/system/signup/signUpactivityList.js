layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '../../../biz/activity_findAllVolunteerJoinActivity.action?volunteerId='+window.sessionStorage.getItem("userId"),
        cellMinWidth : 155,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'activityCode', title: '活动编号', align:'center'},
            {field: 'activityTitle', title: '活动标题', minWidth:100, align:"center"},
            {field: 'activityContent', title: '活动内容', minWidth:200, align:'center'},
            {field: 'activityLeader', title: '活动负责人', minWidth:50, align:'center'},
            {field: 'activityPeopleNum', title: '活动人数', minWidth:50, align:'center'},
            {field: 'activityStartTime', title: '活动开始时间', minWidth:100, align:'center'},
            {field: 'activityEndTime', title: '活动结束时间', minWidth:100, align:'center'},
            {field: 'activitySignStartTime', title: '活动报名开始时间', minWidth:100, align:'center'},
            {field: 'activitySignEndTime', title: '活动报名结束时间', minWidth:100, align:'center'},
            {title: '操作', minWidth:50, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            var index = layer.msg('查询中,请稍候...',{icon: 16,time:false,shade:0})
            setTimeout(function(){
                table.reload("userListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    key: $(".searchVal").val()  //搜索的关键字
                }
            });
            layer.close(index);
        },800)
        } else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "activityAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.activityId);
                    body.find(".activityCode").val(edit.activityCode);
                    body.find(".activityTitle").val(edit.activityTitle);
                    body.find(".activityLeader").val(edit.activityLeader);
                    body.find(".activityPeopleNum").val(edit.activityPeopleNum);
                    body.find(".activityContent").val(edit.activityContent);
                    body.find(".activityStartTime").val(edit.activityStartTime);
                    body.find(".activityEndTime").val(edit.activityEndTime);
                    body.find(".activitySignStartTime").val(edit.activitySignStartTime);
                    body.find(".activitySignEndTime").val(edit.activitySignEndTime);
                    body.find(".updateFlag").val(1);//更新
                    form.render();
                }
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].activityId);
            }
            layer.confirm('确定退出选中活动？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("../../../biz/activity_deleteAllVolunteerActivity.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                tableIns.reload();
                layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
         if(layEvent === 'out'){ //删除
            layer.confirm('确定退出此活动？',{icon:3, title:'提示信息'},function(index){
                $.get("../../../biz/volunteersignup_outVolunteerSignUp.action",{
                    activityId : data.activityId,
                    volunteerId : window.sessionStorage.getItem("userId")
                },function(data){
                    if (data.code === 0){
                        layer.msg("退出成功");
                    }else {
                        layer.msg("退出失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

})
