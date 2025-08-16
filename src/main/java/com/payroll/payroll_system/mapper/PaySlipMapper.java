package com.payroll.payroll_system.mapper;

import com.payroll.payroll_system.dto.PaySlipDto.PaySlipInDto;
import com.payroll.payroll_system.dto.PaySlipDto.PaySlipOutDto;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.PaySlip;
import org.springframework.stereotype.Service;

@Service
public class PaySlipMapper {
    public PaySlipOutDto toDto(PaySlip slip){
        PaySlipOutDto dto = new PaySlipOutDto();
        dto.setId(slip.getId());
        dto.setGrossSalary(slip.getGrossSalary());
        dto.setTotalDeduction(slip.getTotalDeduction());
        dto.setTotalAllowance(slip.getTotalAllowance());
        dto.setNetSalary(slip.getNetSalary());
        dto.setEmpId(slip.getEmpId().getId());
        return dto;
    }
}
