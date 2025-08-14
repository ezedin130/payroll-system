package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodInDto;
import com.payroll.payroll_system.dto.PayrollPeriodDto.PayrollPeriodOutDto;
import com.payroll.payroll_system.service.PayrollPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class PayrollPeriodController {
    private final PayrollPeriodService service;

    @PostMapping("payroll")
    public ResponseEntity<PayrollPeriodOutDto> createPayroll(@RequestBody PayrollPeriodInDto dto){
        PayrollPeriodOutDto result = service.createPayroll(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("payroll")
    public ResponseEntity<List<PayrollPeriodOutDto>> getAllPayrolls(){
        List<PayrollPeriodOutDto> result = service.getAllPayrolls();
        return ResponseEntity.ok(result);
    }
}
