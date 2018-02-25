package com.biz.std.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biz.std.model.Grade;
import com.biz.std.model.Subject;
import com.biz.std.service.SubjectService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
@RequestMapping("/")
public class SubjectController {

	private ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)  //类级别的设置，JsonInclude.Include.NON_EMPTY标识只有非NULL的值才会被纳入json string之中，其余的都将被忽略
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) //禁止使用出现未知属性之时，抛出异常

.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);//转化后的json的key命名格式

	@Autowired
	private SubjectService subjectService;
	
	/**
	 * 进行分页查询
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/subjectlist.do")
	public void getSubjectList(HttpServletResponse resp,int page,int rows) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(OBJECT_MAPPER.writeValueAsString(subjectService.findAllSubjects(page,rows)));
	}
	
	/**
	 * 插入新数据
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/subjectsave.do")
	public void saveSubject(HttpServletResponse resp,Subject subject) throws IOException{
		subjectService.saveSubject(subject);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print("1");
	}
	
	/**
	 * 更新数据
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/subjectupdate.do")
	public void updateGrade(HttpServletResponse resp,Subject subject) throws IOException{
		subjectService.updateSubject(subject);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print("1");
	}
	
	/**
	 * 删除数据
	 * @throws IOException 
	 * 
	 * */
	@RequestMapping("/subjectdelete.do")
	public void deleteSubject(HttpServletResponse resp,String ids) throws IOException{
		String[] arrayId = ids.split(",");
		subjectService.deleteSubject(arrayId);
		resp.getWriter().println(new Integer(arrayId.length).toString());
	}
}
