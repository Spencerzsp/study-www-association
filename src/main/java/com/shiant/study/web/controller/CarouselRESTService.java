package com.shiant.study.web.controller;

import com.shiant.common.exception.ServiceException;
import com.shiant.rmi.user.UserRmiService;
import com.shiant.rmi.user.vo.UserRmiVo;
import com.shiant.study.core.system.service.CarouselService;
import com.shiant.study.core.system.vo.CarouselVo;
import com.shiant.study.web.util.IWebUtils;
import com.shiant.study.web.util.SSOUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.shiant.study.web.util.IWebUtils.JSON;

@Api(value = "轮播控制器", description = "轮播控制器", tags = { "shiant-portal" })
@Controller
@RequestMapping("/carousel")
public class CarouselRESTService {
	
	@Autowired 
	private CarouselService carouselService;

	@Autowired 
	private UserRmiService userRmiService;
	
	@ResponseBody
	@RequestMapping(value="/addBean",method = RequestMethod.POST)
	public View addBean(CarouselVo bean,HttpServletRequest req,
			HttpServletResponse resp, Model model) {
		try {
			UserRmiVo user = SSOUtils.getUserData(req, resp, userRmiService);
			carouselService.addBean(bean, user);
			model.addAttribute("msg", "轮播添加成功！");
			model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
		}catch(ServiceException e){
			model.addAttribute("msg",e.getErrorMsg());
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
		}catch(Exception e){
			model.addAttribute("msg","系统错误！");
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
			e.printStackTrace();
		}
		return JSON;
	}
	
	@ApiOperation(value = "修改轮播信息",httpMethod = "PUT",notes = "修改轮播信息",produces="application/json",tags = {"shiant-portal"})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "body",name = "bean",value = "轮播对象",dataType = "CarouselVo",required = true)
	})
	@ResponseBody
	@RequestMapping(value="/updateBean",method=RequestMethod.POST)
	public View updateBean(CarouselVo bean,HttpServletRequest req, 
			HttpServletResponse resp, Model model) {
		try {
			UserRmiVo user = SSOUtils.getUserData(req, resp, userRmiService);
			carouselService.updateBean(bean, user);
			model.addAttribute("msg", "轮播更新成功！");
			model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
		}catch(ServiceException e){
			model.addAttribute("msg",e.getErrorMsg());
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
		}catch(Exception e){
			model.addAttribute("msg","系统错误！");
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
			e.printStackTrace();
		}
		
		return JSON;
	}
	
	@ApiOperation(value = "获取轮播列表",httpMethod = "GET",notes = "获取轮播列表",produces="application/json",tags = {"shiant-portal"})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query",name = "page",value = "页码",dataType = "int",required = false),
		@ApiImplicitParam(paramType = "query",name = "limit",value = "页长",dataType = "int",required = false),
		@ApiImplicitParam(paramType = "query",name = "domain",value = "域名",dataType = "String",required = false)
	})
	@ResponseBody
	@RequestMapping(value="/getBeans",method = RequestMethod.GET)
	public View getBeans(
			@RequestParam(required=false, defaultValue = "0")int page, 
			@RequestParam(required=false, defaultValue = "0")int limit, 
			String domain,HttpServletRequest req,HttpServletResponse resp,Model model) {
		try {
			UserRmiVo user = SSOUtils.getUserData(req, resp, userRmiService);
			Map<String, Object> beans = carouselService.getBeans(page, limit, domain, user);
			model.addAttribute("data", beans.get("beans"));
			model.addAttribute("count", beans.get("count").toString());
			model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
			model.addAttribute("msg", "查询成功！");
			model.addAttribute("code", 0);
		} catch (Exception e) {
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
			model.addAttribute("msg", "查询失败！");
			model.addAttribute("code", -1);
			e.printStackTrace();
		}
		return JSON;
	}
	
	@ApiOperation(value = "获取轮播详细",httpMethod = "GET",notes = "获取轮播详细",produces="application/json",tags = {"shiant-portal"})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query",name = "id",value = "编号",dataType = "Long",required = false),
		@ApiImplicitParam(paramType = "query",name = "condition",value = "查询条件",dataType = "String",required = false)
	})
	@ResponseBody
	@RequestMapping(value="/getBean",method = RequestMethod.GET)
	public View getBean(
			@RequestParam(required=true)Long id,String condition,
			HttpServletRequest req, HttpServletResponse resp,Model model) {
		try {
			UserRmiVo user = SSOUtils.getUserData(req, resp, userRmiService);
			CarouselVo bean = carouselService.getBean(id, user);
			model.addAttribute("bean",  bean);
			model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
			model.addAttribute("msg", "查询成功！");
		} catch (Exception e) {
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
			model.addAttribute("msg", "查询失败！");
			e.printStackTrace();
		}
		return JSON;
	}
	
	@ApiOperation(value = "删除轮播信息",httpMethod = "GET",notes = "删除轮播信息",produces="application/json",tags = {"shiant-portal"})
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query",name = "id",value = "轮播编号",dataType = "String",required = true,allowMultiple=true)
	})
	@ResponseBody
	@RequestMapping(value="/deleteBean",method=RequestMethod.GET)
	public View deleteBean(@RequestParam String ids,HttpServletRequest req, HttpServletResponse resp,
			Model model) {
		try {
			UserRmiVo user = SSOUtils.getUserData(req, resp, userRmiService);
			if (ids!=null &&!ids.equals("")) {
				String[] ids_ = ids.split(",");
				for (String id : ids_) {
					if(id!=null &&!id.equals("")){
						carouselService.deleteBean(Long.parseLong(id),user);
					}
				}
			}
			model.addAttribute("msg", "轮播删除成功！");
			model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
			
		}catch(ServiceException e){
			model.addAttribute("mes",e.getErrorMsg());
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
		}catch(Exception e){
			model.addAttribute("msg","系统错误！");
			model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
			e.printStackTrace();
		}
		
		return JSON;
	}
}
