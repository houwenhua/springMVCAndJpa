package com.biz.std.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.std.model.Pagination;
import com.biz.std.model.Student;
import com.biz.std.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Transactional
	public Pagination findAllStudents(int page,int rows){
		Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
		Sort sort = new Sort(order);
		
		Pageable pageable = new PageRequest(page-1,rows,sort);
		Page<Student> list = studentRepository.findAll(pageable);
		Pagination pagination = new Pagination(list.getTotalElements(),list.getNumber()+1,list.getContent());
		return pagination;
	}

	@Transactional
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	@Transactional
	public void updateStudent(Student s) {
		Student tempStu = studentRepository.findOne(s.getId());
		tempStu.setNumber(s.getNumber());
		tempStu.setName(s.getName());
		tempStu.setPicture(s.getPicture());
		tempStu.setSex(s.getSex());
		tempStu.setBirthday(s.getBirthday());
		studentRepository.saveAndFlush(tempStu);
	}

	public void deleteStudent(String[] ids) {
		for(String id : ids){
			studentRepository.delete(Integer.parseInt(id));
		}
	}
}
