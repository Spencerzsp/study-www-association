package com.shiant.study.core.system.dao;

import com.shiant.study.core.system.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: study-www-association
 * @description
 * @author: z p、
 * @create: 2020-09-21 13:32
 **/
@Repository(value = "schoolDeleteRepository")
public interface ISchoolDeleteRespository extends CrudRepository<School,Long> {
    void deleteSchoolBySchoolNameCStartingWith(String name);
}
