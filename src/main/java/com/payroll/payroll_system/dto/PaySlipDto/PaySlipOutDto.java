package com.payroll.payroll_system.dto.PaySlipDto;

import com.payroll.payroll_system.model.Employee;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaySlipOutDto {
    private Long id;
    private double grossSalary;
    private double totalDeduction;
    private double totalAllowance;
    private double netSalary;
    private Long empId;
}
