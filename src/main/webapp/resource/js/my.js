$(function () {
	var shiant = window.shiant = window.shiant || {};
	var type = shiant.getUrlParam("name");
	var alias = decodeURIComponent(decodeURIComponent(shiant.getUrlParam("alias")));
	var layer;
	
	layui.use(['layer'], function(){
		 layer = layui.layer;
	});
	
	myCourse = function() {
		layer.msg('敬请期待');
	}
	help = function() {
		layer.msg('敬请期待');
	}
	
	init = function() {
		$.myAjax({
			type:'get',
	    	url:'home/getUserData',
	    	success:function (res) {
	          	if(res.status){
	            	$("#orgName").html(res.orgName);
	    			$("#userName").html(res.userName+"  "+shiant.l("您好"));
	        	}
	    	}
		});
		shiant.translate();
	}
	init();
})
