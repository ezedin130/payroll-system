package com.payroll.payroll_system.dto.PaySlipDto;

import lombok.Data;

@Data
public class PaySlipInDto {
    private double grossSalary;
    private double totalDeduction;
    private double totalAllowance;
    private double netSalary;
    private Long empId;
}
