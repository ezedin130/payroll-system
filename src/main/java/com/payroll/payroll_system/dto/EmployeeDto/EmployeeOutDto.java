package com.payroll.payroll_system.dto.EmployeeDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeOutDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String bankAccountNumber;
    private String position;
    private LocalDate hireDate;
    private int workingdays;
    private double baseSalary;
    private double totalAllowance;
    private double totalDeduction;
    private double netSalary;
    private double deposit;
    private String status;
    private Long deptId;
}
