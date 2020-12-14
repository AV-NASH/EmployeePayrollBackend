package com.cg.employeepayrollspring.service;

import com.cg.employeepayrollspring.domain.EmployeePayroll;
import com.cg.employeepayrollspring.dto.EmployeePayrollDto;
import com.cg.employeepayrollspring.exceptions.*;
import com.cg.employeepayrollspring.exceptions.UserNotFound;
import com.cg.employeepayrollspring.repository.EmployeePayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    public EmployeePayrollDto CreateUser(EmployeePayrollDto employeePayrollDto){
        if(Objects.nonNull(employeePayrollDto.getName()) && Objects.nonNull(employeePayrollDto.getSalary())) {
            EmployeePayroll employeePayroll = new EmployeePayroll(employeePayrollDto.getName(),employeePayrollDto.getSalary());
            return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }

        throw new DetailsNotProvidedExceptions("Invalid Data");
    }

    public EmployeePayrollDto UpdateUser(EmployeePayrollDto employeePayrollDto){

        return employeePayrollRepository.findById(employeePayrollDto.getId()).map(employeePayroll -> {
            if(Objects.nonNull(employeePayrollDto.getName())){
                employeePayroll.setName(employeePayrollDto.getName());
            }
            if(Objects.nonNull(employeePayroll.getSalary())){
                employeePayroll.setSalary(employeePayroll.getSalary());
            }
           return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }).orElseThrow(()-> new UserNotFound("UserNotFound"));
    }

    public EmployeePayrollDto deleteUser(Long id){
        return employeePayrollRepository.findById(id).map(employeePayroll -> {
            employeePayrollRepository.deleteById(employeePayroll.getId());
            return new EmployeePayrollDto(employeePayroll);
        }).orElseThrow(()-> new UserNotFound("UserNotFound"));
    }


    public List<EmployeePayrollDto> getAllUser(){
        return employeePayrollRepository.findAll()
                .stream()
                .map(employeePayroll -> new EmployeePayrollDto(employeePayroll))
                .collect(Collectors.toList());
    }
}
