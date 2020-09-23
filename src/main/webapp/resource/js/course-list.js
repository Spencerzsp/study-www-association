$(function () {
	var shiant = window.shiant = window.shiant || {};
	var id = shiant.getUrlParam("id");
	
	getTypeData = function() {
		$.myAjax({
			type:'get',
            url:'courseType/getBeans?from=1&size=20',
            success:function (res) {
	            if(res.status){
	            	var html = '';
	            	var html1 = '';
            		for(var i=0;i<res.beans.length;i++){
            			if(i == 0){
            				html = html+'<li class="layui-this" data-ctid="'+res.beans[i].ctid+'">'+res.beans[i].alias+'</li>';
            				html1 = html1 + '<div id="course-list-'+res.beans[i].ctid+'" class="layui-tab-item layui-show layui-row"></div>'
            			}else{
            				html = html+'<li data-ctid="'+res.beans[i].ctid+'">'+res.beans[i].alias+'</li>';
            				html1 = html1 + '<div id="course-list-'+res.beans[i].ctid+'" class="layui-tab-item layui-row"></div>'
            			}
            		}
            		$("#type-list").empty().append(html);
            		$("#course-list").empty().append(html1);
            		initTypeDetail(res.beans[0].ctid);
	            }
            }
	   });
	}
	
	initTypeDetail = function(typeid,title) {
		layui.flow.load({
		    elem: '#course-list-'+typeid
		    ,scrollElem: '#course-list-'+typeid
		    ,isAuto: true
		    ,isLazyimg: true
		    ,done: function(page, next){ //加载下一页
		    	getCourseData(typeid,page,next,title);
		    }
		});
	}
	
	getCourseData = function(typeid,page,next,keyWord) {
	   $.myAjax({
			type:'get',
			url:'course/getBeansByType?typeid='+typeid+'&from='+page+'&size=16'+
				(keyWord?"&keyWord="+encodeURIComponent(encodeURIComponent(keyWord)):""),
			success:function (res) {
				if(res.status){
					var html = '';
					for(var i=0;i<res.beans.length;i++){
						var bean = res.beans[i];
						if(i%2==0&&i!=0){
							html = html+'</div>'
						}
						if(i%2==0){
							html = html+'<div class="layui-col-xs12">'
						}
						html = html + 
							`<div class="layui-col-xs6">
						        <a href="./course-step-preview.html?cid=`+bean.cid+`">
							        <div class="image">
										<img src="`+bean.coverFile+`?x-oss-process=image/resize,m_fixed,h_225,w_400" alt="image">
									</div>
									<div class="text">
										<h4 class="title">`+bean.title+`</h4>
										<div class="text-muted">`+bean.showTime+shiant.l('人观看')+`</div>
									</div>
						        </a>
					        </div>`
					}
					if(res.beans.length>0){
						html = html+'</div>'
					}
					next(html, page < res.totalPages); 
				}
			}
		});
	}
	
	init = function() {
		layui.use(['flow','element'], function(){
			element = layui.element;
			flow = layui.flow;
			getTypeData();
			
			element.on('tab(course-tab)', function(data){
				typeid = $(data.elem.context).data("ctid");
				var html = $('#course-list-'+typeid).html();
				if(html == ''){
					initTypeDetail(typeid);
				}
			});
			
			$('#searchInput').bind('keypress',function(event){ 
		         if(event.keyCode == 13){ 
		        	 var typeid = $("#type-list .layui-this").data('ctid')
		        	 $("#course-list-"+typeid).empty();
		        	 initTypeDetail(typeid,$('#searchInput').val());
		         }  
		    });
		});
		shiant.translate();
	}
	init();
})
