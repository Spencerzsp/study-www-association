package com.shiant.study.core.system.dao;

import com.shiant.study.core.system.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-22 13:56
 **/
@Repository(value = "articleRepository")
public interface IArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Article findAllByTitleAndAndId(String title,Long id);
}
