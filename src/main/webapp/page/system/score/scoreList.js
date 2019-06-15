layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '../../../biz/volunteersignup_findAllVolunteerScore.action',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'activityCode', title: '活动编号', align:'center'},
            {field: 'activityName', title: '活动标题', minWidth:100, align:"center"},
            {field: 'volunteerScore', title: '分数', minWidth:100, align:'center'},
            {field: 'volunteerName', title: '姓名', minWidth:100, align:'center'},
            {title: '操作', minWidth:100, templet:'#userListBar',fixed:"right",align:"center"}
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

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "是否确定禁用此用户？",
                btnText = "已禁用";
            if(_this.text()=="已禁用"){
                usableText = "是否确定启用此用户？",
                btnText = "已启用";
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                _this.text(btnText);
                layer.close(index);
            },function(index){
                layer.close(index);
            });
        }else
            if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("../../../biz/volunteersignup_saveVolunteerSignUp.action",{
                    activityId : data.activityId  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'scoring'){
            layer.confirm('确定打分此活动？',{icon:3, title:'提示信息'},function(index){
                $.get("../../../biz/volunteersignup_saveVolunteerSignUp.action",{
                    activityId : data.activityId,  //将需要打分的newsId作为参数传入
                    userId : window.sessionStorage.getItem("userId")
                },function(data){
                    if (data.code === 0){
                        layer.msg("报名成功");
                    }else {
                        layer.msg("报名失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

})
