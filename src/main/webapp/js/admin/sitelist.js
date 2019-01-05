(function($) {
	$(function() {

				$('#tt1').tree({   
				    url:"admin/SITEFindTree.do?level=2",
					onClick: function(node) {
						if(node.attributes.level == 3)
							alert("非管理站点" +node.id);
						else {
							window.parent.$("#sitesuper").attr("value",node.id);
							window.parent.$('#wsite').window('close');
						}
				    }
				});
				
			});

})(window.jQuery);

