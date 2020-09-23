package com.shiant.study.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.shiant.common.exception.ServiceException;
import com.shiant.rmi.user.UserRmiService;
import com.shiant.study.core.system.model.School;
import com.shiant.study.core.system.service.ISchoolService;
import com.shiant.study.web.util.IWebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 15:15
 **/
@RestController
@Api(tags = "国际教育展")
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private ISchoolService schoolService;

    @ApiOperation(value = "FindByNameCAndNameE")
    @RequestMapping(value = "/findByNameCAndNameE/", method = RequestMethod.PUT)
    public JSONArray findByNameCAndNameE(@RequestBody School school, Model model) {
        JSONArray array = new JSONArray();
        try {
            array = schoolService.getAll(school);
            model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
            model.addAttribute("msg", "查询成功！");
            model.addAttribute("code", 0);
        } catch (Exception e) {
            model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
            model.addAttribute("msg", "查询失败！");
            model.addAttribute("code", -1);
            e.printStackTrace();
        }
        return array;
    }


    @ApiOperation(value = "Add Data")
    @RequestMapping(value = "/putData/", method = RequestMethod.PUT)
    public void putData(@RequestBody School school, Model model) throws ServiceException {
        try {
            schoolService.addData1(school);
            model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
            model.addAttribute("msg", "添加成功！");
            model.addAttribute("code", 0);
        } catch (Exception e) {
            model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
            model.addAttribute("msg", "添加失败！");
            model.addAttribute("code", -1);
            e.printStackTrace();
        }

    }


    @ResponseBody
    @DeleteMapping("deleteMethod/")
    public void deleteDate(@RequestParam("name") String name, Model model) throws ServiceException {
        try {
            schoolService.deleteData(name);
            model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
            model.addAttribute("msg", "删除成功！");
            model.addAttribute("code", 0);
        } catch (Exception e) {
            model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
            model.addAttribute("msg", "删除失败！");
            model.addAttribute("code", -1);
            e.printStackTrace();
        }
    }

    // @ResponseBody
    // @RequestMapping(value = "updateMethod/{id},{nameC},{nameE},{about}", method = RequestMethod.GET)
    // public void updateData(@ApiParam(value = "必选参数", required = true)
    //                        @PathVariable("id") Long id,
    //                        @PathVariable("nameC") String nameC,
    //                        @PathVariable("nameE") String nameE,
    //                        @ApiParam(value = "可选参数", required = false)
    //                        @PathVariable("about") String about
    // ) throws ServiceException {
    //     schoolService.updateData(id, nameC, nameE, about);
    // }
    @ResponseBody
    @RequestMapping(value = "/update/", method = RequestMethod.PUT)
    public void updateData(@RequestBody School sc, Model model) throws ServiceException {
        try {
            schoolService.updateData(sc);
            model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
            model.addAttribute("msg", "更新成功！");
            model.addAttribute("code", 0);
        } catch (Exception e) {
            model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
            model.addAttribute("msg", "更新失败！");
            model.addAttribute("code", -1);
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/DynamicQuery", method = RequestMethod.GET)
    public JSONArray getBeans(Model model, School school) {
        JSONArray array = null;
        try {
            array = schoolService.queryXxxs(school);
            model.addAttribute("status", IWebUtils.SERVER_STATUS_SUCCESS);
            model.addAttribute("msg", "查询成功！");
            model.addAttribute("code", 0);
        } catch (Exception e) {
            model.addAttribute("status", IWebUtils.SERVER_STATUS_FAILED);
            model.addAttribute("msg", "查询失败！");
            model.addAttribute("code", -1);
            e.printStackTrace();
        }
        return array;
    }

     @ResponseBody
     @RequestMapping(value = "/addDate", method = RequestMethod.GET)
     public void addData(
             @RequestParam("nameC") String nameC,
             @RequestParam("nameE") String nameE,
             @RequestParam("schoolLog") String schoolLog,
             @RequestParam("schoolLing") String schoolLing,
             @RequestParam("schoolProperty") String schoolProperty,
             @RequestParam("professionNum") String professionNum,
             @RequestParam("schoolAbout") String schoolAbout,
             @RequestParam("createDate") String createDate,
             @RequestParam("updateDate") String updateDate
     ) throws Exception {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         schoolService.addData(
                 nameC,
                 nameE,
                 schoolLog,
                 schoolLing,
                 schoolProperty,
                 professionNum,
                 schoolAbout,
                 sdf.parse(createDate),
                 sdf.parse(updateDate)
                 );
         System.out.println("---------");
     }
}
