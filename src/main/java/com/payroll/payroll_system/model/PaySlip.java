package com.payroll.payroll_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaySlip {
    //TODO:  the system should insert data to the table by itself after calculating everything
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "gross salary is required")
    private double grossSalary;
    //'ToDo: make sure the deduction and allowances are based on employee position '
    @NotNull(message = "total deduction is required")
    private double totalDeduction;
    @NotNull(message = "taxable income is required")
    private double taxableIncome;
    @NotNull(message = "income tax is required")
    private double incomeTax;
    @NotNull(message = "employee Pension is required")
    private double employeePension;
    @NotNull(message = "total allowance is required")
    private double totalAllowance;
    @NotNull(message = "net salary is required")
    private double netSalary;
    @NotNull(message = "employee id is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee empId;

}
