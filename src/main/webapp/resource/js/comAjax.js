"use strict";
function checkLogin() {
	$.myAjax({
		url: "user/valLogin",
		success: function(e) {
			e.success ? (localStorage.setItem("msg", e.msg), $("#header-nav-top .login").addClass("none"), $("#header-nav-top .usercenter,#header-nav-top .exit").removeClass("none"), $("#welcome").html("欢迎您：" + e.data.nickname)) : ($("#header-nav-top .login").removeClass("none"), $("#header-nav-top .usercenter,#header-nav-top .exit").addClass("none"))
		}
	})
}
$(function() { 
	$.myAjax = function(a) {
		a.type = a.type || "get",
		a.data = a.data || {},
		a.success = a.success ||
		function() {},
		a.beforeSend = a.beforeSend ||
		function() {},
		a.failed = a.failed ||
		function() {};
		var e = {
			url: shiant.getHttpPrefix() + a.url,
			type: a.type,
			data: a.data,
			cache: !1,
			beforeSend: function() {
				a.beforeSend()
			},
			success: function(e) {
				a.success(e)
			},
			failed: function() {
				a.failed()
			}
		};
		("post" == a.type||"put" == a.type) 
						&& ("multipart/form-data" == a.contentType 
							? (e.processData = !1, e.contentType = !1) 
							: e.contentType = a.contentType),
		a.dataType && (e.dataType = a.dataType),
		a.xhrFields && (e.xhrFields = a.xhrFields),
		$.ajax(e)
	},
	$.fn.serializeJson = function() {
		var e = {};
		return $(this.serializeArray()).each(function() {
			e[this.name] = this.value
		}),
		e
	},
	$(".webmap").mouseenter(function() {
		$(".map").css("display", "block")
	}),
	$(".map").mouseleave(function() {
		$(".map").css("display", "none")
	})
});
var regChn = /^[\u4e00-\u9fa5]+$/,
zyw = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,
gmreg = /^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/,
specialName = /^[\u4e00-\u9fa5a-zA-Z|\。\，\、\？\《\》\“\‘\—\—\！]*$/,
regName = /^[\u4e00-\u9fa5A-Za-z0-9]+$/,
regUrl = /^((ht|f)tps?):\/\/[\w\-]+(\.[\w\-]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#]])?$/,
regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
regphone = /^(1)\d{10}$/,
regNum = /^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/;
function resetInputFile(e) {
	if (e[0].value) {
		try {
			e[0].value = ""
		} catch(e) {}
		if (e[0].value) {
			var a = document.createElement("form"),
			t = e.nextSibling,
			n = e.parentNode;
			a.appendChild(e),
			a.reset(),
			n.insertBefore(e, t)
		}
	}
}