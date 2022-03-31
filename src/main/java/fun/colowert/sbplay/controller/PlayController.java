package fun.colowert.sbplay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fun.colowert.sbplay.dao.EmployeeDao;
import fun.colowert.sbplay.model.Employee;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PlayController {

	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ResponseBody
	public List<Employee> getEmployees() {
		log.info("All employees are returned");
		return employeeDao.getAllEmployees();
	}

	@RequestMapping(value = "/employee/{empNo}", method = RequestMethod.GET)
	@ResponseBody
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		return employeeDao.getEmployee(empNo);
	}

	@PostMapping("/employee")
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee emp) {
		log.info("(Service Side) Creating employee: {}", emp.getEmpNo());
		return employeeDao.addEmployee(emp);
	}

	@RequestMapping(value = "/employee", method = RequestMethod.PUT)
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee emp) {
		log.info("(Service Side) Editing employee: " + emp.getEmpNo());
		return employeeDao.updateEmployee(emp);
	}

	@RequestMapping(value = "/employee/{empNo}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteEmployee(@PathVariable("empNo") String empNo) {
		log.info("(Service Side) Deleting employee: " + empNo);
		employeeDao.deleteEmployee(empNo);
	}

}
