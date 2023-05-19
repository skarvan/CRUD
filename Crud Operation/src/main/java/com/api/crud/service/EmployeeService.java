package com.api.crud.service;

import com.api.crud.dto.EmployeeDto;
import com.api.crud.entity.Department;
import com.api.crud.entity.Employee;
import com.api.crud.exception.ResourceNotFoundException;
import com.api.crud.repository.DepartmentRepo;
import com.api.crud.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<EmployeeDto> getAllEmployees(){

        List<EmployeeDto> listOfEmployee= new ArrayList<>();

        for(Employee emp : employeeRepo.findAll())
        {
            Department department = departmentRepo.findById(emp.getDept_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + emp.getId()));

            EmployeeDto dto = new EmployeeDto();
            dto.setName(emp.getName());
            dto.setId(emp.getId());
            dto.setSalary(emp.getSalary());
            dto.setDept_name(department.getDept_name());
            listOfEmployee.add(dto);
        }
        return listOfEmployee;
    }
    public EmployeeDto getEmployee(long id){

       Employee emp= employeeRepo.findById((long) id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
        Department department = departmentRepo.findById(emp.getDept_id())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + emp.getId()));

        EmployeeDto dto = new EmployeeDto();

        dto.setName(emp.getName());
        dto.setId(emp.getId());
        dto.setSalary(emp.getSalary());
        dto.setDept_name(department.getDept_name());

        return  dto;
    }

    public String addEmployee(Employee emp){

            Employee employee = employeeRepo.save(emp);
            return "Ok";
    }

    public String updateEmployee( long id,Employee emp)
    {

            Employee employee = employeeRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

            employee.setId(emp.getId());
            employee.setName(emp.getName());
            employee.setSalary(emp.getSalary());
            employee.setDept_id(emp.getDept_id());

            employeeRepo.save(employee);
            return "Updated Successfully";
    }

}
