(function($) {
    $(function() {
        //获取参数
        var icode = JUDGE.getURLParameter("icode");
        //alert(icode);
        //icode		


        $("#btnCancel").click(function(){
            window.parent.$('#tt').tabs('close','密码修改');

        });

        $("#btnSave").click(function(){
            //表单验证
            var supass = $('#oldpass').val();
            var sunewpass = $('#newpass').val();
            var sunewpass1 = $('#newpass1').val();
            if(supass.length == 0 || sunewpass.length == 0) {
                $.messager.alert("提示消息", "信息填写不完整!", "info");
                return;
            }
            if(sunewpass != sunewpass1) {
                $.messager.alert("提示消息", "两次输入的新密码应相同!", "info");
                return;
            }
            $.post(
                'admin/UserModipass.do',
                {
                    upass:supass,
                    unewpass:sunewpass
                },
                function (data) {
                    var jsonresult = eval( data );
                    if(jsonresult.retcode == 9999) {
                        window.parent.frames.location.href="login.do" ;
                        return;
                    }
                    if(jsonresult.retcode != 0) {
                        $.messager.alert("提示消息", jsonresult.retMessage, "info");
                        return;
                    }
                    $.messager.alert("提示消息", jsonresult.retMessage, "info");
                });

        });

    });
})(window.jQuery);
