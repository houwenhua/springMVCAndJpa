package com.biz.std.service;

import java.util.ArrayList;
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
import com.biz.std.model.StudentImage;
import com.biz.std.repository.StudentRepository;
import com.biz.std.repository.UploadRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private UploadRepository uploadRepository;
	
	@Transactional
	public Pagination findAllStudents(int page,int rows){
		Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
		Sort sort = new Sort(order);
		
		Pageable pageable = new PageRequest(page-1,rows,sort);
		Page<Student> list = studentRepository.findAll(pageable);
		
		//获得所有student列表
		List<Student> stuList = list.getContent();
		//创建一个list列表，用于存放重新组装的Student
		List<Student> newStuList = new ArrayList<Student>();
		//循环遍历
		for(Student stu : stuList){
			//根据student的id获得StudentImage对象
			StudentImage stuImg = uploadRepository.findByStudentId(stu.getId());
			if(stuImg != null){
				stu.setPicture(stuImg.getOsspath());
			}
			newStuList.add(stu);
		}
		Pagination pagination = new Pagination(list.getTotalElements(),list.getNumber()+1,newStuList);
		return pagination;
	}

	@Transactional
	public String saveStudent(Student student) {
		Student stu = studentRepository.findByStudentNumber(student.getNumber());
		//如果学号number已经存在就返回2
		if(stu == null){
			studentRepository.save(student);
			return "1";
		} else {
			return "2";
		}
		
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

	/**
	 * 删除学生对象和所对应的图片
	 * 
	 * */
	@Transactional
	public void deleteStudent(String[] ids) {
		for(String id : ids){
			StudentImage stuImg = uploadRepository.findByStudentId(Integer.parseInt(id));
			uploadRepository.delete(stuImg);
			studentRepository.delete(Integer.parseInt(id));
		}
	}
}
