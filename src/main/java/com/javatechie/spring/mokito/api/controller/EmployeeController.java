package com.javatechie.spring.mokito.api.controller;

import com.javatechie.spring.mokito.api.model.Employee;
import com.javatechie.spring.mokito.api.model.Response;
import com.javatechie.spring.mokito.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/EmployeeService")
public class EmployeeController
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/addEmployee")
    public Response addEmp(@RequestBody Employee emp)
    {
        employeeRepository.save(emp);
        return new Response(emp.getId()+" added", Boolean.TRUE);
    }

    @GetMapping("/getEmployee")
    public Response getAllEmp()
    {
        List<Employee>  emp = employeeRepository.findAll();
        return new Response("record count "+emp.size(), Boolean.TRUE);
    }
}
