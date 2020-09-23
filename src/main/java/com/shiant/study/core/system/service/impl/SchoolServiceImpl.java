package com.shiant.study.core.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shiant.common.exception.ServiceException;
import com.shiant.study.core.system.dao.ISchoolDAO;
import com.shiant.study.core.system.dao.ISchoolDeleteRespository;
import com.shiant.study.core.system.dao.ISchoolRepository;
import com.shiant.study.core.system.model.School;
import com.shiant.study.core.system.service.ISchoolService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 14:55
 **/
@Service
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private ISchoolDAO schoolDAO;

    @Autowired(required = true)
    private ISchoolRepository schoolRepository;

    @Autowired
    private ISchoolDeleteRespository deleteRespository;

    @Override
    public JSONArray getAll(School school) {
        JSONArray array = new JSONArray();
        List<School> all = schoolRepository.findAllBySchoolNameCAndSchoolNameE(school.getSchoolNameC(), school.getSchoolNameE());
        for (School sc : all) {
            array.add(sc);
        }
        return array;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addData(String nameC, String nameE, String schoolLog,
                        String schoolLing, String schoolProperty, String professionNum,
                        String schoolAbout, Date createDate, Date updateDate) throws ServiceException {
        try {
            School sc = new School();
            sc.setSchoolNameC(nameC);
            sc.setSchoolNameE(nameE);
            sc.setSchoolLog(schoolLog);
            sc.setSchooLing(schoolLing);
            sc.setSchoolProperty(schoolProperty);
            sc.setProfessionNum(professionNum);
            sc.setSchoolAbout(schoolAbout);
            sc.setCreateDate(createDate);
            sc.setUpdateDate(updateDate);

            schoolRepository.save(sc);
        } catch (Exception e) {
            throw new ServiceException("添加数据失败!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addData1(School school) throws ServiceException {
        try {
            School sc = new School();
            sc.setSchoolNameC(school.getSchoolNameC());
            sc.setSchoolNameE(school.getSchoolNameE());
            sc.setSchoolLog(school.getSchoolLog());
            sc.setSchooLing(school.getSchooLing());
            sc.setSchoolProperty(school.getSchoolProperty());
            sc.setStudentsEnrollment(school.getStudentsEnrollment());
            sc.setSchoolAddress(school.getSchoolAddress());
            sc.setProfessionNum(school.getProfessionNum());
            sc.setSchoolAbout(school.getSchoolAbout());
            sc.setCreateDate(school.getCreateDate());
            sc.setUpdateDate(school.getUpdateDate());

            schoolRepository.save(sc);
        } catch (Exception e) {
            throw new ServiceException("更新失败!", e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteData(String name) {
        deleteRespository.deleteSchoolBySchoolNameCStartingWith(name);
        System.out.println(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateData(School school) throws ServiceException {
        try {
            School sc = schoolRepository.findBySchoolNameCAndSchoolNameEAndId(
                    school.getSchoolNameC(),
                    school.getSchoolNameE(),
                    school.getId());

            sc.setSchoolLog(school.getSchoolLog());
            sc.setSchooLing(school.getSchooLing());
            sc.setSchoolProperty(school.getSchoolProperty());
            sc.setStudentsEnrollment(school.getStudentsEnrollment());
            sc.setSchoolAddress(school.getSchoolAddress());
            sc.setProfessionNum(school.getProfessionNum());
            sc.setSchoolAbout(school.getSchoolAbout());
            sc.setCreateDate(school.getCreateDate());
            sc.setUpdateDate(school.getUpdateDate());

            schoolRepository.save(sc);
        } catch (NullPointerException e) {
            throw new ServiceException("更新失败，无该字段值!", e);
        } catch (Exception e) {
            throw new ServiceException("更新失败!", e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONArray queryXxxs(School school) {
        Specification<School> specification = new Specification<School>() {
            @Override
            public Predicate toPredicate(Root<School> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(school.getSchoolNameC())) {
                    predicates.add(cb.equal(root.get("schoolNameC").as(String.class), school.getSchoolNameC()));
                }
                if (StringUtils.isNotBlank(school.getSchoolNameE())) {
                    predicates.add(cb.equal(root.get("schoolNameE").as(String.class), school.getSchoolNameE()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable = PageRequest.of(0, 10);
        List<School> content = schoolRepository.findAll(specification, pageable).getContent();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(content));
        return array;
    }
}
