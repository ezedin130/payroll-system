package com.payroll.payroll_system.dto.PayrollPeriodDto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PayrollPeriodInDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
