(function($) {
	$(function() {

				$('#tt1').tree({   
				    url:"admin/TypeFindTree.do?state=0&superid=-1",
					onBeforeExpand:function(node,param){
						$('#tt1').tree('options').url="admin/TypeFindTree.do?state=1&superid="+node.id;
					},
					onClick: function(node) {
							window.parent.$("#superid").attr("value",node.id);
							window.parent.$('#wtype').window('close');
				    }
				});
				
			});

})(window.jQuery);

