package com.cg.springmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.springmongodb.model.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, Integer> {

}
