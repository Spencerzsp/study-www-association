package com.shiant.study.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AuthorityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setCharacterEncoding("utf-8");
//		if(request.getSession().getAttribute("user")!=null) {
//			System.out.println("使用拦截器true");
//			return true;
//		}else {
//			response.setContentType("application/json; charset=utf-8");
//			PrintWriter writer = response.getWriter();
//			System.out.println("使用拦截器false");
//			//response.sendRedirect("/managementviews/login.html");
//			System.out.println("request.getRequestURI():"+request.getRequestURI());
//			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("msg","请先登录！");
//			map.put("status", IWebUtils.SERVER_STATUS_FAILED);
//			writer.print(JSONObject.fromObject(map));
//			return false;
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
