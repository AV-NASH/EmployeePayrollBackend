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
    public ResponseEntity<ResponseDto> createUser(@RequestBody @Valid EmployeePayrollDto user, BindingResult bindingResult){
        try{
            if(bindingResult.hasErrors()){
                return new ResponseEntity<ResponseDto>(new ResponseDto((bindingResult.getAllErrors().get(0).getDefaultMessage()),"404"),HttpStatus.BAD_REQUEST);
            }
            EmployeePayrollDto employeePayrollDto = employeePayrollService.CreateUser(user);

           return new ResponseEntity<ResponseDto> ( new ResponseDto("User created successfully",employeePayrollDto),HttpStatus.CREATED);
        } catch (UserNotFound e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeePayrollDto> updateUser(@RequestBody EmployeePayrollDto user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(employeePayrollService.UpdateUser(user));
        } catch (UserNotFound e){
            throw new PayrollException(PayrollException.ExceptionTypes.EMPLOYEE_NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeePayrollDto> deleteUser(@PathVariable("id")Long id){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeePayrollService.deleteUser(id));
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
