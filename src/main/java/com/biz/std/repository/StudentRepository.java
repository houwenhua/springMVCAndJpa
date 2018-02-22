package com.biz.std.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import com.biz.std.model.Student;

public interface StudentRepository extends Repository<Student,Integer> {

	@Query("select s from Student s")
	public List<Student> findAllStudent();
	
	public Student findByName(String name);
}
