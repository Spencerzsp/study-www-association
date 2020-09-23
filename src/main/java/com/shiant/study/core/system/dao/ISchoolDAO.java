package com.shiant.study.core.system.dao;

import com.shiant.common.dao.AbstractModelDAO;
import com.shiant.study.core.system.model.Carousel;
import com.shiant.study.core.system.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 14:44
 **/
public interface ISchoolDAO  extends AbstractModelDAO<School> {
    // Page<School> findByOrgNameStartingWith(String orgName, Pageable pageable);
}
