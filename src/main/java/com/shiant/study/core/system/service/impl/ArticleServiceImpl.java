package com.shiant.study.core.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shiant.common.exception.ServiceException;
import com.shiant.study.core.system.dao.*;
import com.shiant.study.core.system.model.Article;
import com.shiant.study.core.system.model.School;
import com.shiant.study.core.system.service.IArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-22 14:06
 **/
@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDao articleDao;

    @Autowired
    private IArticleRepository articleRepository;

    @Autowired
    private IArticleDeleteRespositoy deleteRespositoy;

    @Override
    public JSONArray getAll() {
        JSONArray array = new JSONArray();
        List<Article> all = articleRepository.findAll();
        for (Article article : all) {
            array.add(article);
        }
        return array;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addData(Article article) {
        Article ar = new Article();
        ar.setTextType(article.getTextType());
        ar.setTitle(article.getTitle());
        ar.setContent(article.getContent());
        ar.setCreateDate(article.getCreateDate());
        ar.setUpdateDate(article.getUpdateDate());

        articleRepository.save(ar);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteData(String titleName) throws ServiceException {
        deleteRespositoy.deleteByTitle(titleName);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateData(Article article) throws ServiceException {
        try {
            Article ar = articleRepository.findAllByTitleAndAndId(article.getTitle(), article.getId());
            ar.setTextType(article.getTextType());
            ar.setContent(article.getContent());
            ar.setCreateDate(article.getCreateDate());
            ar.setUpdateDate(article.getUpdateDate());

            articleRepository.save(ar);
        } catch (NullPointerException e) {
            throw new ServiceException("更新失败，无该字段值!", e);
        } catch (Exception e) {
            throw new ServiceException("更新失败!", e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JSONArray queryXxxs(Article article) throws ServiceException {
        Specification<Article> specification = new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(article.getTextType())) {
                    predicates.add(cb.equal(root.get("textType").as(String.class), article.getTextType()));
                }

                if (StringUtils.isNotBlank(article.getTitle())) {
                    predicates.add(cb.equal(root.get("title").as(String.class), article.getTitle()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Sort sort = Sort.by(Sort.Direction.ASC, "createDate");
        Pageable pageable = PageRequest.of(0, 10);
        List<Article> content = articleRepository.findAll(specification, pageable).getContent();
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(content));

        return array;
    }
}
