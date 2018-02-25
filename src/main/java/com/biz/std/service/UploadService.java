package com.biz.std.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.std.model.StudentImage;
import com.biz.std.repository.UploadRepository;

@Service
public class UploadService {

	@Autowired
	private UploadRepository uploadRepository;

	@Transactional
	public void saveImage(StudentImage si) {
		uploadRepository.save(si);
	}

	@Transactional
	public StudentImage findOneStudentImage(int studentid) {
		return uploadRepository.findByStudentId(studentid);
	}

	@Transactional
	public void deleteStudentImage(StudentImage stuImg) {
		uploadRepository.delete(stuImg);
	}
}
