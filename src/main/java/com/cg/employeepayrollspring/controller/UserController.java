package com.cg.employeepayrollspring.controller;

import com.cg.employeepayrollspring.dto.EmployeePayrollDto;
import com.cg.employeepayrollspring.dto.ResponseDto;
import com.cg.employeepayrollspring.exceptions.PayrollException;
import com.cg.employeepayrollspring.exceptions.UserNotFound;
import com.cg.employeepayrollspring.service.EmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UserController {

    @Autowired
    EmployeePayrollService employeePayrollService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid EmployeePayrollDto user){
        try{
            EmployeePayrollDto employeePayrollDto = employeePayrollService.CreateUser(user);

           return new ResponseEntity<ResponseDto> ( new ResponseDto("User created successfully","200",employeePayrollDto),HttpStatus.CREATED);
        } catch (UserNotFound e){
            throw new PayrollException(PayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody @Valid EmployeePayrollDto user, BindingResult bindingResult){
        try{
            EmployeePayrollDto employeePayrollDto = employeePayrollService.UpdateUser(user);
            return new ResponseEntity<ResponseDto> ( new ResponseDto("User updated successfully","200",employeePayrollDto),HttpStatus.CREATED);
        } catch (UserNotFound e){
            throw new PayrollException(PayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable("id")Long id){
        try{
            EmployeePayrollDto employeePayrollDto = employeePayrollService.deleteUser(id);
            return new ResponseEntity<ResponseDto>(new ResponseDto("Employee Deleted Successfully","200",employeePayrollDto),HttpStatus.CREATED);
        } catch (UserNotFound e){
            throw new PayrollException(PayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<EmployeePayrollDto>> getAllUser(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.getAllUser());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
