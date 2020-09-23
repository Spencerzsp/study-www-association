$(function () {
	var shiant = window.shiant = window.shiant || {};
	var id = shiant.getUrlParam("id");
	var cid = shiant.getUrlParam("cid");
	var ins;
	$("#footerpage").load("footer.html");
	
	getStepData = function() {
		$('select[name="step"]').parent().parent().removeClass('layui-hide');
		$('#next-btn').parent().removeClass('layui-hide');
		$($('select[name="step"]').parent().parent().next()).hide();
		$.myAjax({
			type:'get',
			url:'courseStep/getBeans?cid='+cid,
			success:function (res) {
				if(res.status){
					var html = '';
					for(var i=0;i<res.beans.length;i++){
						html = html +  '<option value="'+res.beans[i].csid+'">'+
							shiant.l("第")+res.beans[i].order+shiant.l("步")+
							"  -  "+ res.beans[i].title+'</option>';
					}
					$('select[name="step"]').append(html);
					$("#course-title").html(res.course.title);
				}
				layui.form.render("select");
				if(res.beans.length != 0){
					id = res.beans[0].csid;
					getData();
					layui.form.on('select(step)', function(data){
						id = data.value;
						getData();
					});
				}
			}
		});
	}

	gotoAnswer = function() {
		window.location.href='./question.html?cid='+cid+'&csid='+id;
	}
	
	gotoNext = function() {
		var dddom = $('select[name="step"]').next().find('.layui-this');
		if(dddom.next().length > 0){
			var value = dddom.next().attr("lay-value");
			$('select[name="step"]').val(value);
			layui.form.render("select");
			id = value;
			getData();
			if(dddom.next().next() == 0){
				$("#next-btn").hide();
    			$("#complete-btn").show();
			}
		}else{
			layui.layer.msg(shiant.l("已经是最后一步"));
		}
	}

	gotoComplete = function() {
		window.location.href='./course-list.html';
	}
	
	collection = function() {
		layui.layer.msg(shiant.l("敬请期待"));
	}
	
	addShowTime = function() {
		$.myAjax({
			type:'get',
            url:'course/addShowTime?cid='+cid,
            success:function (res) {
    		}
    	});
	}
	
	getData = function() {
		if(id){
			$.myAjax({
				type:'get',
	            url:'courseStep/getBean?id='+id,
	            success:function (res) {
	            	if(res.status){
	            		var dom = $('select[name="step"]').next()
	            						.find('.layui-this').next();
	            		if(dom.length == 0){
	            			$("#next-btn").hide();
	            			$("#complete-btn").show();
	            		}else{
	            			$("#next-btn").show();
	            			$("#complete-btn").hide();
	            		}
	            		$('#step-title').html(res.bean.title);
	            		$('#step-order').html(res.bean.order);
	            		$('#step-content').html(res.bean.content);
	            		var html = '';
	            		if(res.bean.videoFile){
	            			html = `<div style="text-align:center;">
		        						<a onclick="showVideo(this)"><video src="`+res.bean.videoFile+`"
		        						poster="../resource/img/video.png" preload="none">	
		        						<source src="movie.mp4" type="video/mp4">
										<source src="movie.ogg" type="video/ogg">
										<source src="movie.webm" type="video/webm">
		        						</video></a>
		        					</div>`;
	            		}
	            		for(var i=1;i<11;i++){
	            			if(res.bean["imageFile"+i]){
	            				html = html+'<div style="text-align:center;"><a onclick="showImgage(this)"><img src="'+res.bean["imageFile"+i]+'"/></img></a></div>';
	            			}
	            		}
	            		$("#carousel").empty();
	            		$("#carousel").append('<div carousel-item="">'+html+'</div>');
	            		ins = layui.carousel.render({
	        			    elem: '#carousel'
	        			    ,width: '100%'
	        			    ,height: '200px'
	        			    ,interval: 500000
	        			    ,anim: 'fade'
	        			   	,arrow:'none'
	        			   	,autoplay:false
	        			});
//	            		form.val('example', {
//	      		          "username": "贤心" // "name": "value"
//	      		          ,"password": "123456"
//	      		          ,"interest": 1
//	      		          ,"like[write]": true //复选框选中状态
//	      		          ,"close": true //开关状态
//	      		          ,"sex": "女"
//	      		          ,"desc": "我爱 layui"
//	      		        });
	            	}
	    		}
	    	});
		}
	}
	
	showVideo = function(dom) {
		layer.open({
			type : 1,
			title : false,
			area: '100%',
			closeBtn : 0,
			skin: 'layui-layer-nobg',
			shadeClose : true,
			content :`<video width="100%" src="`+$(dom).children().attr("src")+`" controls>	
		            	<source src="movie.mp4" type="video/mp4">
						<source src="movie.ogg" type="video/ogg">
						<source src="movie.webm" type="video/webm">
	            	  </video>`
		});
	}
	showImgage = function(dom) {
		layer.open({
			type : 1,
			title : false,
			area: '100%',
			closeBtn : 0,
			skin: 'layui-layer-nobg',
			shadeClose : true,
			content :`<img width="100%" src="`+$(dom).children().attr("src")+`"/>	
	            	  </img>`
		});
	}
	
	init = function() {
		layui.use(['carousel','form','layer'], function(){
			var carousel = layui.carousel;
			var form = layui.form;
			var layer = layui.form;
			if(cid){
				getStepData();
			}else{
				getData();  
			}
			ins = carousel.render({
			    elem: '#carousel'
			    ,width: '100%'
			    ,height: '200px'
			    ,interval: 5000
			    ,anim: 'fade'
			   	,arrow:'none'
				,autoplay:false
			});
			
			$("#carousel").on("touchstart", function (e) {
	            var startX = e.originalEvent.targetTouches[0].pageX;//开始坐标X
	            $(this).on('touchmove', function (e) {
	                arguments[0].preventDefault();//阻止手机浏览器默认事件
	            });
	            $(this).on('touchend', function (e) {
	                var endX = e.originalEvent.changedTouches[0].pageX;//结束坐标X
	                e.stopPropagation();//停止DOM事件逐层往上传播
	                if (endX - startX > 30) {
	                    ins.slide("sub"); 
	                }
	                else if (startX - endX > 30) {
	                    ins.slide("add"); 
	                }
	                $(this).off('touchmove touchend');
	            });
	        });
		});
		shiant.translate();
		addShowTime();
	}
	init();
})
