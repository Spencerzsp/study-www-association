package com.shiant.study.core.system.service;

import com.alibaba.fastjson.JSONArray;
import com.shiant.common.exception.ServiceException;
import com.shiant.study.core.system.model.School;

import java.util.Date;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 14:52
 **/
public interface ISchoolService {

    /**
     * 获取所有院校信息
     *
     * @return
     */
    JSONArray getAll(School school);

    /**
     * 添加院校信息
     */
    void addData(String namec, String nameE, String schoolLog,
                 String schooLling, String schoolProperty, String professionNum,
                 String schoolAbout, Date createDate, Date updateDate) throws ServiceException;

    void addData1(School school) throws ServiceException;

    /**
     * 删除信息
     *
     * @param
     */
    void deleteData(String schoolName) throws ServiceException;


    /**
     * 动态更新（问题）
     *
     * @param school
     * @throws ServiceException
     */
    void updateData(School school) throws ServiceException;


    /**
     * 动态查询
     *
     * @return
     */
    JSONArray queryXxxs(School school) throws ServiceException;

}
