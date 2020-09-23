package com.shiant.study.core.system.dao.impl;

import com.shiant.common.dao.impl.AbstractModelDAOImpl;
import com.shiant.study.core.system.dao.ISchoolDAO;
import com.shiant.study.core.system.model.School;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 14:50
 **/
@Repository(value = "schoolDao")
public class SchoolDAOImpl extends AbstractModelDAOImpl<School> implements ISchoolDAO {
    @PersistenceContext
    private EntityManager entityManager;
}
