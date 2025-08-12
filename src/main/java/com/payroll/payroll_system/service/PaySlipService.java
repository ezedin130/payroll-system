package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.PaySlipDto.PaySlipInDto;
import com.payroll.payroll_system.dto.PaySlipDto.PaySlipOutDto;
import com.payroll.payroll_system.mapper.PaySlipMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.PaySlip;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.PaySlipRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaySlipService {
    @Autowired
    private final PaySlipRepo slipRepo;
    @Autowired
    private final EmployeeRepo empRepo;
    @Autowired
    private final PaySlipMapper mapper;

    public PaySlipOutDto createPaySlip(PaySlipInDto dto){
        Employee empl = empRepo.findById(dto.getEmpId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        PaySlip slip = mapper.toEntity(dto,empl);
        PaySlip savedSlip = slipRepo.save(slip);
        return mapper.toDto(savedSlip);
    }
    public List<PaySlipOutDto> getAllSlips(){
        return slipRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}
