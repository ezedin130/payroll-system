package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.DepartmentDto.DepartmentInDto;
import com.payroll.payroll_system.dto.DepartmentDto.DepartmentOutDto;
import com.payroll.payroll_system.mapper.DepartmentMapper;
import com.payroll.payroll_system.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService service;
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @PostMapping("/department")
    public ResponseEntity<DepartmentOutDto> createDepartment(@RequestBody DepartmentInDto dto){
        DepartmentOutDto result = service.createDepartment(dto);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    @GetMapping("/department")
    public ResponseEntity<List<DepartmentOutDto>> getAllDepartments(){
        List<DepartmentOutDto> result = service.getAllDepartments();
        return ResponseEntity.ok(result);
    }
}
