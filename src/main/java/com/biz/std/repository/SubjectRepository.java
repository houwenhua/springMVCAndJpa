package com.biz.std.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.biz.std.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> ,JpaSpecificationExecutor<Subject>  {

}
