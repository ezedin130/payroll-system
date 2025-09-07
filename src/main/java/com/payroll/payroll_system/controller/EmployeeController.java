package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.EmployeeDto.EmployeeInDto;
import com.payroll.payroll_system.dto.EmployeeDto.EmployeeOutDto;
import com.payroll.payroll_system.mapper.EmployeeMapper;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Validated
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;
    private final EmployeeMapper mapper;
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PostMapping("/create-employee")
    public ResponseEntity<EmployeeOutDto> createEmployee(@RequestBody EmployeeInDto dto){
        EmployeeOutDto result = service.createEmployee(dto);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @GetMapping("/get-all-employees")
    public ResponseEntity<List<EmployeeOutDto>> getAllEmployees(){
        List<EmployeeOutDto> result = service.getAllEmployees();
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAnyRole('ADMIN','FINANCE')")
    @GetMapping("/get-employee-by-id/{id}")
    public ResponseEntity<EmployeeOutDto> getEmployeeById(@PathVariable Long id){
        Employee empl = service.searchEmployeeById(id);
        EmployeeOutDto dto = mapper.toDto(empl);
        return ResponseEntity.ok(dto);
    }
}
