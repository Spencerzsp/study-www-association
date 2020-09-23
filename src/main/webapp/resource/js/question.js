$(function () {
	var shiant = window.shiant = window.shiant || {};
	var csid = shiant.getUrlParam("csid");
	var cid = shiant.getUrlParam("cid");
	var id = shiant.getUrlParam("id");
	var index = 0;
	var totalArray = new Array();
	var layer_height = document.body.clientHeight/2;
	$("#footerpage").load("footer.html");
	
	submitData = function(data) {
		layui.layer.msg(shiant.l("保存中"), {
    		icon: 16,shade: 0.01,offset:layer_height+"px",time:120000
    	}); 
		var url = 'question/addBean';
		if(id){
			url = 'question/updateBean';
		}
		data.qid = id;
		data.csid = csid;
		data.tip = "";
		$('input[type=checkbox]:checked').each(function() {
		      data.tip = data.tip+$(this).attr("title")+";"
		});
		$.myAjax({
			type:'post',
			url:url,
			data:data,
			dataType:"json",
			success:function (res) {
				if(res.status){
					layui.layer.msg(shiant.l("保存成功"),{offset:layer_height+"px",time:1000});
					window.history.back();
				}else{
					layer.msg(shiant.l("保存失败"),{offset:layer_height+"px",time:1000});
				}
			}
		});
	}
	
	getData = function() {
		if(id){
			$.myAjax({
				type:'get',
	            url:'question/getBean?id='+id,
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
		layui.use(['form','upload','layer'], function(){
			upload = layui.upload;
			form = layui.form;
			layer = layui.layer;
			
			$('input[name="qid"]').val(id);
			
			var uploadVideo = upload.render({
		    	elem: '#video-upload'
		    	,url: shiant.getResHttpPrefix()+'/video?path=/center/business/question'
		    	,accept: 'video' //只允许上传图片
		    	,acceptMime: 'video/*' //只筛选图片
		    	,size: 1024*40 //限定大小 单位 KB
		    	,before: function(obj){
		    		layer.msg(shiant.l("上传中"),{time:2000});
		    		$('#uploadVideoIconView').hide();
		    		//预读本地文件示例，不支持ie8
		    		obj.preview(function(index, file, result){
		    			$('#uploadVideoView').removeClass('layui-hide');
		    			$('#video-upload-v').attr('src', result); //图片链接（base64）
		    		});
		    	}
			    ,done: function(res){
			    	//如果上传失败
			    	if(res.code > 0){
			    		return layer.msg(shiant.l("上传失败"));
			    	}
			    	layer.close(layer.index);
			    	//上传成功
			    	$('input[name="videoFile"]').val(res.urlOfHD.replace("-hd",""));
			    	$('input[name="videoFile"]').parent().find(".delete-upload").show();
			    }
			    ,error: function(){
			    	//演示失败状态，并实现重传
			    	var demoText = $('#video-upload-text');
			    	demoText.html('<span style="color: #FF5722;">'+shiant.l("上传失败")+'</span> <a class="layui-btn layui-btn-mini demo-reload">'+shiant.l("重试")+'</a>');
			    	demoText.find('.demo-reload').on('click', function(){
			    		uploadVideo.upload();
			    	});
			    }
		    });
		    for(var x=1;x<11;x++){
		    	 upload.render({
			    	 elem: '#image-upload-'+x
					,url: shiant.getResHttpPrefix()+'/image?path=/center/business/question'
					,accept: 'images' //只允许上传图片
					,acceptMime: 'image/*' //只筛选图片
					,size: 1024*5 //限定大小 单位 KB
					,before: function(obj){
						layer.msg(shiant.l("上传中"),{time:2000});
						var uid = $(this.elem).data("uid");
						$('#uploadImageIconView-'+uid).hide();
						//预读本地文件示例，不支持ie8
						obj.preview(function(index, file, result){
							$('#uploadImageView-'+uid).removeClass('layui-hide');
					    	$('#imgage-upload-img-'+uid).attr('src', result); //图片链接（base64）
						});
					 }
			    	 ,done: function(res){
			    		var uid = $(this.elem).data("uid");
						if(res.code > 0){
							return layer.msg(shiant.l("上传失败"));
						}
						layer.close(layer.index);
							//上传成功
							$('input[name="imageFile'+uid+'"]').val(res.url);
							$('#image-upload-'+(uid+1)).parent().removeClass('layui-hide');
							$('#image-upload-'+uid).parent().find(".delete-upload").show();
						}
			    	 ,error: function(){
			    		 var uid = $(this.elem).data("uid");
						 var demoText = $('#imgage-upload-text-'+uid);
						 demoText.html('<span style="color: #FF5722;">'+shiant.l("上传失败")+'</span>');
					}
			    });	
		    }
		    //监听提交
		    form.on('submit(question-add)', function(data){
		      submitData(data.field);
		      return false;
		    });
		    shiant.translate();
		    if(!cid&&cid!=''){
				$('input[name="type"]').val("咨询");
				$("#title").html(shiant.l("一键提问"));
			}else{
				$('input[name="type"]').val("实训");
				$("#title").html(shiant.l("实训课程提问"));
			}
		});	
	}
	init();
})
