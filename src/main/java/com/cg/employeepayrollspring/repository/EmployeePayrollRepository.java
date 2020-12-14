package com.cg.employeepayrollspring.repository;

import com.cg.employeepayrollspring.domain.EmployeePayroll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePayrollRepository extends JpaRepository<EmployeePayroll,Long> {
}
