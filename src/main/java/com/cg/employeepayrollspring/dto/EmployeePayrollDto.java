package com.cg.employeepayrollspring.dto;


import com.cg.employeepayrollspring.domain.EmployeePayroll;
import com.cg.employeepayrollspring.domain.EmployeePayroll;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeePayrollDto {

    private Long id;
    @NotNull(message = "Please enter valid name")
    @NotEmpty(message = "Please enter valid name")
    @Pattern(regexp = "^[A-Za-z]{3,}$", message = "Please enter valid name")
    private String name;
    @Min(value = 500,message = "salary cant be less than 500")
    private String salary;

    public EmployeePayrollDto(){

    }

    public EmployeePayrollDto(EmployeePayroll user){
        this.id = user.getId();
        this.name = user.getName();
        this.salary = user.getSalary();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSalary() {
        return salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
