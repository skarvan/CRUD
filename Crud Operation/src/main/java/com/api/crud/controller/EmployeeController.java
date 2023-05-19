package com.api.crud.controller;

import com.api.crud.dto.EmployeeDto;
import com.api.crud.entity.Employee;
import com.api.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.List;

@RestController("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping(value = "/employees")
    public List<EmployeeDto> getAllEmp(){
       return employeeService.getAllEmployees();

    }

    @GetMapping(value = "/employees/{id}")
    public EmployeeDto getEmp(@PathVariable long id){

        return employeeService.getEmployee(id);

    }
    @PostMapping(value = "/employees")
    public String addEmployee(@RequestBody Employee employee)
    {
       return employeeService.addEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public String updateEmployee(@RequestBody Employee employee, @PathVariable long id){
            return employeeService.updateEmployee(id,employee);

    }

}

