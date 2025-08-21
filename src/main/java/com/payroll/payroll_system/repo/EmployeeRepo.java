package com.payroll.payroll_system.repo;

import com.payroll.payroll_system.enums.status.EmployeeStatus;
import com.payroll.payroll_system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    List<Employee> findByStatus(EmployeeStatus employeeStatus);
}
