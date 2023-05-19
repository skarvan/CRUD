package com.api.crud.dto;


import lombok.Data;

@Data
public class EmployeeDto {
    private long id;
    private String name;
    private long salary;
    private String dept_name;
}
