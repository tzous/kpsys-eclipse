
(function($) {
    $(function() {

        $(function() {
            $('#dataview').treegrid({
                nowrap : false,
                rownumbers : true,
                animate : true,
                collapsible : true,
                fitColumns : true,
                url : 'admin/USERFindGrid.do',
                pagination:true,
                idField : 'uname',
                columns : [ [ {
                    field : 'uname',
                    title : '登陆账号',
                    width : 50
                }, {
                    field : 'remarks',
                    title : '姓名',
                    width : 50
                }, {
                    field : 'access',
                    title : '角色',
                    width : 50
                },{
                    field : '操作',
                    title : '操作',
                    width : 50,
                    // 添加超级链，并将来文号作为参数传入
                    formatter : function(value, row, index) {
                        if(row.ICONCLS && row.ICONCLS.length>0){

                        }else{
                            var s = '<span  onclick="dataEdit(\''+row.uname+'\');" style="color:blue;">编辑</span> '
                                +'| <span  onclick="dataDelete(\''+row.uname+'\');" style="color:blue;">删除</span>';
                            return s ;
                        }
                    }} ] ],
                toolbar : [ {
                    id : 'btnadd',
                    text : '新增',
                    iconCls : 'icon-add',
                    handler : function() {
                        $("#iframemain").attr("src", "admin/useredit.do");
                        $("#wedit").window('open');
                    }
                }, {
                    id : 'btncut',
                    text : '刷新',
                    iconCls : 'icon-reload',
                    handler : function() {
                        //$('#btnsave').linkbutton('enable');
                        //alert('cut')
                        //getSelected();
                        $('#dataview').treegrid('reload');
                    }
                } ]
            });
            //设置分页控件
            var p = $('#dataview').datagrid('getPager');
            $(p).pagination({
                pageSize: 10,//每页显示的记录条数，默认为10
                pageList: [10,20],//可以设置每页记录条数的列表
                beforePageText: '第',//页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                /*onBeforeRefresh:function(){
                 $(this).pagination('loading');
                 alert('before refresh');
                 $(this).pagination('loaded');
                 }*/
            });
        });

    });
})(window.jQuery);

function dataEdit(icode){
    $("#iframemain").attr("src", "admin/useredit.do?uname="+icode);
    $("#wedit").window('open');
}

function dataDelete(icode){
    $.messager.confirm('询问','确定要删除'+ icode + '吗?',function(r){
        if (r){
            $.ajax( {
                type : "post",
                url : "admin/UserDelete.do?uname="+icode,
                contentType : "application/x-www-form-urlencoded",
                error : function(event,request, settings) {
                    //请求失败时调用函数。
                    $.messager.alert("提示消息", "请求失败!", "info");
                },
                success : function(data) {
                    //请求成功后回调函数。
                    var jasondata = eval(data);
                    if(jasondata.retcode==0) {
                        $.messager.alert('操作提示', '数据删除成功!');
                        //clearSelect('datagrid');
                        //flashTable("datagrid");
                        $('#dataview').treegrid('reload');
                    } else {
                        $.messager.alert('操作提示', '数据删除失败!');
                    }
                }
            });
        }
    });
}

