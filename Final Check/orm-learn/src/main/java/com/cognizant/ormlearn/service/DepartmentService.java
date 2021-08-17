package com.cognizant.ormlearn.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository deptRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Transactional
	public Department get(int id) {
		LOGGER.info("Start");
		return deptRepository.findById(id).get();
	}

	@Transactional
	public void save(Department department) {
		LOGGER.info("Start");
		deptRepository.save(department);
		LOGGER.info("End");
	}
}
