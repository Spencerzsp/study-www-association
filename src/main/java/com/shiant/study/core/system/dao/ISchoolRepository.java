package com.shiant.study.core.system.dao;

import com.shiant.study.core.system.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-17 14:45
 **/
@Repository(value = "schoolRepository")
public interface ISchoolRepository extends JpaRepository<School, Long>, JpaSpecificationExecutor<School> {

    Page<School> findAll(@Nullable Specification<School> var1, Pageable var2);

    School findBySchoolNameCAndSchoolNameEAndId(String nameC, String nameE, Long id);

    List<School> findAllBySchoolNameCAndSchoolNameE(String nameC, String nameE);

}
