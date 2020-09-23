$(function () {
	var shiant = window.shiant = window.shiant || {};
	var id = shiant.getUrlParam("id");
	$("#footerpage").load("footer.html");
	
	submitData = function() {
//		layer.msg(JSON.stringify(data.field));
		var data = document.getElementById('dataForm');
		var formdata = new FormData(data);
		layer.msg('正在保存数据', {
    		icon: 16,shade: 0.01,time:120000
    	}); 
//		if(layedit.getContent(index)!=''){
			formdata.append('content',layedit.getContent(index));
//		}else{
//			formdata.append('content',$("#layedit").val());
//		}
		var url = 'article/addBean';
		if(id){
			url = 'article/updateBean';
			formdata.append('aid',id);
		}
		$.myAjax({
			type:'post',
			url:url,
			data:formdata,
			contentType:'multipart/form-data',
			success:function (res) {
				if(res.status){
					layer.msg('保存成功',{time:1000});
					window.location.href='management.html';
				}else{
					layer.msg('保存失败，请重试',{time:1000});
				}
			}
		});
	}
	
	getData = function() {
		if(id){
			$.myAjax({
				type:'get',
	            url:'article/getBean?id='+id,
	            success:function (res) {
	            	if(res.status){
	            		var doms = $('select[name="type"]').children();
	            		var num = 0;
	            		for(var i=0;i<doms.length;i++){
	            			$(doms[i]).removeAttr("selected");
	            			var v = $(doms[i]).attr("value");
	            			if(v == res.bean.type){
	            				num = i;
	            			}
	            		}
	            		$(doms[num]).attr("selected",'');
	            		
	            		$('input[name="title"]').val(res.bean.title);
	            		$('#layedit').val(res.bean.content);
	            		$('input[name="url"]').val(res.bean.url);
	            		$('input[name="articleFile"]').val(res.bean.fileUrl);
	            		$('input[name="coverFile"]').val(res.bean.coverUrl);
	            		$('input[name="order"]').val(res.bean.order);
	            		
	            		if(res.bean.fileUrl){
	            			$('input[name="articleFile"]').parent().children('.upine').empty().append('<img style="width:50px;height:50px;" src="'+res.bean.fileUrl+'">');
	            		}
	            		if(res.bean.coverUrl){
	            			$('input[name="coverFile"]').parent().children('.upine').empty().append('<img style="width:50px;height:50px;" src="../resource/img/fileico.png">');
	            		}
	            	}
	    		}
	    	});
		}
	}
	
	init = function() {
		layui.use(['form','element'], function(){
			element = layui.element;
		});
		shiant.translate('collection-list');
	}
	init();
})
