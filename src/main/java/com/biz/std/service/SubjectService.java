package com.biz.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.std.model.Pagination;
import com.biz.std.model.Subject;
import com.biz.std.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	public Pagination findAllSubjects(int page, int rows) {
		Sort.Order order = new Sort.Order(Sort.Direction.ASC,"id");
		Sort sort = new Sort(order);
		
		Pageable pageable = new PageRequest(page-1,rows,sort);
		Page<Subject> list = subjectRepository.findAll(pageable);
		Pagination pagination = new Pagination(list.getTotalElements(),list.getNumber()+1,list.getContent());
		return pagination;
	}

	@Transactional
	public void saveSubject(Subject subject) {
		subjectRepository.save(subject);
	}

	@Transactional
	public void updateSubject(Subject subject) {
		Subject subtemp = subjectRepository.findOne(subject.getId());
		subtemp.setSubjectname(subject.getSubjectname());
		subtemp.setOptionname(subject.getOptionname());
		subtemp.setAvg(subject.getAvg());
		subjectRepository.saveAndFlush(subtemp);
	}

	@Transactional
	public void deleteSubject(String[] ids) {
		for(String id : ids){
			subjectRepository.delete(Integer.parseInt(id));
		}
	}
}
