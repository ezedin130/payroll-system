package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodInDto;
import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodOutDto;
import com.payroll.payroll_system.mapper.PayrollPeriodMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.PayrollPeriod;
import com.payroll.payroll_system.service.PayrollPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class PayrollPeriodController {
    private final PayrollPeriodService service;
    private final PayrollPeriodMapper mapper;

    @PostMapping("/run")
    public ResponseEntity<String> runPayroll(){
        service.runPayroll();
        return ResponseEntity.ok("Payroll has been processed successfully");
    }
    @GetMapping("/payroll")
    public ResponseEntity<List<PayrollPeriodOutDto>> getAllPayrolls(){
        List<PayrollPeriodOutDto> result = service.getAllPayrolls();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PayrollPeriodOutDto> getPayroll(@PathVariable Long id){
        PayrollPeriod payroll = service.getPayrollById(id);
        PayrollPeriodOutDto dto = mapper.toDto(payroll);
        return ResponseEntity.ok(dto);
    }
}
