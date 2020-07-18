package com.javatechie.spring.mokito.api.repository;

import com.javatechie.spring.mokito.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
