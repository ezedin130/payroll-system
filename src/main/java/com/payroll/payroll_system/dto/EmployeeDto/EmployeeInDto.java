package com.payroll.payroll_system.dto.EmployeeDto;

import com.payroll.payroll_system.enums.status.EmployeeStatus;
import com.payroll.payroll_system.model.Department;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeInDto {
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
