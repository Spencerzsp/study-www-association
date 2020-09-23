package com.shiant.study.core.system.service;

import com.alibaba.fastjson.JSONArray;
import com.shiant.common.exception.ServiceException;
import com.shiant.study.core.system.model.Article;
import com.shiant.study.core.system.model.School;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-22 14:04
 **/
public interface IArticleService {

    JSONArray getAll();

    void addData(Article article);

    void deleteData(String  titleName) throws ServiceException;

    void updateData(Article article) throws ServiceException;

    JSONArray queryXxxs(Article article) throws ServiceException;

}
