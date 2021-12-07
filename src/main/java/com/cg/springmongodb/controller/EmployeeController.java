package com.cg.springmongodb.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springmongodb.model.Employee;
import com.cg.springmongodb.model.exception.EmployeeNotFoundException;
import com.cg.springmongodb.repository.EmployeeRepo;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepo employeeRepo;

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findEmployees() {

		List<Employee> employees = employeeRepo.findAll();
		if (employees.size() > 0) {
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/addemployee")
	public Employee addEmployee(@RequestBody Employee emp) {
		emp.setFrom(new Date(System.currentTimeMillis()));
		Employee save = employeeRepo.save(emp);
		return save;

	}

	@GetMapping("/employee/{id}")
	public Employee findEmployee(@PathVariable("id") int empId) {

		Optional<Employee> findById = employeeRepo.findById(empId);
		findById.orElseThrow(() -> new EmployeeNotFoundException(empId));
		return findById.get();
	}

	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id") int empId, @RequestBody Employee emp) {

		Optional<Employee> findById = employeeRepo.findById(empId);
		findById.orElseThrow(() -> new EmployeeNotFoundException(empId));
		if (Objects.nonNull(emp.getName())) {
			findById.get().setName(emp.getName());
		}
		if (Objects.nonNull(emp.getRole())) {
			findById.get().setRole(emp.getRole());
		}
		if (Objects.nonNull(emp.getTo())) {
			findById.get().setTo(emp.getTo());
		}
		return findById.get();
	}

	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") int empId) {

		employeeRepo.deleteById(empId);
	}
	
	@PostMapping("/addemployees")
	public ResponseEntity<Iterable<Employee>> addEmployees(@RequestBody Iterable<Employee> emp){
		List<Employee> saveAll = employeeRepo.saveAll(emp);
		/*
		 * Spliterator<Employee> it = emp.spliterator(); it.forEachRemaining(t ->
		 * t.getId());
		 */
		
		return new ResponseEntity<Iterable<Employee>>(saveAll, HttpStatus.OK);
	}
}
