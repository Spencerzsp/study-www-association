package com.shiant.study.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.shiant.common.exception.ServiceException;
import com.shiant.study.core.system.model.Article;
import com.shiant.study.core.system.service.IArticleService;
import com.shiant.study.web.util.IWebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-22 14:09
 **/
@RestController
@Api(tags = "政策法规")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "Find All")
    @GetMapping
    public JSONArray fndAll(Model model) {
        JSONArray array = new JSONArray();
        try {
            array = articleService.getAll();
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
    public void putData(@RequestBody Article article, Model model) throws ServiceException {
        try {
            articleService.addData(article);
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
    public void deleteDate(@RequestParam("title") String title, Model model) throws ServiceException {
        try {
            articleService.deleteData(title);
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

    @ResponseBody
    @RequestMapping(value = "/update/", method = RequestMethod.PUT)
    public void updateData(@RequestBody Article article, Model model) throws ServiceException {
        try {
            articleService.updateData(article);
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
    public JSONArray getBeans(Model model, Article article) {
        JSONArray array = null;
        try {
            array = articleService.queryXxxs(article);
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
}
