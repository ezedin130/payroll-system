package com.payroll.payroll_system.dto.EmployeeDto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeOutDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bankAccountNumber;
    private LocalDate hireDate;
    private int workingdays;
    private double baseSalary;
    private double deposit;
    private String status;
    private Long deptId;
    private Long roleId;
}
