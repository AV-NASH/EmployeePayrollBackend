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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;

    public EmployeePayrollDto CreateUser(EmployeePayrollDto employeePayrollDto){
        if(Objects.nonNull(employeePayrollDto.getName()) && Objects.nonNull(employeePayrollDto.getSalary())&&
                Objects.nonNull(employeePayrollDto.getDepartment())&&Objects.nonNull(employeePayrollDto.getStartDate())&&
                Objects.nonNull(employeePayrollDto.getGender())&&Objects.nonNull(employeePayrollDto.getNotes())
        &&Objects.nonNull(employeePayrollDto.getProfilepic())) {
            EmployeePayroll employeePayroll = new EmployeePayroll(employeePayrollDto.getName(),employeePayrollDto.getGender(),employeePayrollDto.getDepartment(),employeePayrollDto.getSalary(),employeePayrollDto.getStartDate(),employeePayrollDto.getNotes(),employeePayrollDto.getProfilepic());
            return new EmployeePayrollDto(employeePayrollRepository.save(employeePayroll));
        }

        throw new DetailsNotProvidedExceptions("Invalid Data");
    }

    public EmployeePayrollDto UpdateUser(EmployeePayrollDto employeePayrollDto){

        return employeePayrollRepository.findById(employeePayrollDto.getId()).map(employeePayroll -> {
            if(Objects.nonNull(employeePayrollDto.getName())){
                employeePayroll.setName(employeePayrollDto.getName());
            }
            if(Objects.nonNull(employeePayrollDto.getGender())){
                employeePayroll.setGender(employeePayrollDto.getGender());
            }
            if(Objects.nonNull(employeePayrollDto.getDepartment())){
                employeePayroll.setDepartment(employeePayrollDto.getDepartment());
            }
            if(Objects.nonNull(employeePayrollDto.getSalary())){
                employeePayroll.setSalary(employeePayrollDto.getSalary());
            }

            if(Objects.nonNull(employeePayrollDto.getStartDate())){
                employeePayroll.setStartDate(employeePayrollDto.getStartDate());
            }
            if(Objects.nonNull(employeePayrollDto.getProfilepic())){
                employeePayroll.setProfilepic(employeePayrollDto.getProfilepic());
            }
            if(Objects.nonNull(employeePayrollDto.getNotes())){
                employeePayroll.setNotes(employeePayrollDto.getNotes());
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

    public Optional<EmployeePayrollDto> getById(long id) {
        System.out.println("in get");
        System.out.println(employeePayrollRepository.findById(id));
        Optional<EmployeePayroll> employeePayrolloptional= employeePayrollRepository.findById(id);
        EmployeePayroll employeePayroll=employeePayrolloptional.get();
        return Optional.of(new EmployeePayrollDto(employeePayroll));
    }
}
