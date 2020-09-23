package com.shiant.study.core.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.shiant.study.core.system.model.Carousel;

@Repository(value = "carouselRepository")
public interface CarouselRepository extends JpaRepository<Carousel, Long>,JpaSpecificationExecutor<Carousel>{

	Page<Carousel> findByOrgNameStartingWith(String orgName, Pageable pageable);
	
}
