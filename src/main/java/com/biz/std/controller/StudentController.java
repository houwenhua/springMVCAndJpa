package com.biz.std.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.biz.std.model.Student;
import com.biz.std.service.StudentService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;



@Controller
@RequestMapping("/")
public class StudentController {
	 private ObjectMapper OBJECT_MAPPER = new ObjectMapper()
	            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)  //类级别的设置，JsonInclude.Include.NON_EMPTY标识只有非NULL的值才会被纳入json string之中，其余的都将被忽略
	            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) //禁止使用出现未知属性之时，抛出异常

	.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
	            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);//转化后的json的key命名格式

	@Autowired
	private StudentService studentService;
	/**
	 * 进行分页查询
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/studentlist.do")
	public void getStudentList(HttpServletResponse resp,int page,int rows) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(OBJECT_MAPPER.writeValueAsString(studentService.findAllStudents(page,rows)));
	}
	
	/**
	 * 插入新数据
	 * @throws IOException 
	 * @throws ParseException 
	 * 
	 * */
	@RequestMapping("/studentsave.do")
	public void saveStudent(HttpServletResponse resp,String number,String name,String picture,String sex,String birthday) throws IOException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Student student = new Student(number, name, picture, sex, sdf.parse(birthday));
		String flag = studentService.saveStudent(student);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(flag);
	}
	
	/**
	 * 更新数据
	 * @throws IOException 
	 * @throws ParseException 
	 * 
	 * */
	@RequestMapping("/studentupdate.do")
	public void updateStudent(HttpServletResponse resp,Integer id,String number,String name,String picture,String sex,String birthday) throws IOException, ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Student stu = new Student(id, number, name, picture, sex, sdf.parse(birthday));
		studentService.updateStudent(stu);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print("1");
	}
	
	/**
	 * 删除数据
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/studentdelete.do")
	public void deleteStudent(HttpServletResponse resp,String ids) throws IOException{
		String[] arrayId = ids.split(",");
		studentService.deleteStudent(arrayId);
		resp.getWriter().println(new Integer(arrayId.length).toString());
	}
}
