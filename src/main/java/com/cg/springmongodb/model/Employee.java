package com.cg.springmongodb.model;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "employee")
public class Employee {
	@Id
	private int id;
	@NotNull(message = "name cannot be null")
	private String name;
	@NotNull(message = "role cannot be null")
	private String role;
	@Past(message = "from date should be the PAST date")
	private Date from;
	@FutureOrPresent(message = "to date date must be a future or present date")
	private Date to;
}
