package com.biz.std.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.std.model.Student;



@Controller
@RequestMapping("/")
public class StudentController {

	@RequestMapping("/studentlist.do")
	public String getUserList(){
		System.out.println("hello");
		Student s = new Student(1,"001","houwenhua","11111","男",new Date());
		return "";
	}
}
