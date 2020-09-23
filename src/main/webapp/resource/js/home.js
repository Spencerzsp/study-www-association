$(function () {
	var shiant = window.shiant = window.shiant || {};
	var layer;
	var typeData;
	
	initData = function(){
		$.myAjax({
			url:'home/getHome',
			success:function (res) {
				if(res.status){
					$("#orgName").html(res.orgName);
					$("#userName").html(res.userName+"  "+shiant.l("您好"));
					if(res.userName&&res.userName!=''){
						$('.has-login').removeClass('layui-hide');
						$('.need-login').hide();
					}
					$("#orgLogo").attr("src",res.orgLogo);
					var html = '';
            		for(var i=0;i<res.carousels.length;i++){
            			html = html+'<div style="text-align:center;"><a href="views/course-step-preview.html?cid=4"><img height="130px" src="'+res.carousels[i].fileUrl+'?x-oss-process=image/resize,m_fixed,h_165,w_460"/></img></a></div>';
            		}
            		$("#carousel").children().empty();
            		$("#carousel").children().append(html);
            		ins = layui.carousel.render({
        			    elem: '#carousel'
        			    ,width: '100%'
        			    ,height: '130px'
        			    ,interval: 5000
        			    ,indicator:'none'
        			    ,anim: 'fade'
        			   	,arrow:'none'
        			});
				}else{
					layui.layer.msg(shiant.l('获取主页信息失败'),{time:1000});
				}
			}
		});
		$.myAjax({
			url:'course/getBeans?from=1&size=4',
			success:function (res) {
				if(res.status){
					var html = '';
            		for(var i=0;i<res.beans.length;i++){
            			if(i%2==0&&i!=0){
							html = html+'</div>'
						}
						if(i%2==0){
							html = html+'<div class="layui-col-xs12">'
						}
						html = html+'<div class="layui-col-xs6">';
            			html = html+'<a class="item" href="views/course-step-preview.html?cid='+res.beans[i].cid+'">';
            			html = html+'<div class="image">';
            			html = html+'<img src="'+res.beans[i].coverFile+'?x-oss-process=image/resize,m_fixed,h_225,w_400" alt="image">';
            			html = html+'</div>';
            			html = html+'<div class="text">';
            			html = html+'<p class="title">'+res.beans[i].title+'</p>';
            			html = html+'<div class="text-muted">'+res.beans[i].showTime+shiant.l("人观看")+'</div>';
            			html = html+'</div>';
            			html = html+' </a></div>';
            		}
            		if(res.beans.length>0){
						html = html+'</div>'
					}
            		$("#course-list").empty().append(html);
				}else{
					layui.layer.msg(shiant.l('获取实训列表失败'),{time:1000});
				}
			}
		});
	}
	
	init = function() {
		layui.use(['element','layer','carousel'], function(){
			  element = layui.element;
			  layer = layui.layer;
			  carousel = layui.carousel;
			  
			  carousel.render({
				    elem: '#carousel'
				    ,width: '100%'
				    ,height: '120px'
					,height: '200px'
				    ,anim: 'fade'
				    ,indicator:'none'
				   	,arrow:'none'
			  });	
			initData();
		});
		$("#questions").click(function(){
			window.location.href='./views/question.html';
//			layer.open({
//			  type: 2,
//			  title: false,
//			  closeBtn: 0,
//			  shadeClose: true,
//			  skin: 'yourclass',
//			  content: 'addPlatformProperty.html'
//			});
		});
		shiant.translate();
	}
	init();
})
