package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.EmployeeDto.EmployeeInDto;
import com.payroll.payroll_system.dto.EmployeeDto.EmployeeOutDto;
import com.payroll.payroll_system.mapper.EmployeeMapper;
import com.payroll.payroll_system.model.Department;
import com.payroll.payroll_system.model.Employee;
import com.payroll.payroll_system.model.Role;
import com.payroll.payroll_system.repo.DepartmentRepo;
import com.payroll.payroll_system.repo.EmployeeRepo;
import com.payroll.payroll_system.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private final EmployeeRepo empRepo;
    @Autowired
    private final DepartmentRepo deptRepo;
    @Autowired
    private final RoleRepo roleRepo;
    @Autowired
    private final EmployeeMapper mapper;

    public EmployeeOutDto createEmployee(EmployeeInDto dto){
        Department dept = deptRepo.findById(dto.getDeptId())
                .orElseThrow(() -> new RuntimeException("Department Not Found"));
        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
        Employee empl = mapper.toEntity(dto, dept , role);
        Employee savedEmpl = empRepo.save(empl);
        return mapper.toDto(savedEmpl);
    }
    public List<EmployeeOutDto> getAllEmployees(){
        return empRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    public Employee searchEmployeeById(Long id){
        return empRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with this is not found"));
    }
}
