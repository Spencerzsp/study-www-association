package com.shiant.study.web.util;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biot.fsnip.sso.client.util.SSOClientUtil;
import com.biot.fsnip.sso.client.vo.AuthenticateInfo;
import com.shiant.common.exception.ServiceException;
import com.shiant.rmi.user.UserRmiService;
import com.shiant.rmi.user.vo.UserRmiVo;

@Component
public class SSOUtils {
	
	@Autowired
	UserRmiService userRmiService;
	
    public static SSOUtils ssoUtils;
    
    @PostConstruct
    public void init() {    
    	ssoUtils = this;
    } 
    
	public static UserRmiVo getUserData(HttpServletRequest req, 
			  HttpServletResponse resp){
		AuthenticateInfo info = SSOClientUtil.validUser(req, resp);
		UserRmiVo user = new UserRmiVo();
		user.setOrgid(1L);
		user.setOrgName("山海云汇");
		user.setUserid(info.getUserId());
		user.setUserName(info.getUserName());
		user.setUserRealName(info.getRealUserName());
		return user;
	}

	public static UserRmiVo getUserData(HttpServletRequest req, HttpServletResponse resp,
			UserRmiService service) throws ServiceException {
		AuthenticateInfo info = SSOClientUtil.validUser(req, resp);
//		UserRmiVo bean = service.getBean(info.getUserId(), null);
//		bean.setUserName(info.getUserName());
//		bean.setUserRealName(info.getRealUserName());
		UserRmiVo user = new UserRmiVo();
		user.setOrgid(1L);
		user.setOrgName("山海云汇");
		user.setUserid(info.getUserId());
		user.setUserName(info.getUserName());
		user.setUserRealName(info.getRealUserName());
		return user;
	}
}
