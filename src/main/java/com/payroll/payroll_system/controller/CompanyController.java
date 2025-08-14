package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.CompanyDto.CompanyInDto;
import com.payroll.payroll_system.dto.CompanyDto.CompanyOutDto;
import com.payroll.payroll_system.mapper.CompanyMapper;
import com.payroll.payroll_system.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class CompanyController {
    @Autowired
    private final CompanyService service;
    @PostMapping("/company")
    public ResponseEntity<CompanyOutDto> createCompany(@RequestBody CompanyInDto dto){
        CompanyOutDto result = service.createCompany(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/company")
    public ResponseEntity<List<CompanyOutDto>> getAllCompanies(){
        List<CompanyOutDto> result = service.getAllCompanies();
        return ResponseEntity.ok(result);
    }
}
