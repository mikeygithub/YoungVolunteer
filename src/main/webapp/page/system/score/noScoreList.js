layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //列表
    var tableIns = table.render({
        elem: '#userList',
        url : '../../../biz/volunteersignup_findAllVolunteerNoScore.action',
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

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
            if(layEvent === 'edit'){
                layer.prompt({
                    formType: 2,
                    value: '评分',
                    title: '请输入分数',
                    area: ['800px', '350px'] //自定义文本域宽高
                }, function(value, index, elem){
                    $.get("../../../biz/volunteersignup_updateVolunteerSignUp.action",{
                        activityId : data.activityId,
                        signUpId : data.signUpId,
                        volunteerScore : value,
                        volunteerId : data.volunteerId,
                        volunteerName : data.volunteerName,
                        activityName: data. activityName
                    },function(data){
                        if (data.code === 0){
                            layer.msg("评分成功");
                        }else {
                            layer.msg("评分失败");
                        }
                        tableIns.reload();
                        layer.close(index);
                    })
                    layer.close(index);
                });


        }
    });

})
