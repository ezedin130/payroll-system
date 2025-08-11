package com.payroll.payroll_system.dto.EmployeeDto;

import lombok.Data;

@Data
public class EmployeeOutDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String position;
    private String bankAccountNumber;
    private int workingdays;
    private double baseSalary;
    private double deposit;
    private String status;
    private Long deptId;
}
