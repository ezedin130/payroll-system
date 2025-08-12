package com.payroll.payroll_system.service;

import com.payroll.payroll_system.dto.DepartmentDto.DepartmentInDto;
import com.payroll.payroll_system.dto.DepartmentDto.DepartmentOutDto;
import com.payroll.payroll_system.mapper.DepartmentMapper;
import com.payroll.payroll_system.model.Department;
import com.payroll.payroll_system.repo.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    @Autowired
    private final DepartmentRepo repo;
    @Autowired
    private final DepartmentMapper mapper;

    public DepartmentOutDto createDepartment(DepartmentInDto dto){
        Department dept = mapper.toEntity(dto);
        Department savedDept = repo.save(dept);
        return mapper.toDto(savedDept);
    }
    public List<DepartmentOutDto> getAllDepartments(){
        return repo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
