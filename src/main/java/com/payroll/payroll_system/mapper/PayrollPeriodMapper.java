package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodInDto;
import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodOutDto;
import com.payroll.payroll_system.enums.status.PayrollStatus;
import com.payroll.payroll_system.model.PayrollPeriod;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PayrollPeriodMapper {
    public PayrollPeriod toEntity(PayrollPeriodInDto dto){
        return PayrollPeriod.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(PayrollStatus.valueOf(dto.getStatus()))
                .processedAt(LocalDateTime.now())
                .build();
    }
    public PayrollPeriodOutDto toDto(PayrollPeriod payroll){
        PayrollPeriodOutDto dto = new PayrollPeriodOutDto();
        dto.setId(payroll.getId());
        dto.setStartDate(payroll.getStartDate());
        dto.setEndDate(payroll.getEndDate());
        dto.setStatus(payroll.getStatus().name());
        dto.setProcessedAt(payroll.getProcessedAt());
        return dto;
    }
}
