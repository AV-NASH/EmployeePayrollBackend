package com.cg.employeepayrollspring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class EmployeePayroll implements Serializable {

    private static final long serialVersionUID = -8900492704842756948L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String gender;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "employee_department" , joinColumns=@JoinColumn(name="id"))
    @Column(name = "department")
    private List<String> department;
    private String salary;
    private String startDate;
    private String notes;
    private String profilepic;


    public EmployeePayroll() {

    }

    public EmployeePayroll(String name, String gender, List<String> department, String salary, String startDate,String notes, String profilepic) {
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.startDate = startDate;
        this.notes=notes;
        this.profilepic=profilepic;
    }
}
