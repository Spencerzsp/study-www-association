package com.shiant.study.core.system.dao;

import com.shiant.study.core.system.model.Article;
import com.shiant.study.core.system.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-22 14:32
 **/
@Repository(value = "articleDeleteRepository")
public interface IArticleDeleteRespositoy extends CrudRepository<Article, Long> {
    void deleteByTitle(String title);
}
