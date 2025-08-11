package com.payroll.payroll_system.dto.PayrollPeriodDto;

import com.payroll.payroll_system.enums.status.PayrollStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class PayrollPeriodOutDto {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime processedAt;
}
