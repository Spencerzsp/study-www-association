$(function() {
	var shiant = window.shiant = window.shiant || {};

	myCourse = function() {
		layer.msg('敬请期待');
	}
	help = function() {
		layer.msg('敬请期待');
	}
	setLanguage = function() {
		layer.open({
			type : 1,
			closeBtn : 0,
			area: '300px',
			shadeClose : true,
			title :shiant.l('语言选择'),
			skin : 'language-layer-skin',
			content : `	<div id="language-div" class="layui-form" >
						    <div class="layui-input-inline" style="padding-left: 20px;">
						      <input type="radio" name="language" lay-filter="languageRadio" value="`+shiant.l("中文")+`" title="`+shiant.l("中文")+`">
						      <input type="radio" name="language" lay-filter="languageRadio" value="`+shiant.l("英文")+`" title="`+shiant.l("英文")+`">
						    </div>
				    	</div>`
		});
		layui.form.render();
	}

	init = function() {
		layui.use(['layer','form'], function() {
			layer = layui.layer;
			form = layui.form;
			
			form.on('radio(languageRadio)', function(data){
			    if(data.value == shiant.l("中文")){
			    	 window.localStorage.setItem("shiant-center-language","zh-CN");
			    	 window.location.reload();
			    } else {
			    	window.localStorage.setItem("shiant-center-language","en-US");
			    	window.location.reload();
			    }
			});
		});
		$.myAjax({
			type : 'get',
			url : 'home/getUserData',
			success : function(res) {
				if (res.status) {
					$("#orgName").html(res.orgName);
				}
			}
		});
		shiant.translate();
	}
	init();
})
