package com.payroll.payroll_system.model;

import com.payroll.payroll_system.enums.SalaryType;
import com.payroll.payroll_system.enums.status.EmployeeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "first name is required")
    private String firstName;
    @NotBlank(message = "last name is required")
    private String lastName;
    @NotBlank(message = "phone number is required")
    private String phoneNumber;
    @NotBlank(message = "bank account number is required")
    private String bankAccountNumber;
    @NotBlank(message = "position is required")
    private String position;
    @NotNull(message = "hire date is required")
    private LocalDate hireDate;
    @NotNull(message = "total working days is required")
    @Min(1)
    @Max(30)
    private int workingdays;
    @NotNull(message = "net salary is required")
    private double baseSalary;
    @Transient
    private double netSalary;
    @Transient
    private double totalAllowance;
    @Transient
    private double totalDeduction;
    @NotNull(message = "deposit is required")
    private double deposit;
    @NotNull(message = "status is required")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;
    @NotNull(message = "department id is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department deptId;

}
