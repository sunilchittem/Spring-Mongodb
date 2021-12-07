package com.cg.springmongodb.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class EmployeeValidation {
	//will get triggered before data persist to database to do validation
	@Bean
	public ValidatingMongoEventListener eventListner() {
		return new ValidatingMongoEventListener(validation());
	}
	@Bean
	public LocalValidatorFactoryBean validation() {
		return new LocalValidatorFactoryBean();
	}
}
