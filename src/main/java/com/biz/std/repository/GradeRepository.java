package com.biz.std.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.biz.std.model.Grade;

public interface GradeRepository extends JpaRepository<Grade,Integer> ,JpaSpecificationExecutor<Grade> {

}
