/**
 * Created by ZQ on 2016-06-13.
 */
(function($) {
    $(function() {
        //获取参数
        var icode = JUDGE.getURLParameter("typeid");
        //alert(icode);
        //icode
        if(!JUDGE.isNull(icode)){
            var url = "admin/TypeFindByID.do?typeid="+icode;
            $.ajax( {
                type : "post",
                url : url,
                contentType : "application/x-www-form-urlencoded",
                error : function(event,request, settings) {
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success : function(data) {
                    var jsondata = eval(data);
                    if(jsondata.retcode == 0) {
                        
                        $("#typeid").attr("value", jsondata.devtype.typeID);
                        $("#typename").attr("value", jsondata.devtype.typeName);
                        $("#superid").attr("value", jsondata.devtype.superID);
                        $("#typeid").attr("readonly", true);
                    } else {
                        $.messager.alert("提示消息", "获取信息失败!", "info");
                    }

                }
            });
        }


        $("#btnCancel").click(function(){
            window.parent.$('#wedit').window('close');
        });

        $("#btnSave").click(function(){
            //表单验证
            if($("#typeid").val().length == 0 || $("#typename").val().length == 0 || $("#superid").val().length == 0) {
                $.messager.alert("提示消息", "信息输入不全!", "info");
                return;
            }
            var typeid = $('#typeid').val();
            var typename = encodeURI($('#typename').val());
            var superid = $('#superid').val();

            $.ajax ({
                type:"post",
                url:"admin/TypeSaveUpdate.do",
                data:{
                    typeid:typeid,
                    typename:typename,
                    superid:superid
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
function selectSuper() {
    $("#iframetype").attr("src", "admin/typelist.do");
    $("#wtype").window('open');
}