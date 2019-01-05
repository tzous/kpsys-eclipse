/**
 * Created by ZQ on 2016-06-13.
 */
(function($) {
    $(function() {
        //获取参数
        var icode = JUDGE.getURLParameter("siteid");
        //alert(icode);
        //icode
        if(!JUDGE.isNull(icode)){
            var url = "admin/SITEFindByID.do?siteid="+icode;
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
                        
                        $("#siteid").attr("value", jsondata.devsite.siteID);
                        $("#sitename").attr("value", jsondata.devsite.siteName);
                        $("#sitesuper").attr("value", jsondata.devsite.siteSuper);
                        $("#sitelevel").val(jsondata.devsite.siteLevel);
                        $("#sitestat").val(jsondata.devsite.siteStat);
                        $("#siteid").attr("readonly", true);
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
            if($("#siteid").val().length == 0 || $("#sitename").val().length == 0 || $("#sitesuper").val().length == 0) {
                $.messager.alert("提示消息", "信息输入不全!", "info");
                return;
            }
            var siteid = $('#siteid').val();
            var sitename = encodeURI($('#sitename').val());
            var sitesuper = $('#sitesuper').val();
            var sitelevel = $('#sitelevel').val();
            var sitestat = $('#sitestat').val();

            $.ajax ({
                type:"post",
                url:"admin/SiteSaveUpdate.do",
                data:{
                    siteid:siteid,
                    sitename:sitename,
                    sitesuper:sitesuper,
                    sitelevel:sitelevel,
                    sitestat:sitestat
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
function selectArea() {
    $("#iframesite").attr("src", "admin/sitelist.do");
    $("#wsite").window('open');
}