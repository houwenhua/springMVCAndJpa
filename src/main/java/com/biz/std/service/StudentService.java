package com.biz.std.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.std.model.Student;
import com.biz.std.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional
	public List<Student> findAllStudents(){
		studentRepository.findAllStudent();
		return null;
	}
}
