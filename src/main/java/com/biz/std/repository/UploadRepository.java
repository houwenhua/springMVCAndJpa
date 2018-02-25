package com.biz.std.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biz.std.model.StudentImage;

public interface UploadRepository extends JpaRepository<StudentImage,Integer>{

	@Query("select s from StudentImage s where s.studentid = ?1")
	public StudentImage findByStudentId(Integer studentid);
}
