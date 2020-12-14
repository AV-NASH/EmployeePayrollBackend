package com.cg.employeepayrollspring.dto;


import com.cg.employeepayrollspring.domain.EmployeePayroll;
import com.cg.employeepayrollspring.domain.EmployeePayroll;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Data
@AllArgsConstructor
public class EmployeePayrollDto {

    private Long id;
    @NotNull(message = "Please enter valid name")
    @NotEmpty(message = "Please enter valid name")
    @Pattern(regexp = "^[A-Za-z]{3,}$", message = "Please enter valid name")
    private String name;
    private String gender;
    private String department;
    @Min(value = 500,message = "salary cant be less than 500")
    private String salary;
    private Date startDate;

    public EmployeePayrollDto(EmployeePayroll user){
        this.id = user.getId();
        this.name = user.getName();
        this.salary = user.getSalary();
        this.gender = user.getGender();
        this.department = user.getDepartment();
        this.startDate = user.getStartDate();
    }

}
