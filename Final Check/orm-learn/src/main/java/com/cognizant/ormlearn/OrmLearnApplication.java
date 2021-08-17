package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	private static StockService stockService;
	private static EmployeeService empService;
	private static DepartmentService deptService;
	private static SkillService skillService;
	@Autowired
	public static StockRepository repository;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		LOGGER.info("Application Started...");
		empService = context.getBean(EmployeeService.class);
		deptService = context.getBean(DepartmentService.class);
		skillService = context.getBean(SkillService.class);
//		testGetAllCountries();
//		testAddCountry();
//		testUpdateCountry();
//		testDeleteCountry();
//		testGetEmployee();
//		testAddEmployee();
//		testUpdateEmployee();
//		testGetDepartment();
		testAddSkillToEmployee();
		testGetAllPermanentEmployees();
		testGetAllEmployeesNative();
	}

	public static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countryList = countryService.getAllCountries();
		LOGGER.info("Countries : {} ", countryList);
		try {
			Country country = countryService.findCountryByCode("AF");
			LOGGER.info("Countries : {} ", country);
		} catch (CountryNotFoundException e) {
			LOGGER.error(e.getMessage());
		}

		LOGGER.info("End");

	}

	public static void testAddCountry() {
		LOGGER.info("Start");
		Country country = new Country("AA", "New Country");
		countryService.addCountry(country);
		LOGGER.info("End");

	}

	public static void testUpdateCountry() {
		LOGGER.info("Start");
		try {
			countryService.updateCountry("MM", "New Country Modified");
		} catch (CountryNotFoundException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("End");

	}

	public static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("MM");
		LOGGER.info("End");
	}

	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee = empService.get(1);
		LOGGER.debug("Employee:{}", employee);
		LOGGER.debug("Department:{}", employee.getDepartment());
		LOGGER.debug("Skills: {}", employee.getSkillList());
		LOGGER.info("End");
	}

	private static void testAddEmployee() throws ParseException {
		LOGGER.info("Start");
		Employee employee = new Employee();
		employee.setName("Tom");
		employee.setSalary(new BigDecimal(50000));
		employee.setPermanent(true);
		employee.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("06/08/2017"));
		employee.setDepartment(deptService.get(1));
		empService.addEmployee(employee);

	}

	private static void testUpdateEmployee() {
		Employee employee = empService.get(5);
		employee.setDepartment(deptService.get(2));
		empService.addEmployee(employee);
	}

	private static void testGetDepartment() {
		Department department = deptService.get(2);
		LOGGER.debug("Department: {}", department);
		LOGGER.debug("Employee List: {}", department.getEmployeeList());
	}

	private static void testAddSkillToEmployee() {
		Employee employee = empService.get(1);
		Skill skill = skillService.get(3);
		employee.getSkillList().add(skill);
		empService.addEmployee(employee);
	}

	public static void testGetAllPermanentEmployees() {

		LOGGER.info("Start");
		List<Employee> employees = empService.getAllPermanentEmployees();
		LOGGER.debug("Permanent Employees:{}", employees);
		employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
		LOGGER.info("End");
	}

	public static void testGetAllEmployeesNative() {

		LOGGER.info("Start");
		List<Employee> employees = empService.getAllEmployeesNative();
		LOGGER.debug("Employees:{}", employees);
		LOGGER.info("End");
	}
}