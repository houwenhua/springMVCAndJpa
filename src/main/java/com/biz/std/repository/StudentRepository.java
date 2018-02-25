package com.biz.std.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import com.biz.std.model.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> ,JpaSpecificationExecutor<Student>{
	
}
