package com.biz.std.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import com.biz.std.model.Student;
import com.biz.std.model.StudentImage;

public interface StudentRepository extends JpaRepository<Student,Integer> ,JpaSpecificationExecutor<Student>{
	
	@Query("select s from Student s where s.number = ?1")
	public Student findByStudentNumber(String number);
}
