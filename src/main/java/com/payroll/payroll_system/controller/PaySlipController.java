package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.PaySlipDto.PaySlipInDto;
import com.payroll.payroll_system.dto.PaySlipDto.PaySlipOutDto;
import com.payroll.payroll_system.service.PaySlipService;
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
public class PaySlipController {
    private final PaySlipService service;

    @PostMapping("slip")
    public ResponseEntity<PaySlipOutDto> createSlip(@RequestBody PaySlipInDto dto){
        PaySlipOutDto result = service.createPaySlip(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("slip")
    public ResponseEntity<List<PaySlipOutDto>> getAllSlips(){
        List<PaySlipOutDto> result = service.getAllSlips();
        return ResponseEntity.ok(result);
    }
}
