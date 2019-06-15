layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '../../../biz/volunteersignup_findAllVolunteerNoSignUp.action?volunteerId='+window.sessionStorage.getItem("userId"),
        cellMinWidth : 160,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'activityId', title: '活动编号', align:'center'},
            {field: 'signIn', title: '是否已签到', align:'center',templet:function (d) {
                    return d.signIn===null?'未签到':'已签到';
                }},
            {field: 'activityName', title: '活动标题',  align:"center"},
            {field: 'volunteerScore', title: '分数',  align:'center',templet:function(d){
                    return d.volunteerScore==null?'未评分':d.volunteerScore;
                }},
            {field: 'volunteerName', title: '姓名', minWidth:100, align:'center'},
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

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].activityId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
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
         if(layEvent === 'del'){
            layer.confirm('确定签到？',{icon:3, title:'提示信息'},function(index){
                $.get("../../../biz/volunteersignup_volunteerSignIn.action",{
                    signUpId: data.signUpId
                },function(data){
                    if (data.code === 0){
                        layer.msg("签到成功");
                    }else {
                        layer.msg("签到失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

})
