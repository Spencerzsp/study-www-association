package com.shiant.study.core.system.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.shiant.common.dao.impl.AbstractModelDAOImpl;
import com.shiant.study.core.system.dao.CarouselDAO;
import com.shiant.study.core.system.model.Carousel;

@Repository(value = "carouselDao")
public class CarouselDAOImpl extends AbstractModelDAOImpl<Carousel> implements CarouselDAO {

	@PersistenceContext
	private EntityManager entityManager;

}
