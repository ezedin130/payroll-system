package com.payroll.payroll_system.dto.EmployeeDto;

import lombok.Data;

@Data
public class EmployeeInDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bankAccountNumber;
    private String position;
    private int workingdays;
    private double baseSalary;
    private double deposit;
    private String status;
    private Long deptId;
    private Long roleId;
}
