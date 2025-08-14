package com.payroll.payroll_system.controller;

import com.payroll.payroll_system.dto.RoleDto.RoleInDto;
import com.payroll.payroll_system.dto.RoleDto.RoleOutDto;
import com.payroll.payroll_system.service.RoleService;
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
public class RoleController {
    private final RoleService service;

    @PostMapping("/role")
    public ResponseEntity<RoleOutDto> createRole(@RequestBody RoleInDto dto){
        RoleOutDto result = service.createRole(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/role")
    public ResponseEntity<List<RoleOutDto>> getAllRoles(){
        List<RoleOutDto> result = service.getAllRoles();
        return ResponseEntity.ok(result);
    }
}
