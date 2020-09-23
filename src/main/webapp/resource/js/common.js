(function($) {
	var shiant = window.shiant = window.shiant || {};
	shiant.hostname = window.location.hostname;
	
	var DEV_URL = "127.0.0.1";
	var QA_URL = "devlims.fsnip.com";
	var PROD_URL = "shiantqs.shanhaiyh.com";
	
	function getContextPath() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0,index+1);
		return result;
	};
	
	shiant.getUrlParam = function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }
	
	shiant.getResHttpPrefix = function(){
		var httpPrefix = "";
		switch(this.hostname){
			case PROD_URL://PROD
			case QA_URL://QA
			case DEV_URL:
				httpPrefix = "http://39.108.121.185:10009/res";
				break;
			default://DEV
				httpPrefix = "http://39.108.121.185:10009/res";
				break;
		}
		return httpPrefix;
	}
	
	shiant.getHttpPrefix = function(){
		var httpPrefix = "";
		switch(this.hostname){
			case PROD_URL://PROD
				httpPrefix = "http://shiantqs.shanhaiyh.com/service/";
				break;
			case QA_URL://QA
			case DEV_URL:
				httpPrefix = getContextPath()+"/service/";
				break;
			default://DEV
				httpPrefix = "../../center-business/service/";
				break;
		}
		return httpPrefix;
	};
	
	shiant.l = function(key){
		if(!shiant.languages){
			shiant.translate();
		}
		return shiant.languages[key];
	}
	
	shiant.translate = function(pageDom){
		var language = window.localStorage.getItem("shiant-center-language");
		if(!language){
			language = "zh-CN";
		}
		var url = "../resource/language/"+language+".json";
		if(window.location.pathname.indexOf("home.html")>0
				||window.location.pathname.indexOf(".html")<0){
			url = "./resource/language/"+language+".json";
		}
		if(!shiant.languages){
			$.ajax({
				async: false,
				type: "GET",
				url: url,
				success: function(data) {
					shiant.languages = data;
				}
			});
		}
		var doms = $(".lang");
		if(pageDom){
			doms = $("#"+pageDom+" .lang");
		}
		doms.each(function(index, element) {
			if($(this).attr("placeholder")){
				var key = $(this).attr("placeholder");
				var value = shiant.languages[key];
				if(value){
					$(this).attr("placeholder",value);
				}
			}else{
				var key = $(this).data("lang");
				var value = shiant.languages[key];
				if(value){
					$(this).append(value);
				}
			}
		});
	}
})(jQuery)

