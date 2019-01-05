/**
 * Created by ZQ on 2016-06-13.
 */
(function($) {
    $(function() {
        //获取参数
        var icode = JUDGE.getURLParameter("uname");
        //alert(icode);
        //icode
        if(!JUDGE.isNull(icode)){
            var url = "admin/USERFindUname.do?uname="+icode;
            $.ajax( {
                type : "post",
                url : url,
                contentType : "application/x-www-form-urlencoded",
                error : function(event,request, settings) {
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success : function(data) {
                    var jsondata = eval(data);
                    $("#uname").attr("value",jsondata.aduser.uname);
                    $("#remarks").attr("value",jsondata.aduser.remarks);
                    $("#access").val(jsondata.aduser.access);
                    $("#uname").attr("readonly",true);

                }
            });
        }


        $("#btnCancel").click(function(){
            window.parent.$('#wedit').window('close');
        });

        $("#btnSave").click(function(){
            //表单验证
            if($("#uname").val().length == 0 || $("#remarks").val().length == 0) {
                $.messager.alert("提示消息", "请输入登录名及用户姓名!", "info");
                return;
            }
            var uname = $('#uname').val();
            var remarks = encodeURI($('#remarks').val());
            var access = $('#access').val();

            $.ajax ({
                type:"post",
                url:"admin/UserSaveUpdate.do",
                data:{
                    uname:uname,
                    remarks:remarks,
                    access:access
                },
               contentType : "application/x-www-form-urlencoded",
                error : function(event,request, settings) {
                $.messager.alert("提示消息", "请求失败!", "info");
              },
              success :  function(data) {
                    var jasondata = eval(data);
                    if(jasondata.retcode==0){
                        //自身主键刷新，不要出现重复保存的情况
                        if(jasondata.savetype=="insert"){
                            $.messager.alert("提示消息", "新增保存成功!", "info",function(){
                                window.parent.$('#dataview').treegrid('reload');
                                window.parent.$('#wedit').window('close');
                            });
                        }
                        else{
                            $.messager.alert("提示消息", "编辑保存成功!", "info",function(){
                                window.parent.$('#dataview').treegrid('reload');
                                window.parent.$('#wedit').window('close');
                            });
                        }
                    }
                    else{
                        $.messager.alert("提示消息", "保存失败!", "info");
                    }
                }
            });
        });

    });
})(window.jQuery);