package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodInDto;
import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodOutDto;
import com.payroll.payroll_system.mapper.PayrollPeriodMapper;
import com.payroll.payroll_system.model.PayrollPeriod;
import com.payroll.payroll_system.repo.PayrollPeriodRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollPeriodService {
    @Autowired
    private final PayrollPeriodRepo repo;
    @Autowired
    private final PayrollPeriodMapper mapper;

    public PayrollPeriodOutDto createPayroll(PayrollPeriodInDto dto){
        PayrollPeriod payroll = mapper.toEntity(dto);
        PayrollPeriod savedPayroll = repo.save(payroll);
        return mapper.toDto(savedPayroll);
    }
    public List<PayrollPeriodOutDto> getAllPayrolls(){
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
